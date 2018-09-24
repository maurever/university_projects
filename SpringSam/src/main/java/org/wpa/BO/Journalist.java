package org.wpa.BO;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Novinar.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Journalist.findAll", query = "SELECT c FROM Journalist c")
@Table(name = "Journalists")
@DiscriminatorValue(value = "JOURNALIST")
public class Journalist extends Participation implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "organizationId")
    private Organization organization;

    public Journalist() {
    }

    public Journalist(Person participant, Year yearObj, School school, Organization organization) {
        super(participant, yearObj, school);
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
        if (!(object instanceof Journalist)) {
            return false;
        }

        return super.equals(object);
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Journalist[" + toTmpString() + " organizationName=" + organization.getOrganizationName() + " ]";
    }

}
