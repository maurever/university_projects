package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Year;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.ParticipationDto;
import org.wpa.DTO.YearDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;
import org.wpa.service.persons.ManagerService;
import org.wpa.service.persons.PersonService;

/**
 * Getting all information about Year.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("yearService")
@Scope(value = "singleton")
public class YearService extends AbstractDataAccessService {

    @Autowired
    ManagerService managerService;
    @Autowired
    PersonService personService;

    @Transactional(readOnly = true)
    public List<YearDto> getAllYears() {
        List<Year> years = genericDao.getAll(Year.class, "yearInt", true);
        List<YearDto> yearDtos = new ArrayList<YearDto>();
        for (Year year : years) {
            yearDtos.add(new YearDto(year));
        }
        return yearDtos;
    }

    @Transactional(readOnly = true)
    public YearDto getYear(Integer year) {
        Year yearEntity = genericDao.getByKey(Year.class, year);
        YearDto yearDto = null;
        if (yearEntity != null) {
            yearDto = new YearDto(yearEntity);
        }
        return yearDto;
    }

    @Transactional(readOnly = true)
    public List<YearDto> getOpenedYears() {
        List<YearDto> allYears = getAllYears();
        List<YearDto> openedYears = new ArrayList<YearDto>();
        for (YearDto yearDto : allYears) {
            if (yearDto.isOpened()) {
                openedYears.add(yearDto);
            }
        }
        return openedYears;
    }

    @Transactional(readOnly = true)
    public List<YearDto> getAvaidableYearsOfManager(Long id) {
        List<YearDto> openedYears = getOpenedYears();
        List<ManagementDto> managements = managerService.getManagementsOfManagerById(id);
        for (ManagementDto managementDto : managements) {
            openedYears.remove(managementDto.getYearDto());
        }
        return openedYears;
    }

    public List<YearDto> getActiveYearsOfPerson(Long id) {
        List<YearDto> years = new ArrayList<YearDto>();
        List<ManagementDto> managements = managerService.getManagementsOfManagerById(id);
        List<ParticipationDto> participations = personService.getParticipationsOfPersonById(id);
        if (managements != null) {
            for (ManagementDto md : managements) {
                years.add(md.getYearDto());
            }
        }
        if (participations != null) {
            for (ParticipationDto pd : participations) {
                if (!years.contains(pd.getYearDto())) {
                    years.add(pd.getYearDto());
                }
            }
        }
        return years;
    }
}
