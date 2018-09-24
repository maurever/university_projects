package org.wpa.service.AbstractDataAccessService;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.wpa.DAOImpl.GenericDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.wpa.BO.Competency;
import org.wpa.BO.Manager;
import org.wpa.BO.Person;

/**
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */

//Configuration in applicationContext-security.xml
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {
     private GenericDAO genericDAO;
    private TransactionTemplate transactionTemplate;

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    UserDetails ud = null;
                    
                    Person person;
                    try {
                        person = genericDAO.findUniqBy(org.wpa.BO.Person.class, "email", username);
                    } catch (EmptyResultDataAccessException erdaex) {
                        throw new BadCredentialsException("Uživatel neexistuje!");
                    }
                    String password = (String) upat.getCredentials();
                    
                    if (person == null || !person.hasPassword(password)) {
                        AuthenticationException e = new BadCredentialsException("Neplatné uživatelské údaje!");
                        throw e;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        auths.add(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));
                        if(person.isManager()){
                            auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                            List<Competency> comps = ((Manager)person).getCompetencies();
                            for(Competency competency : comps){
                                auths.add(new SimpleGrantedAuthority("Competency_"+competency.getId()));
                            }
                        }
                        ud = new User(username, password, auths);
                    }
                    return ud;
                } catch(AuthenticationException e){
                    status.setRollbackOnly();
                    throw e;
                }catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    
}
