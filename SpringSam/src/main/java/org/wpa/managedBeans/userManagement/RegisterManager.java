package org.wpa.managedBeans.userManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.CompetencyDto;
import org.wpa.mailer.MyMailer;
import org.wpa.service.persons.ManagerService;
import org.wpa.supportOperations.Authenticator;
import org.wpa.supportOperations.EmailVerification;
import org.wpa.supportOperations.PresetEmails;
import org.wpa.supportOperations.WebMessage;

/**
 * TODO VM - osetreni vsech prazdnych vstupu
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("registerManager")
@Scope(value = "session")
public class RegisterManager extends AbstractBean {

    @Autowired
    ManagerService managerService;

    @Autowired
    MyMailer mailer;

    @Autowired
    EmailVerification emailVerification;

    List<CompetencyDto> competencies;

    private String email;
    private String firstName;
    private String lastName;
    @PostConstruct
    public void init(){
        competencies = managerService.getAllPossibleCompetencies();
    }
    public String register() {
        if (checkData()) {
            String password = "ZmenteTotoHeslo_" + (int) ((Math.random() * 999999) + 100000);
            List<Long> selectedCompetencies = getSelectedCompetencies();
            managerService.addManager(firstName, lastName, email, password, selectedCompetencies);
            sendEmail(email, password);
            WebMessage.echoMessage("Registrace proběhla úspěšně. Na adresu byl odeslán email s přihlašovacími údaji.");
            return "/index.xhtml?faces-redirect=true";
        } else {
            return "/UserManagement/RegisterManager.xhtml";
        }
    }

    public boolean isLoggedIn() {

        return Authenticator.haveCompetency(new Long(100));
    }

    public void redirectToLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("LoginAndLogout.xhtml");
    }

    private void sendEmail(String email, String password) {
        mailer.sendMail(email, "Registrace", PresetEmails.newManagerMail(email, password));
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

    public List<Long> getSelectedCompetencies() {
        competencies = managerService.getAllPossibleCompetencies();
        List<Long> selectedCompetencies = new ArrayList<Long>();
        for (CompetencyDto c : competencies) {
            if (c.isSelected()) {
                selectedCompetencies.add(c.getId());
            }
        }
        return selectedCompetencies;
    }

    public boolean checkName() {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            WebMessage.echoMessage("Všechny údaje musí být vyplněny.");
            return false;
        }
        return true;
    }

    public void resetAll() {
        setEmail("");
        setFirstName("");
        setLastName("");
    }

    public RegisterManager() {
    }
    
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

    public List<CompetencyDto> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<CompetencyDto> competencies) {
        this.competencies = competencies;
    }

}
