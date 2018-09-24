package org.wpa.service.persons;

import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wpa.BO.Competency;
import org.wpa.BO.Manager;
import org.wpa.BO.Participation;
import org.wpa.BO.Person;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.PersonDto;
import org.wpa.provider.SHA1Provider;

/**
 * Service for getting all information about Person.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("personServiceImpl")
@Scope(value = "singleton")
public class PersonServiceImpl extends AbstractDataAccessService implements PersonService {

    @Override
    public PersonDto getPersonById(Long id) {
        Person person = genericDao.getByKey(Person.class, id);
        if (person == null) {
            return null;
        }
        return new PersonDto(person);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = genericDao.getAll(Person.class);
        List<PersonDto> dtos = new ArrayList<PersonDto>();
        for (Person p : persons) {
            dtos.add(new PersonDto(p));
        }
        return dtos;
    }

    @Override
    public List<PersonDto> getAllPersons(String orderBy, boolean ascending) {
        List<Person> persons = genericDao.getAll(Person.class, orderBy, ascending);
        List<PersonDto> dtos = new ArrayList<PersonDto>();
        for (Person p : persons) {
            dtos.add(new PersonDto(p));
        }
        return dtos;
    }

    /**
     *
     * @param personId
     * @return HashMap<Long, CompetencyDto> of users competencies, null if doesn
     * have any.
     */
    @Override
    public HashMap<Long, CompetencyDto> getCompetencies(Long personId) {
        Manager manager;
        HashMap<Long, CompetencyDto> competencyDtos = null;
        manager = genericDao.getByKey(Manager.class, personId);
        if (manager != null) {
            List<Competency> competencies = manager.getCompetencies();
            competencyDtos = new HashMap<Long, CompetencyDto>();
            for (Competency c : competencies) {
                competencyDtos.put(c.getId(), new CompetencyDto(c));
            }
        }
        return competencyDtos;
    }

    @Override
    public void deletePerson(Long id) {
        genericDao.removeById(id, Person.class);
    }

    @Override
    public Long alterPerson(Long id, String email, String firstName, String lastName, String password) {
        Person person = genericDao.getByKey(Person.class, id);
        person.setEmail(email);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        if (password != null && !password.isEmpty()) {
            person.setPassHash(password);
        }
        return genericDao.mergeEntity(person).getId();
    }

    @Override
    public Long addPerson(String firstName, String lastName, String email, String password) {
        Person person = new Person(firstName, lastName, email, password);
        return genericDao.persistEntity(person).getId();
    }

    @Override
    public boolean isManagerById(Long id) {
        Person person = genericDao.getByKey(Person.class, id);
        return person.isManager();
    }

    @Override
    public PersonDto getPerson(String email, String password) {
        Person person = genericDao.findUniqBy(Person.class, "email", email);
        if (person != null) {
            String salt = person.getSalt();
            String passHash = person.getPassHash();
            if (SHA1Provider.computeHash(password + salt).equals(passHash)) {
                return new PersonDto(person);
            }
        }
        return null;
    }

    @Override
    public PersonDto getPerson(String email) {
        Person person = genericDao.findUniqBy(Person.class, "email", email);
        if (person != null) {
            return new PersonDto(person);
        }
        return null;
        }

    @Override
    public List<ParticipationDto> getParticipationsOfPersonById(Long id) {
        Person person = genericDao.getByKey(Person.class, id);
        if(person == null)return null;
        List<Participation> participations = person.getParticiping();
        List<ParticipationDto> participationDtos = new ArrayList<ParticipationDto>();
        for (Participation participation : participations) {
            //(Long id, Long yearObj, Long manager, String managerEmail, Long politicalParty, String politicalPartyName, Long state, String stateName, Long committe, String committeName, Long rank, String rankName)
            participationDtos.add(new ParticipationDto(participation));
        }
        return participationDtos;}
    

}
