package org.wpa.managedBeans.userManagement;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.wpa.DTO.JournalistDto;
import org.wpa.DTO.LobbyistDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.ManagerDto;
import org.wpa.DTO.OrganizationDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.PersonDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.DTO.StateDto;
import org.wpa.DTO.YearDto;
import org.wpa.Participation.ParticipationService;
import org.wpa.service.Management.ManagementService;
import org.wpa.service.Marginal.CommitteService;
import org.wpa.service.Marginal.DistrictService;
import org.wpa.service.Marginal.OrganizationService;
import org.wpa.service.Marginal.PoliticalPartyService;
import org.wpa.service.Marginal.StateService;
import org.wpa.service.Marginal.YearService;
import org.wpa.service.persons.ManagerService;
import org.wpa.service.persons.PersonService;
import org.wpa.supportOperations.Authenticator;

/**
 *
 * @author Vít Hlaváček <hlava.vit at google.com>
 */
@Component("managAndParticList")
@Scope(value = "view")
public class ManagementAndParticipationList {

    private PersonDto personDto;
    private List<String> activeYears;
    private HashMap<String, Integer> activeYearsMap;
    private String selectedYear = "";
    private List<String> filterBy;
    private String selectedFilter = "";
    private boolean notLimited = false;
    private HashMap<String, Long> marginalFilterMap;
    private List<String> marginalFilter;
    private String selectedMarginal = "";

    ManagementDto managementDto = null;
    JournalistDto journalistDto = null;
    LobbyistDto lobbyistDto = null;
    SenatorDto senatorDto = null;
    DeputyDto deputyDto = null;

    private List<ManagementDto> managementDtos;
    private List<SenatorDto> senatorDtos;
    private List<DeputyDto> deputyDtos;
    private List<LobbyistDto> lobbyistDtos;
    private List<JournalistDto> journalistDtos;

    public final static String POLITICAL_PARTY = "Politická srana";
    public final static String STATE = "Stát";
    public final static String COMMITTE = "Komise";
    public final static String ORGANIZATION = "Organizace";
    public final static String FRACTION = "Frakce";
    public final static String DISTRICT = "Distrikt";

    @Autowired
    Authenticator authenticator;
    @Autowired
    ManagerService managerService;
    @Autowired
    PersonService personService;
    @Autowired
    YearService yearService;
    @Autowired
    ParticipationService participationService;
    @Autowired
    ManagementService managementService;
    @Autowired
    PoliticalPartyService politicalPartyService;
    @Autowired
    StateService stateService;
    @Autowired
    CommitteService committeService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    DistrictService districtService;

    /**
     * Creates a new instance of ManagementAndParticipationList
     */
    public ManagementAndParticipationList() {
    }

    @PostConstruct
    public void init() throws IllegalAccessException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String idString = request.getParameter("id");
        loadPerson(idString);
        if (!isAllovedToViewThisUser(personDto.getId())) {
            throw new IllegalAccessException("You are not permited to view this users page");
        }
        loadActiveYears();
        if (!hasActiveYear()) {
            return;
        }
    }

    public boolean isAllovedToViewThisUser(Long id) {
        if (authenticator.getCurrentUsersId().equals(id)) {
            return true;
        }
        return authenticator.isAlowed(new Long(4));
    }

    public boolean isAllovedToViewAll() {
        return authenticator.isAlowed(new Long(4));
    }

    public String applyFilter() {
        managementDtos = null;
        senatorDtos = null;
        deputyDtos = null;
        lobbyistDtos = null;
        journalistDtos = null;
        if (!checkSelection()) {
            return null;
        }
        if (notLimited) {
            loadMarginalFilter();
        } else {
            applyFilterPerUser();
        }
        return null;
    }

    public String applyGlobalFilter() {
         if(!checkSelection())return null;   
         if (selectedMarginal != null && !selectedFilter.isEmpty()) {
            applyGenericFilter(marginalFilterMap.get(selectedMarginal));
        }
         return null;
    }
    private boolean checkSelection(){
        if(selectedYear==null)  return false;
        if(selectedFilter == null)return false;
        if(selectedYear.isEmpty())return false;
        if(selectedFilter.isEmpty())return false;
        return true;
    }
    public void loadMarginalFilter() {
        marginalFilterMap = new HashMap<String, Long>();
        marginalFilter = new ArrayList<String>();
        if (!notLimited) {
            return;
        }
        if (selectedFilter.equals(POLITICAL_PARTY)) {
            List<PoliticalPartyDto> parties = politicalPartyService.getAllPoliticalParties();
            for (PoliticalPartyDto partyDto : parties) {
                marginalFilterMap.put(partyDto.getPoliticalartyName(), partyDto.getId());
                marginalFilter.add(partyDto.getPoliticalartyName());
            }
        } else if (selectedFilter.equals(STATE)) {
            List<StateDto> stateDtos = stateService.getAllStates();
            for (StateDto stateDto : stateDtos) {
                marginalFilterMap.put(stateDto.getStateName(), stateDto.getId());
                marginalFilter.add(stateDto.getStateName());
            }
        } else if (selectedFilter.equals(COMMITTE)) {
            List<CommitteDto> committeDtos = committeService.getAllCommittes();
            for (CommitteDto committeDto : committeDtos) {
                marginalFilterMap.put(committeDto.getCommitteName(), committeDto.getId());
                marginalFilter.add(committeDto.getCommitteName());
            }
        } else if (selectedFilter.equals(ORGANIZATION)) {
            List<OrganizationDto> organizationDtos = organizationService.getAllOrganization();
            for (OrganizationDto organizationDto : organizationDtos) {
                marginalFilterMap.put(organizationDto.getOrganizationName(), organizationDto.getId());
                marginalFilter.add(organizationDto.getOrganizationName());
            }
        } else if (selectedFilter.equals(DISTRICT)) {
            List<DistrictDto> districtDtos = districtService.getAllDistricts();
            for (DistrictDto districtDto : districtDtos) {
                marginalFilterMap.put(districtDto.getDistrictName(), districtDto.getId());
                marginalFilter.add(districtDto.getDistrictName());
            }
        }
    }

    private void applyFilterPerUser() {
        marginalFilterMap = null;
        selectedMarginal = null;
        applyGenericFilter(null);
    }

    private String applyGenericFilter(Long id) {
        if (selectedFilter.equals(POLITICAL_PARTY)) {
            if (id == null) {
                id = getPoliticalPartyId();
            }
            managementDtos = politicalPartyService.getManagementsOf(id,activeYearsMap.get(selectedYear));
            deputyDtos = politicalPartyService.getDeputiesOf(id,activeYearsMap.get(selectedYear));
            senatorDtos = politicalPartyService.getSenatorsOf(id,activeYearsMap.get(selectedYear));
        } else if (selectedFilter.equals(STATE)) {
            if (id == null) {
                id = getStateId();
            }
            managementDtos = stateService.getManagementsOf(id,activeYearsMap.get(selectedYear));
            senatorDtos = stateService.getSenatorsOf(id,activeYearsMap.get(selectedYear));
            deputyDtos = stateService.getDeputiesOf(id,activeYearsMap.get(selectedYear));
        } else if (selectedFilter.equals(COMMITTE)) {
            if (id == null) {
                id = getCommitteId();
            }
            managementDtos = committeService.getManagementsOf(id,activeYearsMap.get(selectedYear));
            senatorDtos = committeService.getSenatorsOf(id,activeYearsMap.get(selectedYear));
            deputyDtos = committeService.getDeputiesOf(id,activeYearsMap.get(selectedYear));
            lobbyistDtos = committeService.getLobbyistsOf(id,activeYearsMap.get(selectedYear));
        } else if (selectedFilter.equals(ORGANIZATION)) {
            if (id == null) {
                id = getOrganizationId();
            }
            journalistDtos = organizationService.getJournalistsOf(id,activeYearsMap.get(selectedYear));
            lobbyistDtos = organizationService.getLobbyistsOf(id,activeYearsMap.get(selectedYear));
        } else if (selectedFilter.equals(FRACTION)) {
            if (id == null) {
                id = getFractionId();
            }
            senatorDtos = politicalPartyService.getSenatorsOfFraction(id,activeYearsMap.get(selectedYear));
            deputyDtos = politicalPartyService.getDeputiesOfFraction(id,activeYearsMap.get(selectedYear));
        } else if (selectedFilter.equals(DISTRICT)) {
            if (id == null) {
                id = getDistrictId();
            }
            senatorDtos = districtService.getSenatorsOf(id,activeYearsMap.get(selectedYear));
        }
        return null;
    }

    public boolean hasActiveYear() {
        if (activeYears == null || activeYears.isEmpty()) {
            return false;
        }
        return true;
    }

    public void loadFilter() {
        resetForNewFilter();
        if (notLimited) {
            filterBy.add(POLITICAL_PARTY);
            filterBy.add(COMMITTE);
            filterBy.add(STATE);
            filterBy.add(ORGANIZATION);
            filterBy.add(DISTRICT);
        } else if (personDto.isManager()) {
            managementDto = managementService.getManagement(activeYearsMap.get(selectedYear), personDto.getId());
            filterBy.add(POLITICAL_PARTY);
            filterBy.add(COMMITTE);
            filterBy.add(STATE);
        } else {
            ParticipationDto participationDto = participationService.getParticipation(activeYearsMap.get(selectedYear), personDto.getId());
            if (participationDto instanceof JournalistDto) {
                journalistDto = (JournalistDto) participationDto;
                filterBy.add(ORGANIZATION);
                selectedFilter = ORGANIZATION;
            } else if (participationDto instanceof LobbyistDto) {
                lobbyistDto = (LobbyistDto) participationDto;
                filterBy.add(COMMITTE);
                filterBy.add(ORGANIZATION);
            } else if (participationDto instanceof SenatorDto) {
                senatorDto = (SenatorDto) participationDto;
                filterBy.add(FRACTION);
                filterBy.add(COMMITTE);
                filterBy.add(STATE);
                filterBy.add(DISTRICT);
            } else {
                deputyDto = (DeputyDto) participationDto;
                filterBy.add(FRACTION);
                filterBy.add(COMMITTE);
                filterBy.add(STATE);
            }
        }
    }

    private Long getDistrictId() {
        if (senatorDto != null) {
            return senatorDto.getDistrictDto().getId();
        }
        return null;
    }

    private Long getFractionId() {
        if (senatorDto != null) {
            return senatorDto.getFractionDto().getId();
        }
        if (deputyDto != null) {
            return deputyDto.getFractionDto().getId();
        }
        return null;
    }

    private Long getOrganizationId() {
        if (journalistDto != null) {
            return journalistDto.getOrganizationDto().getId();
        }
        if (lobbyistDto != null) {
            lobbyistDto.getOrganizationDto().getId();
        }
        return null;
    }

    private Long getCommitteId() {
        if (managementDto != null) {
            return managementDto.getCommitteDto().getId();
        }
        if (lobbyistDto != null) {
            return lobbyistDto.getCommitteDto().getId();
        }
        if (senatorDto != null) {
            return senatorDto.getCommiteDto().getId();
        }
        if (deputyDto != null) {
            return deputyDto.getCommiteDto().getId();
        }
        return null;
    }

    private Long getPoliticalPartyId() {
        if (managementDto != null) {
            return managementDto.getPoliticalParty().getId();
        }
        return null;
    }

    private Long getStateId() {
        if (managementDto != null) {
            return managementDto.getStateDto().getId();
        }
        if (senatorDto != null) {
            return senatorDto.getStateDto().getId();
        }
        if (deputyDto != null) {
            return deputyDto.getStateDto().getId();
        }
        return null;
    }

    private void resetForNewFilter() {
        filterBy = new ArrayList<String>();
        managementDto = null;
        journalistDto = null;
        lobbyistDto = null;
        senatorDto = null;
        deputyDto = null;
    }

    public void loadActiveYears() {
        List<YearDto> dtos;
        activeYears = new ArrayList<String>();
        activeYearsMap = new HashMap<String, Integer>();
        selectedYear = "";
        if (notLimited) {
            dtos = yearService.getAllYears();
        } else {
            dtos = yearService.getActiveYearsOfPerson(personDto.getId());
        }
        for (YearDto dto : dtos) {
            activeYears.add(dto.getYear().toString());
            activeYearsMap.put(dto.getYear().toString(), dto.getYear());
        }
    }

    private void loadPerson(String idString) {
        Long id;
        if (idString == null || idString.isEmpty()) {
            personDto = authenticator.getCurrentUser();
            id = personDto.getId();
        } else {
            id = Long.parseLong(idString);
            personDto = personService.getPersonById(id);
        }
        ManagerDto managerDto = managerService.getManagerById(id);
        if (managerDto != null) {
            personDto = managerDto;
        }}

    public boolean isManagementDtosSet() {
        return managementDtos != null;
    }

    public boolean isSenatorDtosSet() {
        return senatorDtos != null;
    }

    public boolean isDeputyDtosSet() {
        return deputyDtos != null;
    }

    public boolean isLobbyistDtosSet() {
        return lobbyistDtos != null;
    }

    public boolean isJournalistDtosSet() {
        return journalistDtos != null;
    }

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }

    public List<String> getActiveYears() {
        return activeYears;
    }

    public void setActiveYears(List<String> activeYears) {
        this.activeYears = activeYears;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    

    public List<String> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<String> filterBy) {
        this.filterBy = filterBy;
    }

    public String getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(String sectedFilter) {
        this.selectedFilter = sectedFilter;
    }

    public List<ManagementDto> getManagementDtos() {
        return managementDtos;
    }

    public List<SenatorDto> getSenatorDtos() {
        return senatorDtos;
    }

    public List<DeputyDto> getDeputyDtos() {
        return deputyDtos;
    }

    public List<LobbyistDto> getLobbyistDtos() {
        return lobbyistDtos;
    }

    public List<JournalistDto> getJournalistDtos() {
        return journalistDtos;
    }

    public boolean isNotLimited() {
        return notLimited;
    }

    public void setNotLimited(boolean notLimited) {
        this.notLimited = notLimited;
    }

    public List<String> getMarginalFilter() {
        return marginalFilter;
    }

    public void setMarginalFilter(List<String> marginalFilter) {
        this.marginalFilter = marginalFilter;
    }

    public String getSelectedMarginal() {
        return selectedMarginal;
    }

    public void setSelectedMarginal(String selectedMarginal) {
        this.selectedMarginal = selectedMarginal;
    }

}
