package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.School;
import org.wpa.DTO.SchoolDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about School.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("schoolService")
@Scope(value = "singleton")
public class SchoolService extends AbstractDataAccessService {

    public SchoolDto getSchoolById(Long id) {
        School school = genericDao.getByKey(School.class, id);
        return school != null ? new SchoolDto(school) : null;
    }

    public SchoolDto getSchoolByName(String schoolName) {
        School school = genericDao.findUniqBy(School.class, "schoolName", schoolName);
        return school != null ? new SchoolDto(school) : null;
    }

    public List<SchoolDto> getAllSchools() {
        List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
        List<School> schools = genericDao.getAll(School.class);
        for (School school : schools) {
            schoolDtos.add(new SchoolDto(school));
        }
        return schoolDtos;
    }

    public List<SchoolDto> getSchoolsByCity(String city) {
        List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
        List<School> schools = genericDao.findBy(School.class, "city", city);
        for (School school : schools) {
            schoolDtos.add(new SchoolDto(school));
        }
        return schoolDtos;
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateSchool(SchoolDto schoolDto) {
        School school = genericDao.getByKey(School.class, schoolDto.getId());
        if (school == null) {
            school = new School();
        }
        school.setSchoolName(schoolDto.getSchoolName());
        school.setStreet(schoolDto.getStreet());
        school.setStreetCode(schoolDto.getStreetCode());
        school.setCity(schoolDto.getCity());
        school.setPostalCode(schoolDto.getPostalCode());
        genericDao.mergeEntity(school);
    }

    @Transactional(readOnly = false)
    public void deleteSchoolById(Long id) {
        genericDao.removeById(id, School.class);
    }

}
