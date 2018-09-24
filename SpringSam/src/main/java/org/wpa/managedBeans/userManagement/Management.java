package org.wpa.managedBeans.userManagement;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.CommitteDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.RankDto;
import org.wpa.DTO.StateDto;
import org.wpa.DTO.YearDto;
import org.wpa.service.Management.ManagementService;
import org.wpa.service.Marginal.CommitteService;
import org.wpa.service.Marginal.PoliticalPartyService;
import org.wpa.service.Marginal.RankService;
import org.wpa.service.Marginal.StateService;
import org.wpa.service.Marginal.YearService;
import org.wpa.service.persons.ManagerService;
import org.wpa.supportOperations.WebMessage;

/**
 * Managed Bean for editing managements.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("management")
@Scope(value = "view")
public class Management implements Serializable {

    private Long managerId;
    private Integer year;

    private ManagementDto management;

    private List<YearDto> avaidableYears;
    private List<PoliticalPartyDto> avaidablePoliticalParties;
    private List<StateDto> avaidableStates;
    private List<CommitteDto> avaidableCommittes;
    private List<RankDto> avaidableRanks;

    private String newPoliticalPartyName;
    private String newCommitteName;
    private String newStateName;

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private StateService stateService;
    @Autowired
    private CommitteService committeService;
    @Autowired
    private PoliticalPartyService partyService;
    @Autowired
    private YearService yearService;
    @Autowired
    private RankService rankService;

    /**
     * Save new added management if it is correct or not save.
     *
     * @return to ManagementList.xhtml or stay on Management.xhtml and write
     * warning message.
     */
    public String save() {
        if (checkManagement()) {
            managementService.saveOrUpdateManagement(management);
            return "/UserManagement/ManagementList.xhtml";
        }
        WebMessage.echoMessage("Všechny položky musí být vybrány");
        return null;
    }
    
    /**
     * Check if new management is valid.
     *
     * @return true if the management is valid, false if not.
     */
    private boolean checkManagement() {
        if (management == null) {
            return false;
        }
        if (management.getYearDto() == null) {
            return false;
        }
        if (management.getPersonDTO() == null) {
            return false;
        }
        if (management.getCommitteDto() == null) {
            return false;
        }
        if (management.getPoliticalParty() == null) {
            return false;
        }
        if (management.getRankDto() == null) {
            return false;
        }
        return management.getStateDto() != null;
    }

    /**
     * Create new State.
     */
    public void newState() {
        if (newStateName != null && !newStateName.isEmpty()) {
            if (stateService.getState(newStateName) == null) {
                StateDto stateDto = new StateDto();
                stateDto.setStateName(newStateName);
                newStateName = "";
                avaidableStates.add(stateDto);
                management.setStateDto(stateDto);
            } else {
                WebMessage.echoMessage("Stát s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Jméno státu nesmí být prázdné.");
        }
    }

    /**
     * Create new Committe.
     */
    public void newCommitte() {
        if (newCommitteName != null && !newCommitteName.isEmpty()) {
            if (committeService.getCommitte(newCommitteName) == null) {
                CommitteDto committeDto = new CommitteDto();
                committeDto.setCommitteName(newCommitteName);
                newCommitteName = "";
                avaidableCommittes.add(committeDto);
                management.setCommitteDto(committeDto);
            } else {
                WebMessage.echoMessage("Komise s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Jméno komise nesmí být prázdné.");
        }
    }

    /**
     * Create new Political Party.
     */
    public void newParty() {
        if (newPoliticalPartyName != null && !newPoliticalPartyName.isEmpty()) {
            if (partyService.getPoliticalParty(newPoliticalPartyName) == null) {
                PoliticalPartyDto partyDto = new PoliticalPartyDto();
                partyDto.setPoliticalartyName(newPoliticalPartyName);
                newPoliticalPartyName = "";
                avaidablePoliticalParties.add(partyDto);
                management.setPoliticalParty(partyDto);
            } else {
                WebMessage.echoMessage("Politická strana s tímto názvem již existuje.");
            }
        } else {
            WebMessage.echoMessage("Jméno politické strany nesmí být prázdné.");
        }
    }

    /**
     * Constructor
     */
    public Management() {

    }

    /**
     * Init parametrs and lists before start.
     */
    @PostConstruct
    public void init() {
        retrieveParametrs();
        retrieveManagement();

        avaidableYears = yearService.getAvaidableYearsOfManager(managerId);
        avaidablePoliticalParties = partyService.getAllPoliticalParties();
        avaidableStates = stateService.getAllStates();
        avaidableCommittes = committeService.getAllCommittes();
        avaidableRanks = rankService.getAllRanks();
        if (management.getYearDto() != null) {
            avaidableYears.add(management.getYearDto());
        }
    }

    /**
     * Retrieve Management.
     */
    private void retrieveManagement() {
        if (year == null) {
            management = new ManagementDto();
            management.setPersonDTO(managerService.getManagerById(managerId));
        } else {
            management = managementService.getManagement(year, managerId);
        }
    }

    /**
     * Retrieve Parametrs.
     */
    private void retrieveParametrs() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id = request.getParameter("id");
        String yearString = request.getParameter("year");
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Parametr \"id\" is required");
        }
        managerId = Long.parseLong(id);
        if (yearString != null && !yearString.isEmpty()) {
            this.year = Integer.parseInt(yearString);
        }

    }

    // GETTERS AND SETTERS
    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public ManagementDto getManagement() {
        return management;
    }

    public void setManagement(ManagementDto management) {
        this.management = management;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<YearDto> getAvaidableYears() {
        return avaidableYears;
    }

    public void setAvaidableYears(List<YearDto> avaidableYears) {
        this.avaidableYears = avaidableYears;
    }

    public List<PoliticalPartyDto> getAvaidablePoliticalParties() {
        return avaidablePoliticalParties;
    }

    public void setAvaidablePoliticalParties(List<PoliticalPartyDto> avaidablePoliticalParties) {
        this.avaidablePoliticalParties = avaidablePoliticalParties;
    }

    public List<StateDto> getAvaidableStates() {
        return avaidableStates;
    }

    public void setAvaidableStates(List<StateDto> avaidableStates) {
        this.avaidableStates = avaidableStates;
    }

    public List<CommitteDto> getAvaidableCommittes() {
        return avaidableCommittes;
    }

    public void setAvaidableCommittes(List<CommitteDto> avaidableCommittes) {
        this.avaidableCommittes = avaidableCommittes;
    }

    public List<RankDto> getAvaidableRanks() {
        return avaidableRanks;
    }

    public void setAvaidableRanks(List<RankDto> avaidableRanks) {
        this.avaidableRanks = avaidableRanks;
    }

    public String getNewPoliticalPartyName() {
        return newPoliticalPartyName;
    }

    public void setNewPoliticalPartyName(String newPoliticalPartyName) {
        this.newPoliticalPartyName = newPoliticalPartyName;
    }

    public String getNewCommitteName() {
        return newCommitteName;
    }

    public void setNewCommitteName(String newCommitteName) {
        this.newCommitteName = newCommitteName;
    }

    public String getNewStateName() {
        return newStateName;
    }

    public void setNewStateName(String newStateName) {
        this.newStateName = newStateName;
    }

}
