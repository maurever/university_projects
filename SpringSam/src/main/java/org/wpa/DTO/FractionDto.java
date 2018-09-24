package org.wpa.DTO;

import org.wpa.BO.Fraction;

/**
 *
 * @author Veronika Maurerova
 */
public class FractionDto extends AbstractDto {

    private String fractionName;
    PoliticalPartyDto politicalPartyDto;

    public FractionDto() {
    }

    public FractionDto(Long id, String fractionName, Long politicalParty, String politicalPartyName) {
        super(id);
        this.fractionName = fractionName;
        politicalPartyDto = new PoliticalPartyDto(politicalParty, politicalPartyName);
    }

    public FractionDto(Fraction fraction) {
        super(fraction.getId());
        this.fractionName = fraction.getFractionName();
        this.politicalPartyDto = new PoliticalPartyDto(fraction.getPoliticalParty());
    }

    public String getFractionName() {
        return fractionName;
    }

    public void setFractionName(String fractionName) {
        this.fractionName = fractionName;
    }

    public PoliticalPartyDto getPoliticalPartyDto() {
        return politicalPartyDto;
    }

    public void setPoliticalPartyDto(PoliticalPartyDto politicalPartyDto) {
        this.politicalPartyDto = politicalPartyDto;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.FractionDto[ id=" + id + ", fractionName=" + fractionName + ", politicalPartyDto=" + politicalPartyDto + " ]";
    }

}
