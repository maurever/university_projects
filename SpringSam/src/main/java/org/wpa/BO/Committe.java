package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Vybor. 
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Committe.findAll", query = "SELECT c FROM Committe c")
@Table(name = "Committes")
public class Committe implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String committeName;

    @OneToMany(targetEntity = Lobbyist.class, mappedBy = "committe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Lobbyist> lobbyists;

    @OneToMany(targetEntity = Senator.class, mappedBy = "committe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Senator> senators;

    @OneToMany(targetEntity = Deputy.class, mappedBy = "committe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Deputy> deputies;

    @OneToMany(targetEntity = Management.class, mappedBy = "committe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Management> managements;

    public Committe(String committeName) {
        this.committeName = committeName;
    }

    public Committe() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLobbyists(List<Lobbyist> lobbyists) {
        this.lobbyists = lobbyists;
    }

    public void setSenators(List<Senator> senators) {
        this.senators = senators;
    }

    public void setCommitteName(String committeName) {
        this.committeName = committeName;
    }

    public void setDeputies(List<Deputy> deputies) {
        this.deputies = deputies;
    }

    public void setManagements(List<Management> managements) {
        this.managements = managements;
    }

    public void setCommiteName(String commiteName) {
        this.committeName = commiteName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getCommitteName() {
        return committeName;
    }

    public List<Management> getManagements() {
        return managements;
    }

    public List<Lobbyist> getLobbyists() {
        return lobbyists;
    }

    public List<Senator> getSenators() {
        return senators;
    }

    public List<Deputy> getDeputies() {
        return deputies;
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
        if (!(object instanceof Committe)) {
            return false;
        }
        Committe other = (Committe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Committe[ id=" + id + " commiteName=" + committeName + " ]";
    }

}
