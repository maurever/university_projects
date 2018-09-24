package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Organizator.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Manager.findAll", query = "SELECT c FROM Manager c")
@Table(name = "Managers")
@DiscriminatorValue(value = "MANAGER")
public class Manager extends Person implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Competency.class, cascade = {CascadeType.REFRESH})
    private List<Competency> competencies;

    @OneToMany(targetEntity = Management.class, mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "yearObj")
    private List<Management> managing;

    public Manager() {
    }

    public Manager(String firstName, String lastName, String Email, String passWord, List<Competency> competencies) {
        super(firstName, lastName, Email, passWord);
        this.competencies = competencies;
    }

    public void setManaging(List<Management> managing) {
        this.managing = managing;
    }

    public List<Competency> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
    }

    public List<Management> getManaging() {
        return managing;
    }

    @Override
    public boolean isManager() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Manager[" + totmpString() + " competencies={" + competencies + "} ]";
    }

}
