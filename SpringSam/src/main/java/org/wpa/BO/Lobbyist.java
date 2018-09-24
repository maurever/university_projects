package org.wpa.BO;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Lobbista.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Lobbyist.findAll", query = "SELECT c FROM Lobbyist c")
@Table(name = "Lobbyists")
@DiscriminatorValue(value = "LOBBYIST")
public class Lobbyist extends Participation implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "organizationId")
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "committeId")
    private Committe committe;

    public Lobbyist() {
    }

    public Lobbyist(Person participant, Year yearObj, School school, Organization organization, Committe committe) {
        super(participant, yearObj, school);
        this.organization = organization;
        this.committe = committe;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Committe getCommitte() {
        return committe;
    }

    public void setCommitte(Committe committe) {
        this.committe = committe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lobbyist)) {
            return false;
        }

        return super.equals(object);
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Lobbyist[" + toTmpString() + " organization=" + organization + " committe=" + committe + " ]";
    }

}
