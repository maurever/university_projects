package org.wpa.managedBeans.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.mailer.MyMailer;
import org.wpa.service.persons.PersonService;
import org.wpa.supportOperations.EmailVerification;
import org.wpa.supportOperations.PresetEmails;
import org.wpa.supportOperations.WebMessage;

/**
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("registerParticipant")
@Scope(value = "session")
public class RegisterParticipant extends AbstractBean {

    @Autowired
    PersonService personService;

    @Autowired
    MyMailer mailer;

    @Autowired
    EmailVerification emailVerification;

    private String email;
    private String firstName;
    private String lastName;

    public String register() {
        if (checkData()) {
            String password = "ZmenteTotoHeslo_" + (int) ((Math.random() * 999999) + 100000);
            personService.addPerson(firstName, lastName, email, password);
            sendEmail(email, password);
            WebMessage.echoMessage("Registrace proběhla úspěšně. Na uvedenou adresu byl odeslán email s přihlašovacími údaji.");
            return "/index.xhtml?faces-redirect=true";
        } else {
            return "/UserManagement/RegisterParticipant.xhtml";
        }
    }

    private boolean checkData() {
        return (checkEmail() && checkName());
    }

    public boolean checkEmail() {
        if (email.isEmpty()) {
            WebMessage.echoMessage("Všechny údaje musí být vyplněny.");
            return false;
        } else if (!emailVerification.isValidEmail(email)) {
            email = "";
            WebMessage.echoMessage("Špatně zadaný e-mail.");
            return false;
        } else if (!emailVerification.isEmailAvaidable(email)) {
            email = "";
            WebMessage.echoMessage("Uživatel s tímto e-mailem již existuje.");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkName() {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            WebMessage.echoMessage("Všechny údaje musí být vyplněny.");
            return false;
        }
        return true;
    }

    private void sendEmail(String email, String password) {
        mailer.sendMail(email, "Registrace", PresetEmails.newParticipantMail(email, password));
    }

    public RegisterParticipant() {
    }
    
    //GETERS AND SETTERS

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
