package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Entity, czech equivalent is Skola.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "School.findAll", query = "SELECT c FROM School c")
@Table(name = "Schools")
public class School implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String schoolName;
    @Column(length = 50, nullable = false)
    private String street;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(nullable = false)
    private String streetCode;
    @Column(nullable = false)
    private int postalCode;
    @OneToMany(targetEntity = Participation.class, mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Participation> paticipations;

    public School() {
    }

    public School(String schoolName, String street, String city, String streetCode, int postalCode) {
        this.schoolName = schoolName;
        this.street = street;
        this.city = city;
        this.streetCode = streetCode;
        this.postalCode = postalCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setPaticipations(List<Participation> paticipations) {
        this.paticipations = paticipations;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public List<Participation> getPaticipations() {
        return paticipations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
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
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DAOEntity.School[ id=" + id + " schoolName=" + schoolName
                + " street=" + street + " streetCode=" + streetCode + " city=" + city + " postalCode=" + postalCode + " ]";
    }

}
