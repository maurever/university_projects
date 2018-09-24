package org.wpa.DTO;

import org.wpa.BO.Participation;

/**
 *
 * @author Veronika Maurerova
 */
public class ParticipationDto extends AbstractDtoCompositeKey {

    private SchoolDto schoolDto;
    private String ROLE = "";

    public ParticipationDto() {
    }

    public <E extends Participation> ParticipationDto(E participation) {
        super(participation.getYearObj(), participation.getParticipant());
        this.schoolDto = new SchoolDto(participation.getSchool());
    }

    public SchoolDto getSchoolDto() {
        return schoolDto;
    }

    public void setSchoolDto(SchoolDto schoolDto) {
        this.schoolDto = schoolDto;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
    

    @Override
    public String toString() {
        return "org.wpa.DTO.ParticipationDto[" + toTmpString() + " ]";
    }

    @Override
    public String toTmpString() {
        return super.toTmpString() + ",  schoolId=" + schoolDto;
    }
}
