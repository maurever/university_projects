package org.wpa.supportOperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Person;
import org.wpa.DAOImpl.GenericDAO;

/**
 * Validator for input e-mail.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("emailVerification")
@Scope(value = "singleton")
public class EmailVerification{

    @Autowired
    protected GenericDAO genericDao;

    /**
     * Check if the email address is not emptz and if has correct format.
     * @param emailAddress
     * @return true if email is valid, false if not;
     */
    public boolean isValidEmail(String emailAddress) {
        return emailAddress.contains(" ") == false && emailAddress.matches(".+@.+\\.[a-z]+");
    }
    
    /**
     * Check if the email address exist in the database.
     * @param emailAddress
     * @return true, if the email does not exist in the database, false if there is;
     */
    @Transactional(readOnly = true)
    public boolean isEmailAvaidable(String emailAddress) {
        Person person= genericDao.findUniqBy(Person.class, "email", emailAddress);
        if (person != null) {
            System.out.println(person.toString());
            return false;
        }
        return true;
    }
}
