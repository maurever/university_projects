package org.wpa.BO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.wpa.provider.SHA1Provider;

/**
 * Entity, czech equivalent is Osoba.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
@Entity
@NamedQuery(name = "Person.findAll", query = "SELECT c FROM Person c")
@Table(name = "Persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE")

public class Person implements Serializable, BOWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String passHash;
    @Column(length = 50, nullable = false)
    private String salt;
    @OneToMany(targetEntity = Participation.class, mappedBy = "participant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Participation> participing;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String passWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassHash(passWord);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String password) {
        this.salt = SHA1Provider.computeHash(System.nanoTime() + "");
        this.passHash = SHA1Provider.computeHash(password + salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Participation> getParticiping() {
        return participing;
    }

    public void setParticiping(List<Participation> participing) {
        this.participing = participing;
    }

    public boolean hasPassword(String password) {
        return SHA1Provider.computeHash(password + salt).equals(this.passHash);
    }

    public boolean isManager() {
        return false;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    public String totmpString() {
        return "id=" + id + " email=" + email + " fistName=" + firstName + " laseName=" + lastName;
    }

}
