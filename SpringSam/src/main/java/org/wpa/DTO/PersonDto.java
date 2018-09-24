package org.wpa.DTO;

import org.wpa.BO.Person;

/**
 *
 * @author Veronika Maurerova
 */
public class PersonDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private String email;

    public PersonDto() {
    }

    public PersonDto(Long id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public <E extends Person> PersonDto(E person) {
        super(person.getId());
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
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

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isManager(){
        return false; 
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.PersonDto[" + toTmpString() + " ]";
    }

    public String toTmpString() {
        return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email;
    }

}
