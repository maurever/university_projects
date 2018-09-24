package org.wpa.DTO;

import org.wpa.BO.Lobbyist;

/**
 *
 * @author Veronika Maurerova
 */
public class LobbyistDto extends ParticipationDto {

    private final String ROLE = "Lobbista";
    OrganizationDto organizationDto;
    CommitteDto committeDto;

    public LobbyistDto() {
    }

    public <E extends Lobbyist> LobbyistDto(E lobbyist) {
        super(lobbyist);
        this.organizationDto = new OrganizationDto(lobbyist.getOrganization());
        this.committeDto = new CommitteDto(lobbyist.getCommitte());
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

    public CommitteDto getCommitteDto() {
        return committeDto;
    }

    public void setCommitteDto(CommitteDto committeDto) {
        this.committeDto = committeDto;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.LobbyistDto[" + toTmpString() + " ]";
    }

    @Override
    public String toTmpString() {
        return super.toTmpString() + ", organizationDto=" + organizationDto + ", committeDto=" + committeDto;
    }

}
