package org.wpa.service.persons;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wpa.BO.Competency;
import org.wpa.BO.Fraction;
import org.wpa.BO.Management;
import org.wpa.BO.Manager;
import org.wpa.BO.PoliticalParty;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.ManagerDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Service for getting all information about Manager.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("managerServiceImpl")
@Scope(value = "singleton")
public class ManagerServiceImpl extends AbstractDataAccessService implements ManagerService {

    /**
     *
     * @param firstName
     * @param lastName
     * @param Email
     * @param password
     * @param competencies String on competency numbers separated by ", "
     * @return
     */
    @Override
    public Long addManager(String firstName, String lastName, String Email, String password, String competencies) {
        return addManager(firstName, lastName, Email, password, competenciesToList(competencies));
    }

    @Override
    public Long addManager(String firstName, String lastName, String Email, String password, List<Long> competencies) {
        List<Competency> compets = new ArrayList<Competency>();

        for (Long competencyId : competencies) {
            compets.add(genericDao.getByKey(Competency.class, competencyId));
        }
        Manager manager = new Manager(firstName, lastName, Email, password, compets);
        manager = genericDao.mergeEntity(manager);
        return manager.getId();
    }

    @Override
    public Long alterManager(Long id, String firstName, String lastName, String Email, String passHash, String competencies) {
        return alterManager(id, firstName, lastName, Email, passHash, competenciesToList(competencies));
    }

    @Override
    public Long alterManager(Long id, String firstName, String lastName, String Email, String password, List<Long> competencies) {
        Manager manager = genericDao.getByKey(Manager.class, id);
        if (competencies != null) {
            List<Competency> compets = new ArrayList<Competency>();
            for (Long c : competencies) {
                compets.add(genericDao.getByKey(Competency.class, c));
            }
            manager.setCompetencies(compets);
        }
        manager.setEmail(Email);
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        if (password != null && !password.isEmpty()) {
            manager.setPassHash(password);
        }
        return genericDao.mergeEntity(manager).getId();
    }

    @Override
    public void deleteManager(Long participantId) {
        changeCompetencies(participantId, new ArrayList<Long>());
        genericDao.removeById(participantId, Manager.class);
    }

    @Override
    public ManagerDto getManagerById(Long id) {
        Manager m = genericDao.getByKey(Manager.class, id);
        if (m == null) {
            return null;
        }
        return new ManagerDto(m.getId(), m.getFirstName(), m.getLastName(), m.getEmail());
    }

    @Override
    public List<ManagerDto> getAllManagers(String orderBy, boolean ascending) {
        List<Manager> managers = genericDao.getAll(Manager.class, orderBy, ascending);
        List<ManagerDto> managerDtos = new ArrayList<ManagerDto>();
        for (Manager m : managers) {
            managerDtos.add(new ManagerDto(m.getId(), m.getFirstName(), m.getLastName(), m.getEmail()));
        }
        return managerDtos;
    }

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> managers = genericDao.getAll(Manager.class);
        List<ManagerDto> managerDtos = new ArrayList<ManagerDto>();
        for (Manager m : managers) {
            managerDtos.add(new ManagerDto(m.getId(), m.getFirstName(), m.getLastName(), m.getEmail()));
        }
        return managerDtos;
    }

    @Override
    public List<CompetencyDto> getCompeteciesOfManagerById(Long id) {
        List<Competency> competencies = genericDao.getByKey(Manager.class, id).getCompetencies();
        List<CompetencyDto> competencyDTOs = new ArrayList<CompetencyDto>();
        for (Competency c : competencies) {
            competencyDTOs.add(new CompetencyDto(c));
        }
        return competencyDTOs;
    }

    @Override
    public List<ManagementDto> getManagementsOfManagerById(Long id) {
        Manager manager = genericDao.getByKey(Manager.class, id);
        if (manager == null) {
            return null;
        }
        List<Management> managements = manager.getManaging();
        List<ManagementDto> managementDTOs = new ArrayList<ManagementDto>();
        for (Management m : managements) {
            //(Long id, Long yearObj, Long manager, String managerEmail, Long politicalParty, String politicalPartyName, Long state, String stateName, Long committe, String committeName, Long rank, String rankName)
            managementDTOs.add(new ManagementDto(m));
        }
        return managementDTOs;
    }

    private List<Long> competenciesToList(String competencies) {
        if (competencies == null) {
            return null;
        }
        String[] compets = competencies.split(", ");
        List<Long> coms = new ArrayList<Long>();
        for (String s : compets) {
            coms.add(Long.parseLong(s));
        }
        return coms;
    }

    @Override
    public void changeCompetencies(Long id, String competencies) {
        changeCompetencies(id, competenciesToList(competencies));
    }

    @Override
    public void changeCompetencies(Long id, List<Long> competencies) {
        Manager manager = genericDao.getByKey(Manager.class, id);
        List<Competency> compets = new ArrayList<Competency>();
        for (Long c : competencies) {
            compets.add(genericDao.getByKey(Competency.class, c));
        }
        manager.setCompetencies(compets);
        genericDao.mergeEntity(manager);
    }

    @Override
    public List<CompetencyDto> getAllPossibleCompetencies() {
        List<Competency> competecies = genericDao.getAll(Competency.class, "id", true);
        List<CompetencyDto> competencyDtos = new ArrayList<CompetencyDto>();
        for (Competency c : competecies) {
            competencyDtos.add(new CompetencyDto(c));
        }
        return competencyDtos;
    }

    @Override
    public void addFraction(String name, Long politicalPartyId) {
        PoliticalParty party = genericDao.getByKey(PoliticalParty.class, politicalPartyId);
        genericDao.mergeEntity(new Fraction(name, party));
    }

}
