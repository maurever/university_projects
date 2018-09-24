package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Rocnik.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Year.findAll", query = "SELECT c FROM Year c"),
    @NamedQuery(name = "Year.findOpenedYear", query = "SELECT c FROM Year c WHERE c.opened=true")
})
@Table(name = "Years")
public class Year implements Serializable {

    @Id
    private Integer yearInt;

    private boolean opened;

    @OneToMany(targetEntity = Participation.class, mappedBy = "yearObj", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Participation> participing;

    @OneToMany(targetEntity = Management.class, mappedBy = "yearObj", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Management> managing;

    public Year() {
    }

    public Year(Integer year, boolean open) {
        this.yearInt = year;
        this.opened = open;
    }

    public List<Participation> getParticiping() {
        return participing;
    }

    public Integer getYearInt() {
        return yearInt;
    }

    public void setYearInt(Integer year) {
        this.yearInt = year;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setParticiping(List<Participation> participing) {
        this.participing = participing;
    }

    public void setManaging(List<Management> managing) {
        this.managing = managing;
    }

    public void setOpened(boolean open) {
        this.opened = open;
    }

    public List<Management> getManaging() {
        return managing;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearInt != null ? yearInt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Year)) {
            return false;
        }
        Year other = (Year) object;
        if ((this.yearInt == null && other.yearInt != null) || (this.yearInt != null && !this.yearInt.equals(other.getYearInt()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Year[ year=" + yearInt + " opened=" + opened + " ]";
    }

}
