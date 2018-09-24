package org.wpa.DTO;

import org.wpa.BO.Management;

/**
 *
 * @author Veronika Maurerova
 */
public class ManagementDto extends AbstractDtoCompositeKey {

    PoliticalPartyDto politicalParty;
    StateDto stateDto;
    CommitteDto committeDto;
    RankDto rankDto;

    public ManagementDto() {
    }

    public <E extends Management> ManagementDto(E management) {
        super(management.getYearObj(), management.getManager());
        this.politicalParty = new PoliticalPartyDto(management.getPoliticalParty());
        this.stateDto = new StateDto(management.getState());
        this.committeDto = new CommitteDto(management.getCommitte());
        this.rankDto = new RankDto(management.getRank());
    }

    public PoliticalPartyDto getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalPartyDto politicalParty) {
        this.politicalParty = politicalParty;
    }

    public StateDto getStateDto() {
        return stateDto;
    }

    public void setStateDto(StateDto stateDto) {
        this.stateDto = stateDto;
    }

    public CommitteDto getCommitteDto() {
        return committeDto;
    }

    public void setCommitteDto(CommitteDto committeDto) {
        this.committeDto = committeDto;
    }

    public RankDto getRankDto() {
        return rankDto;
    }

    public void setRankDto(RankDto rankDto) {
        this.rankDto = rankDto;
    }

    @Override
    public String toString() {
        return "ManagementDto{" + super.toTmpString() + ", politicalParty=" + politicalParty + ", stateDto=" + stateDto + ", committeDto=" + committeDto + ", rankDto=" + rankDto + '}';
    }

}
