package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Opravneni.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Competency.findAll", query = "SELECT c FROM Competency c")
public class Competency implements Serializable, BOWithID {

    @Id
    private Long id;
    private String name;
    private String description;
    @ManyToMany(targetEntity = Manager.class, mappedBy = "competencies", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @OrderBy("email, lastName, firstName")
    List<Manager> managers;

    public Competency() {
    }

    public Competency(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrition() {
        return description;
    }

    public void setDescrition(String descrition) {
        this.description = descrition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Competency)) {
            return false;
        }
        Competency other = (Competency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Competency[ id=" + id + " name=" + name + " description=" + description + " ]";
    }

}
