package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Organizace.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Organization.findAll", query = "SELECT c FROM Organization c")
@Table(name = "Organizations")
public class Organization implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String organizationName;
    private String description;

    @OneToMany(targetEntity = Journalist.class, mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Journalist> journalists;

    @OneToMany(targetEntity = Lobbyist.class, mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Lobbyist> lobbyists;

    //type = 1 journalist, type = 2 lobbyist
    private Integer type;

    public Organization() {
    }

    public Organization(String organizationName, String description, Integer type) {
        this.organizationName = organizationName;
        this.description = description;
        this.type = type;
    }

    public void setJournalists(List<Journalist> journalists) {
        this.journalists = journalists;
    }

    public void setLobbyists(List<Lobbyist> lobbyists) {
        this.lobbyists = lobbyists;
    }

    public List<Journalist> getJournalists() {
        return journalists;
    }

    public List<Lobbyist> getLobbyists() {
        return lobbyists;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.Organization[ id=" + id + " organizationName=" + organizationName + " description=" + description + " ]";
    }

}
