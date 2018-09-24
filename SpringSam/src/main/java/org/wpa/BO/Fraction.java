package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Fraction.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@Table(name = "Fractions")
@NamedQuery(name = "Fraction.findAll", query = "SELECT c FROM Fraction c")
public class Fraction implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String fractionName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "partyId", nullable = false)
    private PoliticalParty politicalParty;

    @OneToMany(targetEntity = Senator.class, mappedBy = "fraction", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Senator> senators;
    
    @OneToMany(targetEntity = Deputy.class, mappedBy = "fraction", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Deputy> deputies;

    public Fraction() {
    }

    public Fraction(String fractionName, PoliticalParty politicalParty) {
        this.fractionName = fractionName;
        this.politicalParty = politicalParty;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }
    
    public String getFractionName() {
        return fractionName;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFractionName(String fractionName) {
        this.fractionName = fractionName;
    }

    public List<Senator> getSenators() {
        return senators;
    }

    public void setSenators(List<Senator> senators) {
        this.senators = senators;
    }

    public List<Deputy> getDeputies() {
        return deputies;
    }

    public void setDeputies(List<Deputy> deputies) {
        this.deputies = deputies;
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
        if (!(object instanceof Fraction)) {
            return false;
        }
        Fraction other = (Fraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Fraction[ id=" + id + " ]";
    }

}
