package org.wpa.service.Management;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wpa.BO.Committe;
import org.wpa.BO.Management;
import org.wpa.BO.ManagementId;
import org.wpa.BO.Manager;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.Rank;
import org.wpa.BO.State;
import org.wpa.BO.Year;
import org.wpa.DTO.CommitteDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.PersonDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.RankDto;
import org.wpa.DTO.StateDto;
import org.wpa.DTO.YearDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Service for gettion all information about Management.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("managementServiceImp")
@Scope(value = "singleton")
public class ManagementServiceImpl extends AbstractDataAccessService implements ManagementService {

    @Override
    public ManagementDto getManagement(Integer year, Long managerId) {
        ManagementId key = new ManagementId(year, managerId);
        Management management = genericDao.getByKey(Management.class, key);
        if (management != null) {
            return new ManagementDto(management);
        }
        return null;
    }

    @Override
    public ManagementDto saveOrUpdateManagement(ManagementDto managementDto) {
        Management returnedManagement = genericDao.mergeEntity(getUpdatedManagementFromDTO(managementDto));
        return new ManagementDto(returnedManagement);
    }

    private Management getUpdatedManagementFromDTO(ManagementDto managementDto) {
        Management management = genericDao.getByKey(Management.class, new ManagementId(managementDto.getYearDto().getYear(), managementDto.getPersonDTO().getId()));
        if (management == null) {
            management = new Management();
            management.setYearObj(getUpdatedYearFromDto(managementDto.getYearDto()));
            management.setManager(getManagerFromDto(managementDto.getPersonDTO()));
        }
        management.setPoliticalParty(getUpdatedPoliticalParty(managementDto.getPoliticalParty()));
        management.setCommitte(getUpdatedCommitteFromDto(managementDto.getCommitteDto()));
        management.setRank(getUpdatedRank(managementDto.getRankDto()));
        management.setState(getUpdatedState(managementDto.getStateDto()));

        return management;
    }

    private PoliticalParty getUpdatedPoliticalParty(PoliticalPartyDto partyDto) {
        PoliticalParty party = genericDao.getByKey(PoliticalParty.class, partyDto.getId());
        if (party == null) {
            party = new PoliticalParty(partyDto.getPoliticalartyName());
        }
        return party;
    }

    private State getUpdatedState(StateDto stateDto) {
        State state = genericDao.getByKey(State.class, stateDto.getId());
        if (state == null) {
            state = new State(stateDto.getStateName());
        }
        return state;
    }

    private Rank getUpdatedRank(RankDto rankDto) {
        Rank rank = genericDao.getByKey(Rank.class, rankDto.getId());
        if (rank == null) {
            rank = new Rank(rankDto.getRankName());
        }
        return rank;
    }

    private Committe getUpdatedCommitteFromDto(CommitteDto committeDto) {
        Committe committe = genericDao.getByKey(Committe.class, committeDto.getId());
        if (committe == null) {
            committe = new Committe(committeDto.getCommitteName());
        }
        return committe;
    }

    private Year getUpdatedYearFromDto(YearDto yearDto) {
        Year year = genericDao.getByKey(Year.class, yearDto.getYear());
        if (year == null) {
            year = new Year(yearDto.getYear(), true);
        }
        return year;
    }

    private Manager getManagerFromDto(PersonDto personDto) {
        Manager manager = genericDao.getByKey(Manager.class, personDto.getId());
        if (manager == null) {
            throw new IllegalArgumentException("Atempt to update Management without corect ManagerDto in ManagementDto");
        }
        return manager;
    }
}
