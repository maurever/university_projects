package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Politicka strana.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "PoliticalParties.findAll", query = "SELECT c FROM PoliticalParty c")
@Table(name = "PoliticalParties")
public class PoliticalParty implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String politicalartyName;

    @OneToMany(targetEntity = Fraction.class, mappedBy = "politicalParty", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Fraction> fractions;

    @OneToMany(targetEntity = Management.class, mappedBy = "politicalParty", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Management> managements;

    public PoliticalParty() {
    }

    public PoliticalParty(String politicalartyName) {
        this.politicalartyName = politicalartyName;
    }

    public List<Management> getManagements() {
        return managements;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public void setManagements(List<Management> managements) {
        this.managements = managements;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getPoliticalartyName() {
        return politicalartyName;
    }

    public List<Fraction> getFractions() {
        return fractions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPoliticalartyName(String politicalartyName) {
        this.politicalartyName = politicalartyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoliticalParty)) {
            return false;
        }
        PoliticalParty other = (PoliticalParty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.PoliticalParty[ id=" + id + " politicalPartyName=" + politicalartyName + " ]";
    }

}
