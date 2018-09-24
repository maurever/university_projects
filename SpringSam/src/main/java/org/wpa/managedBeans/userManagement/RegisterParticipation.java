package org.wpa.managedBeans.userManagement;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.CommitteDto;
import org.wpa.DTO.DeputyDto;
import org.wpa.DTO.DistrictDto;
import org.wpa.DTO.FractionDto;
import org.wpa.DTO.JournalistDto;
import org.wpa.DTO.LobbyistDto;
import org.wpa.DTO.OrganizationDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.PersonDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.SchoolDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.DTO.StateDto;
import org.wpa.DTO.YearDto;
import org.wpa.Participation.ParticipationService;
import org.wpa.enums.Role;
import org.wpa.service.Marginal.OrganizationService;
import org.wpa.service.Marginal.SchoolService;
import org.wpa.service.Marginal.StateService;
import org.wpa.service.Marginal.YearService;
import org.wpa.service.persons.PersonService;
import org.wpa.supportOperations.WebMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("registerParticipation")
@Scope(value = "view")
public class RegisterParticipation extends AbstractBean {

    private static final String[] FRUITS = {"", "Banana", "Cranberry", "Blueberry", "Orange"};
    private static final String[] VEGETABLES = {"", "Potatoes", "Broccoli", "Garlic", "Carrot"};
    private String currentItem = "";
    private String currentType = "";
    private List<SelectItem> firstList = new ArrayList<SelectItem>();
    private List<SelectItem> secondList = new ArrayList<SelectItem>();

    private String openedYearString;
   
    private Long participantId;
    private PersonDto participant;
    private YearDto year;
    private Role role;

    private ParticipationDto participation;
    private JournalistDto journalist;
    private LobbyistDto lobbyist;
    private SenatorDto senator;
    private DeputyDto deputy;
    private SchoolDto newSchool;
    private OrganizationDto newJournalistOrganization;
    private OrganizationDto newLobbyistOrganization;

    private List<Role> allTypeParticipation;

    private List<OrganizationDto> availableJournalistOrganizations;
    private List<OrganizationDto> availableLobbyistOrganizations;
    private List<CommitteDto> availableLobbyistCommittes;
    private List<CommitteDto> availableSenatorCommittes;
    private List<CommitteDto> availableDeputyCommittes;
    private List<PoliticalPartyDto> availablePoliticalParties;
    private List<FractionDto> availableSenatorFractions;
    private List<FractionDto> availableDeputyFractions;
    private List<StateDto> availableSenatorStates;
    private List<StateDto> availableDeputyStates;

    private List<DistrictDto> availableDistricts;

    private List<SchoolDto> availableSchool;

    @Autowired
    YearService yearService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    StateService stateService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ParticipationService participationService;
    @Autowired
    PersonService personService;

    public boolean isYearOpened() {
        return year != null;
    }

    public String getOpenedYearString() {
        return isYearOpened() ? "" + year.getYear() : "";
    }

    public String register() {
        if (checkParticipation()) {
            return "AlterUser.xhtml";
        }
        WebMessage.echoMessage("Všechny položky musí být vybrány.");
        return null;
    }

    private boolean checkParticipation() {
        if (participation == null) {
            return false;
        } else if (participation instanceof JournalistDto) {
            return checkJournalistParticipation();
        } else if (participation instanceof LobbyistDto) {
            return checkLobbysitParticipation();
        } else if (participation instanceof SenatorDto) {
            return checkSenatorParticipation();
        } else if (participation instanceof DeputyDto) {
            return checkDeputyParticipation();
        }
        return false;
    }

    private boolean checkJournalistParticipation() {
        return false;
    }

    private boolean checkLobbysitParticipation() {
        return false;
    }

    private boolean checkSenatorParticipation() {
        return false;
    }

    private boolean checkDeputyParticipation() {
        return false;
    }

    /**
     * Nezapoenout nakonci ulozit novou skolu do participationDto!
     */
    public void newSchool() {
        if (checkNewSchool()) {
            if (schoolService.getSchoolByName(newSchool.getSchoolName()) == null) {
                availableSchool.add(newSchool);
                availableLobbyistOrganizations.add(newLobbyistOrganization);
            } else {
                WebMessage.echoMessage("Organizace s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Hodnoty musí mít správný tvar a nesmějí být prázdné.");
        }
    }

    private boolean checkNewSchool() {
        if (newSchool == null) {
            return false;
        }
        if (newSchool.getSchoolName().isEmpty() || newSchool.getSchoolName().equals("")) {
            return false;
        }
        if (newSchool.getStreet().isEmpty() || newSchool.getStreet().equals("")) {
            return false;
        }
        if (newSchool.getStreetCode().isEmpty() || newSchool.getStreetCode().equals("")) {
            return false;
        }
        if (newSchool.getCity().isEmpty() || newSchool.getCity().equals("")) {
            return false;
        }
        return newSchool.getPostalCode() >= 10000 && newSchool.getPostalCode() <= 99999;
    }

    public void newJournalistOrganization() {
        if (newJournalistOrganization.getOrganizationName() != null && !newJournalistOrganization.getOrganizationName().isEmpty() && newJournalistOrganization.getDescription() != null && !newJournalistOrganization.getDescription().isEmpty()) {
            if (organizationService.getOrganizationByName(newJournalistOrganization.getOrganizationName()) == null) {
                newJournalistOrganization.setType(1);
                availableJournalistOrganizations.add(newJournalistOrganization);
                journalist.setOrganizationDto(newJournalistOrganization);
            } else {
                WebMessage.echoMessage("Organizace s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Hodnoty nesmějí být prázdné.");
        }
    }

    public void newLobbyistOrganization() {
        if (newLobbyistOrganization.getOrganizationName() != null && !newLobbyistOrganization.getOrganizationName().isEmpty() && newLobbyistOrganization.getDescription() != null && !newLobbyistOrganization.getDescription().isEmpty()) {
            if (organizationService.getOrganizationByName(newJournalistOrganization.getOrganizationName()) == null) {
                newLobbyistOrganization.setType(2);
                availableLobbyistOrganizations.add(newLobbyistOrganization);
                lobbyist.setOrganizationDto(newLobbyistOrganization);
            } else {
                WebMessage.echoMessage("Organizace s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Hodnoty nesmějí být prázdné.");
        }
    }

    @PostConstruct
    public void init() {
        if (yearService.getOpenedYears().isEmpty()) {
            WebMessage.echoMessage("Momentálně nelze odeslat přihlášku.");
        } else {
            //retrieveParametrs();
            //retrieveParticipant();
            year = yearService.getOpenedYears().get(0);
            openedYearString = getOpenedYearString();
            availableSchool = participationService.getAllSchools();
            allTypeParticipation = getAllRole();
            availableJournalistOrganizations = participationService.getAllFreeJournalistOrganizationByOpenedYear();
            availableLobbyistOrganizations = participationService.getAllFreeLobbyistOrganizationByOpenedYear();
            availableLobbyistCommittes = participationService.getAllCommitteForDeputyOrLobbyist();
            availableDeputyCommittes = participationService.getAllCommitteForDeputyOrLobbyist();
            availableSenatorCommittes = participationService.getAllCommitteForSenator();
            availablePoliticalParties = participationService.getAllPoliticalParties();
            availableDeputyFractions = participationService.getAllFractions();
            availableSenatorFractions = participationService.getAllFractions();
            availableDeputyStates = participationService.getAllFreeStatesForCommitteByOpenedYearAndDeputy(availableDeputyCommittes.get(0));
        }
    }

    /**
     * Retrieve Participant.
     */
    private void retrieveParticipant() {
        participant = personService.getPersonById(participantId);

    }

    /**
     * Retrieve Parametrs.
     */
    private void retrieveParametrs() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Parametr \"id\" is required");
        }
        participantId = Long.parseLong(id);
    }

    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.DEPUTY);
        roles.add(Role.SENATOR);
        roles.add(Role.JOURNALIST);
        roles.add(Role.LOBBYIST);
        return roles;
    }

    public ParticipationDto getParticipation() {
        return participation;
    }

    public void setParticipation(ParticipationDto participation) {
        this.participation = participation;
    }

    public JournalistDto getJournalist() {
        return journalist;
    }

    public void setJournalist(JournalistDto journalist) {
        this.journalist = journalist;
    }

    public LobbyistDto getLobbyist() {
        return lobbyist;
    }

    public void setLobbyist(LobbyistDto lobbyist) {
        this.lobbyist = lobbyist;
    }

    public SenatorDto getSenator() {
        return senator;
    }

    public void setSenator(SenatorDto senator) {
        this.senator = senator;
    }

    public DeputyDto getDeputy() {
        return deputy;
    }

    public void setDeputy(DeputyDto deputy) {
        this.deputy = deputy;
    }

    public List<OrganizationDto> getAvailableJournalistOrganizations() {
        return availableJournalistOrganizations;
    }

    public void setAvailableJournalistOrganizations(List<OrganizationDto> availableJournalistOrganizations) {
        this.availableJournalistOrganizations = availableJournalistOrganizations;
    }

    public List<OrganizationDto> getAvailableLobbyistOrganizations() {
        return availableLobbyistOrganizations;
    }

    public void setAvailableLobbyistOrganizations(List<OrganizationDto> availableLobbyistOrganizations) {
        this.availableLobbyistOrganizations = availableLobbyistOrganizations;
    }

    public List<CommitteDto> getAvailableLobbyistCommittes() {
        return availableLobbyistCommittes;
    }

    public void setAvailableLobbyistCommittes(List<CommitteDto> availableLobbyistCommittes) {
        this.availableLobbyistCommittes = availableLobbyistCommittes;
    }

    public List<CommitteDto> getAvaidableSenatorCommittes() {
        return availableSenatorCommittes;
    }

    public void setAvaidableSenatorCommittes(List<CommitteDto> avaidableSenatorCommittes) {
        this.availableSenatorCommittes = avaidableSenatorCommittes;
    }

    public List<CommitteDto> getAvaidableDeputyCommittes() {
        return availableDeputyCommittes;
    }

    public void setAvaidableDeputyCommittes(List<CommitteDto> avaidableDeputyCommittes) {
        this.availableDeputyCommittes = avaidableDeputyCommittes;
    }

    public List<FractionDto> getAvailableSenatorFractions() {
        return availableSenatorFractions;
    }

    public void setAvailableSenatorFractions(List<FractionDto> availableSenatorFractions) {
        this.availableSenatorFractions = availableSenatorFractions;
    }

    public List<FractionDto> getAvailableDeputyFractions() {
        return availableDeputyFractions;
    }

    public void setAvailableDeputyFractions(List<FractionDto> availableDeputyFractions) {
        this.availableDeputyFractions = availableDeputyFractions;
    }

    public List<StateDto> getAvailableSenatorStates() {
        return availableSenatorStates;
    }

    public void setAvailableSenatorStates(List<StateDto> availableSenatorStates) {
        this.availableSenatorStates = availableSenatorStates;
    }

    public List<StateDto> getAvailableDeputyStates() {
        return availableDeputyStates;
    }

    public void setAvailableDeputyStates(List<StateDto> availableDeputyStates) {
        this.availableDeputyStates = availableDeputyStates;
    }

    public List<PoliticalPartyDto> getAvaidablePoliticalParties() {
        return availablePoliticalParties;
    }

    public void setAvaidablePoliticalParties(List<PoliticalPartyDto> avaidablePoliticalParties) {
        this.availablePoliticalParties = avaidablePoliticalParties;
    }

    public List<FractionDto> getAvailableFraction() {
        return availableSenatorFractions;
    }

    public void setAvailableFraction(List<FractionDto> availableFraction) {
        this.availableSenatorFractions = availableFraction;
    }

    public List<StateDto> getAvailableStates() {
        return availableSenatorStates;
    }

    public void setAvailableStates(List<StateDto> availableStates) {
        this.availableSenatorStates = availableStates;
    }

    public List<DistrictDto> getAvailableDistricts() {
        return availableDistricts;
    }

    public void setAvailableDistricts(List<DistrictDto> availableDistricts) {
        this.availableDistricts = availableDistricts;
    }

    public List<Role> getAllTypeParticipation() {
        return allTypeParticipation;
    }

    public void setAllTypeParticipation(List<Role> allTypeParticipation) {
        this.allTypeParticipation = allTypeParticipation;
    }

    public List<CommitteDto> getAvailableSenatorCommittes() {
        return availableSenatorCommittes;
    }

    public void setAvailableSenatorCommittes(List<CommitteDto> availableSenatorCommittes) {
        this.availableSenatorCommittes = availableSenatorCommittes;
    }

    public List<CommitteDto> getAvailableDeputyCommittes() {
        return availableDeputyCommittes;
    }

    public void setAvailableDeputyCommittes(List<CommitteDto> availableDeputyCommittes) {
        this.availableDeputyCommittes = availableDeputyCommittes;
    }

    public List<PoliticalPartyDto> getAvailablePoliticalParties() {
        return availablePoliticalParties;
    }

    public void setAvailablePoliticalParties(List<PoliticalPartyDto> availablePoliticalParties) {
        this.availablePoliticalParties = availablePoliticalParties;
    }

    public List<SchoolDto> getAvailableSchool() {
        return availableSchool;
    }

    public void setAvailableSchool(List<SchoolDto> availableSchool) {
        this.availableSchool = availableSchool;
    }

    public SchoolDto getNewSchool() {
        return newSchool;
    }

    public void setNewSchool(SchoolDto newSchool) {
        this.newSchool = newSchool;
    }

    public OrganizationDto getNewJournalistOrganization() {
        return newJournalistOrganization;
    }

    public void setNewJournalistOrganization(OrganizationDto newJournalistOrganization) {
        this.newJournalistOrganization = newJournalistOrganization;
    }

    public void setOpenedYearString(String openedYearString) {
        this.openedYearString = openedYearString;
    }
    
    

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(String currentItem) {
        this.currentItem = currentItem;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public List<SelectItem> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<SelectItem> firstList) {
        this.firstList = firstList;
    }

    public List<SelectItem> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<SelectItem> secondList) {
        this.secondList = secondList;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public PersonDto getParticipant() {
        return participant;
    }

    public void setParticipant(PersonDto participant) {
        this.participant = participant;
    }

    public YearDto getYear() {
        return year;
    }

    public void setYear(YearDto year) {
        this.year = year;
    }

    public OrganizationDto getNewLobbyistOrganization() {
        return newLobbyistOrganization;
    }

    public void setNewLobbyistOrganization(OrganizationDto newLobbyistOrganization) {
        this.newLobbyistOrganization = newLobbyistOrganization;
    }

    public RegisterParticipation() {
        SelectItem item = new SelectItem("", "");
        firstList.add(item);
        item = new SelectItem("fruits", "Fruits");
        firstList.add(item);
        item = new SelectItem("vegetables", "Vegetables");
        firstList.add(item);

        for (int i = 0; i < FRUITS.length; i++) {
            item = new SelectItem(FRUITS[i]);
        }
    }

    public void valueChanged(ValueChangeEvent event) {
        secondList.clear();
        if (null != event.getNewValue()) {
            String[] currentItems;

            if (((String) event.getNewValue()).equals("fruits")) {
                currentItems = FRUITS;
            } else {
                currentItems = VEGETABLES;
            }

            for (int i = 0; i < currentItems.length; i++) {
                SelectItem item = new SelectItem(currentItems[i]);

                secondList.add(item);
            }
        }
    }

}
