package org.wpa.service.persons;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.ManagerDto;

/**
 *
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public interface ManagerService {

    /**
     * Change competencies from String reprezentation.
     *
     * @param id
     * @param competencies
     */
    @Transactional(readOnly = false)
    public void changeCompetencies(Long id, String competencies);

    /**
     * Change competencies List reprezentation.
     *
     * @param id
     * @param competencies
     */
    @Transactional(readOnly = false)
    public void changeCompetencies(Long id, List<Long> competencies);

    /**
     * Add manager to database via GenericDAO with String competencies.
     *
     * @param firstName
     * @param lastName
     * @param Email
     * @param passWord
     * @param competencies
     * @return id of new manager;
     */
    @Transactional(readOnly = false)
    public Long addManager(String firstName, String lastName, String Email, String passWord, String competencies);

    /**
     * Add manager to database via GenericDao with List<Comptenecy>.
     *
     * @param firstName
     * @param lastName
     * @param Email
     * @param passWord
     * @param competencies
     * @return id of new manager;
     */
    @Transactional(readOnly = false)
    public Long addManager(String firstName, String lastName, String Email, String passWord, List<Long> competencies);

    /**
     * Update manager with String competencies
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param Email
     * @param passHash
     * @param competencies
     * @return id of new manager;
     */
    @Transactional(readOnly = false)
    public Long alterManager(Long id, String firstName, String lastName, String Email, String passHash, List<Long> competencies);

    /**
     * Update manager with List<Competency>.
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param Email
     * @param passHash
     * @param competencies
     * @return id of new manager;
     */
    @Transactional(readOnly = false)
    public Long alterManager(Long id, String firstName, String lastName, String Email, String passHash, String competencies);

    /**
     * Delete manager form database by ID.
     *
     * @param Id
     */
    @Transactional(readOnly = false)
    public void deleteManager(Long Id);

    /**
     * Get managerDto by ID
     *
     * @param id
     * @return ManagerDto or null if there is not Manager with this ID.
     */
    @Transactional(readOnly = true)
    public ManagerDto getManagerById(Long id);

    /**
     * Get all manager from database in form List<ManagerDto> order by input
     * String or ascending.
     *
     * @param orderBy
     * @param Ascending
     * @return List<ManagerDto>;
     */
    @Transactional(readOnly = true)
    public List<ManagerDto> getAllManagers(String orderBy, boolean Ascending);

    /**
     * Get all manager from database in form List<Ma
     *
     * @return List<ManagerDto>;
     */
    @Transactional(readOnly = true)
    public List<ManagerDto> getAllManagers();

    /**
     * Get comptenecy in list for uniq ID.
     *
     * @param id
     * @return List<CompetencyDto>;
     */
    @Transactional(readOnly = true)
    public List<CompetencyDto> getCompeteciesOfManagerById(Long id);

    /**
     * Get managements for uniq ID.
     *
     * @param id
     * @return List<ManagementDto>
     */
    @Transactional(readOnly = true)
    public List<ManagementDto> getManagementsOfManagerById(Long id);

    /**
     * Get all competencies from database.
     *
     * @return List<CompetencyDto>
     */
    @Transactional(readOnly = true)
    public List<CompetencyDto> getAllPossibleCompetencies();

    /**
     * Add fraction to database.
     * @param name
     * @param politicalPartyId 
     */
    @Transactional(readOnly = false)
    public void addFraction(String name, Long politicalPartyId);

}
