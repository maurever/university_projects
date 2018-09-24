package org.wpa.Participation;

import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
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

/**
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> &    Veronika Maurerova
 <veronika at maurerova.cz>
 */
public interface ParticipationService {

    @Transactional(readOnly = false)
    public Long addParticipation(Long participantId, Integer year, String role, Map<String, Long> map);

    @Transactional(readOnly = true)
    public ParticipationDto getParticipation(Integer year, Long participantId);

    @Transactional(readOnly = false)
    public Long addLobbyist(Long participantId, Long schoolId, Long organizationId, Long committeId, int yearId);

    @Transactional(readOnly = false)
    public Long addJournalist(Long participantId, Long schoolId, Long organizationId, int yearId);

    @Transactional(readOnly = false)
    public Long addSenator(Long participantId, Long schoolId, Long committeId, Long fractionId, Long stateId, Long districtId, int yearId);

    @Transactional(readOnly = false)
    public Long addDeputy(Long participantId, Long schoolId, Long committeId, Long fractionId, Long stateId, int yearId);

    @Transactional(readOnly = true)
    public YearDto getOpenedYear();

    @Transactional(readOnly = false)
    public List<ParticipationDto> getAllParticipation();

    @Transactional(readOnly = true)
    public List<JournalistDto> getAllJournalistByOpenYear();

    @Transactional(readOnly = true)
    public List<LobbyistDto> getAllLobbyistByOpenYear();

    @Transactional(readOnly = true)
    public List<SenatorDto> getAllSenatorByOpenYear();

    @Transactional(readOnly = true)
    public List<DeputyDto> getAllDeputyByOpenYear();

    @Transactional(readOnly = true)
    public List<ParticipationDto> getAllSpecificParticipationByOpenedYearAndCommitte(CommitteDto committe, Class clazz);

    @Transactional(readOnly = true)
    public List<SchoolDto> getAllSchools();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllJournalistOrganization();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllLobbyistOrganization();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllJournalistOrganizationByOpenedYear();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllLobbyistOrganizationByOpenedYear();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllFreeJournalistOrganizationByOpenedYear();

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllFreeLobbyistOrganizationByOpenedYear();

    @Transactional(readOnly = true)
    public List<CommitteDto> getAllCommitteForDeputyOrLobbyist();

    @Transactional(readOnly = true)
    public List<CommitteDto> getAllCommitteForSenator();

    @Transactional(readOnly = true)
    public List<PoliticalPartyDto> getAllPoliticalParties();

    @Transactional(readOnly = true)
    public List<FractionDto> getAllFractions();

    @Transactional(readOnly = true)
    public List<FractionDto> getAllFractionsForDem();

    @Transactional(readOnly = true)
    public List<FractionDto> getAllFractionsForRep();

    @Transactional(readOnly = true)
    public List<StateDto> getAllStates();

    @Transactional(readOnly = true)
    public List<StateDto> getAllStatesInCommitteByOpenedYearAndDeputy(CommitteDto committe);

    @Transactional(readOnly = true)
    public List<StateDto> getAllFreeStatesForCommitteByOpenedYearAndDeputy(CommitteDto commite);

    @Transactional(readOnly = true)
    public List<StateDto> getAllStatesInCommitteByOpenedYearAndSenator(CommitteDto committe);

    @Transactional(readOnly = true)
    public List<StateDto> getAllFreeStatesForCommitteByOpenedYearAndSenator(CommitteDto commite);
}
