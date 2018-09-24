package org.wpa.DTO;

import org.wpa.BO.Senator;

/**
 *
 * @author Veronika Maurerova
 */
public class SenatorDto extends ParticipationDto {

    private final String ROLE = "Senátor";
    CommitteDto commiteDto;
    FractionDto fractionDto;
    StateDto stateDto;
    DistrictDto districtDto;

    public SenatorDto() {
    }

    public <E extends Senator> SenatorDto(E senator) {
        super(senator);
        this.districtDto = new DistrictDto(senator.getDistrict());
        this.commiteDto = new CommitteDto(senator.getCommitte());
        this.fractionDto = new FractionDto(senator.getFraction());
        this.stateDto = new StateDto(senator.getState());
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

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    @Override
    public String getROLE() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.SenatorDto[" + toTmpString() + " ]";
    }

    @Override
    public String toTmpString() {
        return super.toTmpString() + ", districDto=" + districtDto;
    }

}
