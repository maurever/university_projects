package org.wpa.Participation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wpa.BO.Committe;
import org.wpa.BO.Deputy;
import org.wpa.BO.District;
import org.wpa.BO.Fraction;
import org.wpa.BO.Journalist;
import org.wpa.BO.Lobbyist;
import org.wpa.BO.Organization;
import org.wpa.BO.Participation;
import org.wpa.BO.ParticipationId;
import org.wpa.BO.Person;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.School;
import org.wpa.BO.Senator;
import org.wpa.BO.State;
import org.wpa.BO.Year;
import org.wpa.DTO.CommitteDto;
import org.wpa.DTO.DeputyDto;
import org.wpa.DTO.FractionDto;
import org.wpa.DTO.JournalistDto;
import org.wpa.DTO.LobbyistDto;
import org.wpa.DTO.OrganizationDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.SchoolDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.DTO.StateDto;
import org.wpa.DTO.YearDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("participationService")
@Scope(value = "singleton")
public class ParticipationServiceImpl extends AbstractDataAccessService implements ParticipationService {

    @Override
    public ParticipationDto getParticipation(Integer year, Long participantId) {
        ParticipationId pId = new ParticipationId(year, participantId);
        Participation participation = genericDao.getByKey(Participation.class, pId);
        return participationToDto(participation);
    }

    @Override
    public Long addParticipation(Long participantId, Integer year, String role, Map<String, Long> map) {
        return null;
    }

    public void saveOrUpdateParticipation(ParticipationDto participationDto) {

    }

    @Override
    public Long addLobbyist(Long participantId, Long schoolId, Long organizationId, Long committeId, int yearId) {
        Person participant = genericDao.getByKey(Person.class, participantId);
        Year year = genericDao.getByKey(Year.class, yearId);
        Organization org = genericDao.getByKey(Organization.class, organizationId);
        Committe comm = genericDao.getByKey(Committe.class, committeId);
        School school = genericDao.getByKey(School.class, schoolId);
        Lobbyist lobbyist = new Lobbyist(participant, year, school, org, comm);
        return genericDao.persistEntity(lobbyist).getPersonId();
    }

    @Override
    public Long addJournalist(Long participantId, Long schoolId, Long organizationId, int yearId) {
        Person participant = genericDao.getByKey(Person.class, participantId);
        Year year = genericDao.getByKey(Year.class, yearId);
        School school = genericDao.getByKey(School.class, schoolId);
        Organization org = genericDao.getByKey(Organization.class, organizationId);
        Journalist journalist = new Journalist(participant, year, school, org);
        return genericDao.persistEntity(journalist).getPersonId();
    }

    @Override
    public Long addSenator(Long participantId, Long schoolId, Long committeId, Long fractionId, Long stateId, Long districtId, int yearId) {
        Person participant = genericDao.getByKey(Person.class, participantId);
        Year year = genericDao.getByKey(Year.class, yearId);
        School school = genericDao.getByKey(School.class, schoolId);
        Committe comm = genericDao.getByKey(Committe.class, committeId);
        Fraction fra = genericDao.getByKey(Fraction.class, fractionId);
        State state = genericDao.getByKey(State.class, stateId);
        District dist = genericDao.getByKey(District.class, districtId);
        Senator senator = new Senator(participant, year, school, comm, fra, state, dist);
        return genericDao.persistEntity(senator).getPersonId();
    }

    @Override
    public Long addDeputy(Long participantId, Long schoolId, Long committeId, Long fractionId, Long stateId, int yearId) {
        Person participant = genericDao.getByKey(Person.class, participantId);
        Year year = genericDao.getByKey(Year.class, yearId);
        School school = genericDao.getByKey(School.class, schoolId);
        Committe comm = genericDao.getByKey(Committe.class, committeId);
        Fraction fra = genericDao.getByKey(Fraction.class, fractionId);
        State state = genericDao.getByKey(State.class, stateId);
        Deputy deputy = new Deputy(participant, year, school, comm, fra, state);
        return genericDao.persistEntity(deputy).getPersonId();
    }

    @Override
    public YearDto getOpenedYear() {
        Year year = genericDao.getByNamedQuery("Year.findOpenedYear", Year.class).get(0);
        return new YearDto(year);
    }

    @Override
    public List<ParticipationDto> getAllParticipation() {
        List<Participation> participations = genericDao.getAll(Participation.class);
        List<ParticipationDto> participationDtos = new ArrayList<ParticipationDto>();
        for (Participation participation : participations) {
            participationDtos.add(participationToDto(participation));
        }
        return participationDtos;
    }

    @Override
    public List<JournalistDto> getAllJournalistByOpenYear() {
        List<Journalist> journalists = genericDao.findBy(Journalist.class, "yearObj", getOpenedYear().getYear() + "");
        List<JournalistDto> journalistDtos = new ArrayList<JournalistDto>();
        for (Journalist journalist : journalists) {
            journalistDtos.add(new JournalistDto(journalist));
        }
        return journalistDtos;
    }

    @Override
    public List<LobbyistDto> getAllLobbyistByOpenYear() {
        List<Lobbyist> lobbyists = genericDao.findBy(Lobbyist.class, "yearObj", getOpenedYear().getYear() + "");
        List<LobbyistDto> lobbyistDtos = new ArrayList<LobbyistDto>();
        for (Lobbyist lobbyist : lobbyists) {
            lobbyistDtos.add(new LobbyistDto(lobbyist));
        }
        return lobbyistDtos;
    }

    @Override
    public List<SenatorDto> getAllSenatorByOpenYear() {
        List<Senator> senators = genericDao.findBy(Senator.class, "yearObj", getOpenedYear().getYear().toString());
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
        for (Senator senator : senators) {
            senatorDtos.add(new SenatorDto(senator));
        }
        return senatorDtos;
    }

    @Override
    public List<DeputyDto> getAllDeputyByOpenYear() {
        List<Deputy> deputys = genericDao.findBy(Deputy.class, "yearObj", getOpenedYear().getYear().toString());
        List<DeputyDto> deputyDtos = new ArrayList<DeputyDto>();
        for (Deputy deputy : deputys) {
            deputyDtos.add(new DeputyDto(deputy));
        }
        return deputyDtos;
    }

    @Override
    public List<ParticipationDto> getAllSpecificParticipationByOpenedYearAndCommitte(CommitteDto committe, Class clazz) {
        List<ParticipationDto> participationDtos = new ArrayList<ParticipationDto>();
        Map<String, String> mapOfAttributs = new HashMap<String, String>();
        mapOfAttributs.put("committe", committe.getId().toString());
        mapOfAttributs.put("yearObj", getOpenedYear().getYear().toString());
        List<Participation> participations = genericDao.findByMultyWhere(clazz, mapOfAttributs);
        for (Participation participation : participations) {
            participationDtos.add(new ParticipationDto(participation));
        }
        return participationDtos;
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
        List<School> schools = genericDao.getAll(School.class);
        for (School school : schools) {
            schoolDtos.add(new SchoolDto(school));
        }
        return schoolDtos;
    }

    @Override
    public List<OrganizationDto> getAllJournalistOrganization() {
        List<OrganizationDto> organizationDtos = new ArrayList<OrganizationDto>();
        List<Organization> organizations = genericDao.findBy(Organization.class, "type", "1");
        for (Organization organization : organizations) {
            organizationDtos.add(new OrganizationDto(organization));
        }
        return organizationDtos;
    }

    @Override
    public List<OrganizationDto> getAllJournalistOrganizationByOpenedYear() {
        List<JournalistDto> journalists = getAllJournalistByOpenYear();
        List<OrganizationDto> organizations = new ArrayList<OrganizationDto>();
        for (JournalistDto journalist : journalists) {
            organizations.add(journalist.getOrganizationDto());
        }
        return organizations;
    }

    @Override
    public List<OrganizationDto> getAllFreeJournalistOrganizationByOpenedYear() {
        List<OrganizationDto> reservedOrganizations = getAllJournalistOrganizationByOpenedYear();
        List<OrganizationDto> allOrganizations = getAllJournalistOrganization();
        return new Intersection<OrganizationDto>().intersection(allOrganizations, reservedOrganizations);
    }

    @Override
    public List<OrganizationDto> getAllLobbyistOrganization() {
        List<OrganizationDto> organizationDtos = new ArrayList<OrganizationDto>();
        List<Organization> organizations = genericDao.findBy(Organization.class, "type", "2");
        for (Organization organization : organizations) {
            organizationDtos.add(new OrganizationDto(organization));
        }
        return organizationDtos;
    }

    @Override
    public List<OrganizationDto> getAllLobbyistOrganizationByOpenedYear() {
        List<LobbyistDto> lobbyists = getAllLobbyistByOpenYear();
        List<OrganizationDto> organizations = new ArrayList<OrganizationDto>();
        for (LobbyistDto lobbyist : lobbyists) {
            organizations.add(lobbyist.getOrganizationDto());
        }
        return organizations;
    }

    @Override
    public List<OrganizationDto> getAllFreeLobbyistOrganizationByOpenedYear() {
        List<OrganizationDto> reservedOrganizations = getAllLobbyistOrganizationByOpenedYear();
        List<OrganizationDto> allOrganizations = getAllLobbyistOrganization();
        return new Intersection<OrganizationDto>().intersection(allOrganizations, reservedOrganizations);
    }

    private ParticipationDto participationToDto(Participation participation) {
        if (participation instanceof Journalist) {
            return new JournalistDto((Journalist) participation);
        } else if (participation instanceof Lobbyist) {
            return new LobbyistDto((Lobbyist) participation);
        } else if (participation instanceof Senator) {
            return new SenatorDto((Senator) participation);
        } else {
            return new DeputyDto((Deputy) participation);
        }
    }

    /**
     * It needs redo!!
     *
     * @return list
     */
    @Override
    public List<CommitteDto> getAllCommitteForDeputyOrLobbyist() {
        List<CommitteDto> committeDtos = new ArrayList<CommitteDto>();
        List<Committe> committes = genericDao.getAll(Committe.class);
        for (Committe committe : committes) {
            if (!committe.getCommitteName().equals("Senát")) {
                committeDtos.add(new CommitteDto(committe));
            }
        }
        return committeDtos;
    }

    /**
     *
     * It needs redo!!
     *
     * @return list
     */
    @Override
    public List<CommitteDto> getAllCommitteForSenator() {
        List<CommitteDto> committeDtos = new ArrayList<CommitteDto>();
        List<Committe> committes = genericDao.getAll(Committe.class);
        for (Committe committe : committes) {
            if (committe.getCommitteName().equals("Senát")) {
                committeDtos.add(new CommitteDto(committe));
                return committeDtos;
            }
        }
        return committeDtos;
    }

    @Override
    public List<PoliticalPartyDto> getAllPoliticalParties() {
        List<PoliticalPartyDto> partyDtos = new ArrayList<PoliticalPartyDto>();
        List<PoliticalParty> parties = genericDao.getAll(PoliticalParty.class);
        for (PoliticalParty politicalParty : parties) {
            partyDtos.add(new PoliticalPartyDto(politicalParty));
        }
        return partyDtos;
    }

    @Override
    public List<FractionDto> getAllFractions() {
        List<FractionDto> fractionDtos = new ArrayList<FractionDto>();
        List<Fraction> fractions = genericDao.getAll(Fraction.class);
        for (Fraction fraction : fractions) {
            fractionDtos.add(new FractionDto(fraction));
        }
        return fractionDtos;
    }

    @Override
    public List<FractionDto> getAllFractionsForDem() {
        List<FractionDto> fractionDtos = new ArrayList<FractionDto>();
        List<Fraction> fractions = genericDao.findBy(Fraction.class, "partyid", "1");
        for (Fraction fraction : fractions) {
            fractionDtos.add(new FractionDto(fraction));
        }
        return fractionDtos;
    }

    @Override
    public List<FractionDto> getAllFractionsForRep() {
        List<FractionDto> fractionDtos = new ArrayList<FractionDto>();
        List<Fraction> fractions = genericDao.findBy(Fraction.class, "partyid", "2");
        for (Fraction fraction : fractions) {
            fractionDtos.add(new FractionDto(fraction));
        }
        return fractionDtos;
    }

    @Override
    public List<StateDto> getAllStates() {
        List<StateDto> stateDtos = new ArrayList<StateDto>();
        List<State> states = genericDao.getAll(State.class);
        for (State state : states) {
            stateDtos.add(new StateDto(state));
        }
        return stateDtos;
    }

    @Override
    public List<StateDto> getAllStatesInCommitteByOpenedYearAndDeputy(CommitteDto commite) {
        List<StateDto> stateDtos = new ArrayList<StateDto>();
        List<ParticipationDto> participationsWithStateInCommitte = getAllSpecificParticipationByOpenedYearAndCommitte(commite, Deputy.class);

        for (ParticipationDto participationDto : participationsWithStateInCommitte) {
            stateDtos.add(((DeputyDto) participationDto).getStateDto());
        }
        return stateDtos;
    }

    @Override
    public List<StateDto> getAllFreeStatesForCommitteByOpenedYearAndDeputy(CommitteDto commite) {
        List<StateDto> allStates = getAllStates();
        List<StateDto> reservedStates = getAllStatesInCommitteByOpenedYearAndDeputy(commite);
        return new Intersection<StateDto>().intersection(allStates, reservedStates);
    }

    @Override
    public List<StateDto> getAllStatesInCommitteByOpenedYearAndSenator(CommitteDto committe) {
        List<StateDto> stateDtos = new ArrayList<StateDto>();
        List<ParticipationDto> participationsWithStateInCommitte = getAllSpecificParticipationByOpenedYearAndCommitte(committe, Senator.class);

        for (ParticipationDto participationDto : participationsWithStateInCommitte) {
            stateDtos.add(((DeputyDto) participationDto).getStateDto());
        }
        return stateDtos;
    }

    @Override
    public List<StateDto> getAllFreeStatesForCommitteByOpenedYearAndSenator(CommitteDto committe) {
        List<StateDto> allStates = getAllStates();
        List<StateDto> reservedStates = getAllStatesInCommitteByOpenedYearAndSenator(committe);
        return new Intersection<StateDto>().intersection(allStates, reservedStates);
    }
    

}

/**
 * Helper for intersection of two lists.
 *
 * @author Veronika Maurerova
 * @param <E>
 */
class Intersection<E> {

    public List<E> intersection(List<E> firstList, List<E> secondList) {
        List<E> intersectionList = new ArrayList<E>();
        for (E objectFromFirst : firstList) {
            if (!secondList.contains(objectFromFirst)) {
                intersectionList.add(objectFromFirst);
            }
        }
        return intersectionList;
    }
}
