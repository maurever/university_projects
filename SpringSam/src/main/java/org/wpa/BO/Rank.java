package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Hodnoceni.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Rank.findAll", query = "SELECT c FROM Rank c")
@Table(name = "Ranks")
public class Rank implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50,unique = true, nullable = false)
    private String rankName;

    @OneToMany(targetEntity = Management.class, mappedBy = "rank", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Management> managements;

    public Rank() {
    }

    public Rank(String rankName) {
        this.rankName = rankName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManagements(List<Management> managements) {
        this.managements = managements;
    }

    public String getRankName() {
        return rankName;
    }

    public List<Management> getManagements() {
        return managements;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
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
        if (!(object instanceof Rank)) {
            return false;
        }
        Rank other = (Rank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Rank[ id=" + id + " rankName=" + rankName + " ]";
    }

}
