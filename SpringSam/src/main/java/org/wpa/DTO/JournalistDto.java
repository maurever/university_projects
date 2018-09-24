package org.wpa.DTO;

import org.wpa.BO.Journalist;

/**
 *
 * @author Veronika Maurerova
 */
public class JournalistDto extends ParticipationDto {

    private final String ROLE = "Novinář";
    OrganizationDto organizationDto;

    public JournalistDto() {
    }

    public <E extends Journalist> JournalistDto(E journalist) {
        super(journalist);
        this.organizationDto = new OrganizationDto(journalist.getOrganization());
    }

    @Override
    public String getROLE() {
        return ROLE;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.JournalistDto[" + toTmpString() + " ]";
    }

    @Override
    public String toTmpString() {
        return super.toTmpString() + ", organizationDto=" + organizationDto;
    }

}
