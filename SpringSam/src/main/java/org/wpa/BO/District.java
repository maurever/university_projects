package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Okrsek.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "District.findAll", query = "SELECT c FROM District c")
@Table(name = "Districts")
public class District implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String districtName;

    @OneToMany(targetEntity = Senator.class, mappedBy = "district", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Senator> senators;

    public District() {
    }

    public District(String districtName) {
        this.districtName = districtName;
    }

    public void setSenators(List<Senator> senators) {
        this.senators = senators;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Senator> getSenators() {
        return senators;
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
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.District[ id=" + id + " districtName=" + districtName + " ]";
    }

}
