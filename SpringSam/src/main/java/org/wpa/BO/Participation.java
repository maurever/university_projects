package org.wpa.BO;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Účast.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Participation.findAll", query = "SELECT c FROM Participation c")
@Table(name = "Participations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PARTICIPATING_AS")
@IdClass(ParticipationId.class)
public abstract class Participation implements Serializable, BOWithFK {

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id")
    private Person participant;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "yearInt")
    private Year yearObj;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "schoolId")
    private School school;

    private boolean accepted;
    
    public Participation() {
    }

    public Participation(Person participant, Year yearObj, School school) {
        this.yearObj = yearObj;
        this.participant = participant;
        this.school = school;
        this.accepted = false;
    }

    public Year getYearObj() {
        return yearObj;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public boolean isAccepted() {
        return accepted;
    }
    
    public void setYearObj(Year yearObj) {
        this.yearObj = yearObj;
    }

    public Person getParticipant() {
        return participant;
    }

    public void setParticipant(Person participant) {
        this.participant = participant;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearObj != null ? yearObj.hashCode() : 0);
        hash += (participant != null ? participant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.getYearObj() == null && other.getYearObj() != null) || (this.getYearObj() != null && !this.getYearObj().equals(other.getYearObj()))) {
            return false;
        }
        if ((this.getParticipant() == null && other.getParticipant() != null) || (this.getParticipant() != null && !this.getParticipant().equals(other.getParticipant()))) {
            return false;
        }
        return true;
    }

    public String toTmpString() {
        return " email=" + participant.getEmail() + " year=" + yearObj.getYearInt();
    }

    @Override
    public Integer getYearId() {
        return yearObj.getYearInt();
    }

    @Override
    public Long getPersonId() {
        return participant.getId();
    }

}
