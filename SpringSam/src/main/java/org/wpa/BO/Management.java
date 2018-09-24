package org.wpa.BO;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Organizator.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Management.findAll", query = "SELECT c FROM Management c")
@Table(name = "Managements")
@IdClass(ManagementId.class)
public class Management implements Serializable, BOWithFK {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @Id
    @JoinColumn(name = "yearInt", referencedColumnName = "yearInt")
    private Year yearObj;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @Id
    @JoinColumn(name = "managerId")
    private Manager manager;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "partyId", nullable = false)
    private PoliticalParty politicalParty;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "stateId", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "committeId", nullable = false)
    private Committe committe;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rankId", nullable = false)
    private Rank rank;

    public Management() {
    }

    public Management(Manager manager, Year yearObj, PoliticalParty politicalParty, State state, Committe committe, Rank rank) {
        this.yearObj = yearObj;
        this.manager = manager;
        this.politicalParty = politicalParty;
        this.state = state;
        this.committe = committe;
        this.rank = rank;
    }

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (yearObj != null ? yearObj.hashCode() : 0);
        hash += (manager != null ? manager.hashCode() : 0);
        return hash;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Committe getCommitte() {
        return committe;
    }

    public void setCommitte(Committe committe) {
        this.committe = committe;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Year getYearObj() {
        return yearObj;
    }

    public void setYearObj(Year yearObj) {
        this.yearObj = yearObj;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Management)) {
            return false;
        }
        Management other = (Management) object;
        if ((this.getYearObj() == null && other.getYearObj() != null) || (this.getYearObj() != null && !this.getYearObj().equals(other.getYearObj()))) {
            return false;
        }
        if ((this.getManager() == null && other.getManager() != null) || (this.getManager() != null && !this.getManager().equals(other.getManager()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Management[ email=" + manager.getEmail() + " year=" + yearObj.getYearInt() + " politicalParty=" + politicalParty + " state=" + state + " committe=" + committe + " rank" + rank + " ]";
    }

    @Override
    public Integer getYearId() {
        return this.getYearObj().getYearInt();
    }

    @Override
    public Long getPersonId() {
        return manager.getId();
    }

}
