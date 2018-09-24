package org.wpa.DTO;

import org.wpa.BO.PoliticalParty;

/**
 *
 * @author Veronika Maurerova
 */
public class PoliticalPartyDto extends AbstractDto {

    private String politicalartyName;

    public PoliticalPartyDto() {
    }

    public PoliticalPartyDto(Long id, String politicalartyName) {
        super(id);
        this.politicalartyName = politicalartyName;
    }

    public PoliticalPartyDto(PoliticalParty party) {
        super(party.getId());
        this.politicalartyName = party.getPoliticalartyName();
    }

    public String getPoliticalartyName() {
        return politicalartyName;
    }

    public void setPoliticalartyName(String politicalartyName) {
        this.politicalartyName = politicalartyName;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.PoliticalPartyDto[ id=" + id + ", politicalartyName=" + politicalartyName + " ]";
    }

}
