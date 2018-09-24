package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Stat.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "State.findAll", query = "SELECT c FROM State c")
@Table(name = "States")
public class State implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String stateName;

    @OneToMany(targetEntity = Senator.class, mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Senator> senators;

    @OneToMany(targetEntity = Deputy.class, mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Deputy> deputies;

    @OneToMany(targetEntity = Management.class, mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Management> managements;

    public State() {
    }

    public State(String stateName) {
        this.stateName = stateName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<Management> getManagements() {
        return managements;
    }


    public void setManagements(List<Management> managements) {
        this.managements = managements;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getStateName() {
        return stateName;
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
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.State[ id=" + id + " stateName=" + stateName + " ]";
    }

}
