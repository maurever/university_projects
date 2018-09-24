package org.wpa.service.persons;

import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.PersonDto;

/**
 * Interface servis for getting all information about person from database.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public interface PersonService {

    /**
     * Get person form database .
     * @param id
     * @return PersonDto or null;
     */
    @Transactional(readOnly = true)
    public PersonDto getPersonById(Long id);

    /**
     * Get all persons from database.
     * @return List<PersonDto>;
     */
    @Transactional(readOnly = true)
    public List<PersonDto> getAllPersons();

    /**
     * Get all person from database order by input String or ascending.
     * @param orderBy
     * @param ascending
     * @return List<PersonDto>;
     */
    @Transactional(readOnly = true)
    public List<PersonDto> getAllPersons(String orderBy, boolean ascending);

    /**
     * Get person by email and password.
     * @param email
     * @param password
     * @return PersonDto or null;
     */
    @Transactional(readOnly = true)
    public PersonDto getPerson(String email, String password);

    /**
     * Get person by email.
     * @param email
     * @return PersonDto or null;
     */
    @Transactional(readOnly = true)
    public PersonDto getPerson(String email);

    /**
     * Get competencies in map.
     * @param personId
     * @return HashMap<Long, CompetencyDto>;
     */
    @Transactional(readOnly = true)
    public HashMap<Long, CompetencyDto> getCompetencies(Long personId);

    /**
     * Delete person by uniq ID;
     * @param id 
     */
    @Transactional(readOnly = false)
    public void deletePerson(Long id);

    /**
     * Update person by uniq ID.
     * @param id
     * @param firstName
     * @param LastName
     * @param email
     * @param password
     * @return altered person ID.
     */
    @Transactional(readOnly = false)
    public Long alterPerson(Long id, String firstName, String LastName, String email, String password);

    /**
     * Add new person to database.
     * @param firstName
     * @param LastName
     * @param email
     * @param password
     * @return ID new added person.
     */
    @Transactional(readOnly = false)
    public Long addPerson(String firstName, String LastName, String email, String password);

    /**
     * Check if the person with the ID is manager or not.
     * @param id
     * @return true if the person is manager, false if not.
     */
    @Transactional(readOnly = true)
    public boolean isManagerById(Long id);
    
    /**
     * 
     * @param id 
     * @return List of persons paticipations, null if not person
     */
    @Transactional(readOnly = true)
    public List<ParticipationDto>getParticipationsOfPersonById(Long id);
}
