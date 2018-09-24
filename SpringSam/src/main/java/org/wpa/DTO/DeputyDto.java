package org.wpa.DTO;

import org.wpa.BO.Deputy;

/**
 *
 * @author Veronika Maurerova
 */
public class DeputyDto extends ParticipationDto {

    private final String ROLE = "Poslanec";
    CommitteDto commiteDto;
    FractionDto fractionDto;
    StateDto stateDto;

    @Override
    public String getROLE() {
        return ROLE;
    }

    public <E extends Deputy> DeputyDto(E deputy) {
        super(deputy);
        this.commiteDto = new CommitteDto(deputy.getCommitte());
        this.fractionDto = new FractionDto(deputy.getFraction());
        this. stateDto = new StateDto(deputy.getState());
    }

    public CommitteDto getCommiteDto() {
        return commiteDto;
    }

    public void setCommiteDto(CommitteDto commiteDto) {
        this.commiteDto = commiteDto;
    }

    public FractionDto getFractionDto() {
        return fractionDto;
    }

    public void setFractionDto(FractionDto fractionDto) {
        this.fractionDto = fractionDto;
    }

    public StateDto getStateDto() {
        return stateDto;
    }

    public void setStateDto(StateDto stateDto) {
        this.stateDto = stateDto;
    }
    
    

    @Override
    public String toString() {
        return "org.wpa.DTO.DeputyDto[" + toTmpString() + " ]";
    }

    @Override
    public String toTmpString() {
        return super.toTmpString();
    }

}
