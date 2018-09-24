package org.wpa.DTO;

import java.io.Serializable;
import org.wpa.BO.Person;
import org.wpa.BO.Year;

/**
 *
 * @author Veronika Maurerova
 */
public abstract class AbstractDtoCompositeKey implements Serializable{

    YearDto yearDto;
    PersonDto personDTO;

    public AbstractDtoCompositeKey() {
    }

    public AbstractDtoCompositeKey(Year year, Person person) {
        this.yearDto = new YearDto(year);
        this.personDTO = new PersonDto(person);
    }

    public YearDto getYearDto() {
        return yearDto;
    }

    public void setYearDto(YearDto yearDto) {
        this.yearDto = yearDto;
    }

    public PersonDto getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDto personDTO) {
        this.personDTO = personDTO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += yearDto.hashCode();
        hash = personDTO.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDtoCompositeKey other = (AbstractDtoCompositeKey) obj;
        if (this.yearDto != other.yearDto && (this.yearDto == null || !this.yearDto.equals(other.yearDto))) {
            return false;
        }
        if (this.personDTO != other.personDTO && (this.personDTO == null || !this.personDTO.equals(other.personDTO))) {
            return false;
        }
        return true;
    }

    @Override
    public abstract String toString();

    public String toTmpString() {
        return " yearDto=" + yearDto + ", personDto=" + personDTO;
    }
}
