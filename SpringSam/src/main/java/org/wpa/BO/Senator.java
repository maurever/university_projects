package org.wpa.BO;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Senator.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Senator.findAll", query = "SELECT c FROM Senator c")
@Table(name = "Senators")
@DiscriminatorValue(value = "SENATOR")
public class Senator extends Participation implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "committeId")
    private Committe committe;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fractionId")
    private Fraction fraction;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "stateId")
    private State state;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "districtId")
    private District district;

    public Senator() {
    }

    public Senator(Person participant, Year yearObj, School school, Committe committe, Fraction fraction, State state, District district) {
        super(participant, yearObj, school);
        this.committe = committe;
        this.fraction = fraction;
        this.state = state;
        this.district = district;
    }

    public Committe getCommitte() {
        return committe;
    }

    public void setCommitte(Committe committe) {
        this.committe = committe;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Participation)) {
            return false;
        }
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Deputy[" + toTmpString() + " districtName=" + district.getDistrictName() + " ]";
    }
}
