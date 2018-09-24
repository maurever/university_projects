package org.wpa.supportOperations;

import java.util.Collection;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wpa.DTO.PersonDto;
import org.wpa.service.persons.PersonService;

/**
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("authenticator")
@Scope(value = "session")
public class Authenticator {

    @Autowired
    private PersonService personService;

    /**
     *
     * @param competency id of needed competency
     * @return true if current user has specified competency or is an
     * administrator, false otherwise
     */
    public static boolean haveCompetency(Long competency) {

        if (competency > 100 || competency < 1) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority("Competency_100")) || authorities.contains(new SimpleGrantedAuthority("Competency_" + competency));
    }

    public PersonDto getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return personService.getPerson(userName);
    }

    public Long getCurrentUsersId() {
        PersonDto person = getCurrentUser();
        if (person != null) {
            return getCurrentUser().getId();
        }
        return null;
    }

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public static void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    public boolean isAlowed(long Competency){
        return haveCompetency(Competency);
    }
    
}
