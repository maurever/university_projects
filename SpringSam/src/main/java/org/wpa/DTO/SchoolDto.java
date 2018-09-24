package org.wpa.DTO;

import org.wpa.BO.School;

/**
 *
 * @author Veronika Maurerova
 */
public class SchoolDto extends AbstractDto {

     String schoolName;
     String street;
     String city;
     String streetCode;
     int postalCode;

    public SchoolDto() {
    }

    public SchoolDto(Long id, String schoolName, String street, String city, String streetCode, int postalCode) {
        super(id);
        this.schoolName = schoolName;
        this.street = street;
        this.city = city;
        this.streetCode = streetCode;
        this.postalCode = postalCode;

    }

    public SchoolDto(School school) {
        super(school.getId());
        this.schoolName = school.getSchoolName();
        this.street = school.getStreet();
        this.city = school.getCity();
        this.streetCode = school.getStreetCode();
        this.postalCode = school.getPostalCode();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.SchoolDto[ id=" + id + ", schoolName=" + schoolName + ", street=" + street + ", streetCode=" + streetCode + ", city=" + city + ", postalCode=" + postalCode + " ]";
    }

}
