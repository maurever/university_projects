package org.wpa.managedBeans.userManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ManagerDto;
import org.wpa.DTO.PersonDto;
import org.wpa.service.persons.ManagerService;
import org.wpa.service.persons.PersonService;
import org.wpa.supportOperations.Authenticator;
import org.wpa.supportOperations.EmailVerification;
import org.wpa.supportOperations.WebMessage;

/**
 * ManagedBean for altering users.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
//@ManagedBean
//@ViewScoped
@Component("alterUser")
@Scope(value = "session")
public class AlterUser extends AbstractBean {

    List<CompetencyDto> competencies;
    PersonDto person;
    private String email;
    private String firstName;
    private String lastName;
    private String oldPass;
    private String newPass;
    private String newPass2;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private PersonService personService;
    @Autowired 
    private EmailVerification emailVerification;
    @Autowired
    private Authenticator authenticator;

    /**
     * Save new data. If the altering person is Manager save another then
     * Person.
     */
    public void save() {
        if (isManager()) {
            save((ManagerDto) person);
        } else {
            save((PersonDto) person);
        }

    }

    /**
     * Save new data for participant.
     *
     * @param participant
     */
    private void save(PersonDto participant) {
        if (checkForm()) {
            String password = getNewPasshash();
            personService.alterPerson(participant.getId(), firstName, lastName, email, password);
            WebMessage.echoMessage("Uživatel upraven.");
        }
    }

    /**
     * Save new data for manager. Save also changed competencies.
     *
     * @param manager
     */
    private void save(ManagerDto manager) {
        if (checkForm()) {
            String passHash = getNewPasshash();
            List<Long> compets = getNewCompetencies();
            managerService.alterManager(manager.getId(), firstName, lastName, email, passHash, compets);
            WebMessage.echoMessage("Uživatel upraven.");
        }
    }

    /**
     * Delete person by id. It is competency only for managers.
     *
     * @param id
     */
    public void deleteUser(long id) {
        personService.deletePerson(id);
    }

    /**
     * Get changed competencies in List.
     *
     * @return List<Long> id of competencies;
     */
    private List<Long> getNewCompetencies() {
        if (isPermitedToChangeCompetencies() && competencies != null) {
            List<Long> compets = new ArrayList<Long>();
            for (CompetencyDto c : competencies) {
                if (c.isSelected()) {
                    compets.add(c.getId());
                }
            }
            return compets;
        }
        return null;
    }

    /**
     * Get and check changed password. Write warning message.
     *
     * @return checked password;
     */
    private String getNewPasshash() {
        if (checkCurrentPassword()) {
            if (newPass.isEmpty() || newPass2.isEmpty()) {
                return null;
            } else if (!newPass.equals(newPass2)) {
                newPass = "";
                newPass2 = "";
                WebMessage.echoMessage("Nová hesla se neshodují.");
                return null;
            }
            return newPass;
        }
        return null;
    }

    /**
     * Check password setting. Clear password data to be wrong.
     *
     * @return true if passwords are equal, false if are not equal or are some
     * empty.
     */
    private boolean checkCurrentPassword() {
        if (oldPass == null || oldPass.isEmpty()) {
            return false;
        }

        if (personService.getPerson(email, oldPass) != null) {
            return true;
        }
        oldPass = "";
        return false;
    }

    /**
     * Check all input data.
     *
     * @return true, if data are corect, false if not.
     */
    private boolean checkForm() {
        return (checkEmail() && checkName());
    }

    /**
     * Validate email via EmailVerification. Write warning message.
     *
     * @return true if email is validate or false if not.
     */
    public boolean checkEmail() {
       if (email.isEmpty()) {
            WebMessage.echoMessage("Všechny údaje musí být vyplněny.");
            return false;
        } else if (!emailVerification.isValidEmail(email)) {
            email = "";
            WebMessage.echoMessage("Špatně zadaný e-mail.");
            return false;
        } else if (!emailVerification.isEmailAvaidable(email) && !email.equals(person.getEmail())) {
            email = "";
            WebMessage.echoMessage("Uživatel s tímto e-mailem již existuje.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check name to be correct. Write warning message.
     *
     * @return false, if first name or last name are empty, or true if not.
     */
    public boolean checkName() {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            WebMessage.echoMessage("Všechny údaje musí být vyplněny.");
            return false;
        }
        return true;
    }

    /**
     * 
     *
     * @return
     */
    public boolean isPermitedToShowAlteringColumns() {
        return Authenticator.haveCompetency(new Long(3));
    }

    /**
     * 
     *
     * @return
     */
    public boolean isPermitedToChangeThisProfile() {
        if (!isManager() && Authenticator.haveCompetency(new Long(3))) {
            return true;
        }
        if (Authenticator.haveCompetency(new Long(100))) {
            return true;
        }
        return authenticator.getCurrentUsersId().equals(person.getId());
    }

    /**
     * 
     *
     * @return
     */
    public boolean isPermitedToChangeCompetencies() {
        return Authenticator.haveCompetency(new Long(100)) && isManager();

    }

    /**
     * Load person data from database for uniq id. TODO: ověření zda je
     * přihlášen dělá spring!
     *
     * @param id
     * @throws IOException
     */
    public void load(long id) throws IOException {
        if (id == 0) {
            Long longId = getCurentUsersId();
            if (longId == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("LoginAndLogout.xhtml");
                return;
            }
            load(longId);
        } else {
            load(new Long(id));
        }
    }

    /**
     * Load person data from database for uniq id. If logged person is manager,
     * load manager data (with competencies), else only person data.
     *
     * @param id
     */
    private void load(Long id) {
        ManagerDto managerDto = managerService.getManagerById(id);
        if (managerDto != null) {
            person = managerDto;
        } else {
            person = personService.getPersonById(id);
        }
        loadPerson(person);
        if (isPermitedToChangeCompetencies()) {
            loadCompetencies();
        }

    }

    /**
     * Load manager competencies.
     */
    private void loadCompetencies() {
        competencies = managerService.getAllPossibleCompetencies();
        for (CompetencyDto c : managerService.getCompeteciesOfManagerById(person.getId())) {
            competencies.get(competencies.indexOf(c)).setSelected(true);
        }
    }

    /**
     * Load only person data.
     *
     * @param person
     */
    private void loadPerson(PersonDto person) {
        email = person.getEmail();
        firstName = person.getFirstName();
        lastName = person.getLastName();
    }

    /**
     * Check if logged user is Manager.
     *
     * @return if logged user is manager return true, else false.
     */
    public boolean isManager() {
        return person.isManager();
    }

    /**
     * Getter for getting actual logged user. Use Authenticator.
     *
     * @return Long id.
     */
    public Long getCurentUsersId() {
        return authenticator.getCurrentUsersId();
    }

    public AlterUser() {
    }

    // GETTERS AND SETTERS
    public List<CompetencyDto> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<CompetencyDto> competencies) {
        this.competencies = competencies;
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

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPass2() {
        return newPass2;
    }

    public void setNewPass2(String newPass2) {
        this.newPass2 = newPass2;
    }

}
