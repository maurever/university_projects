package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.District;
import org.wpa.BO.Senator;
import org.wpa.DTO.DistrictDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 *
 * @author Vít Hlaváček <hlava.vit at google.com>
 */
@Service("districtService")
@Scope(value = "singleton")
public class DistrictService extends AbstractDataAccessService{
    

    @Transactional(readOnly = true)
    public List<DistrictDto> getAllDistricts() {
        List<DistrictDto> districtDtos = new ArrayList<DistrictDto>();
        List<District> districts = genericDao.getAll(District.class, "districtName", true);
        for (District district : districts) {
            districtDtos.add(new DistrictDto(district));
        }
        return districtDtos;
    }

    @Transactional(readOnly = true)
    public DistrictDto getDistrict(String name) {
        District district = genericDao.findUniqBy(District.class, "districtName", name);
        if (district != null) {
            return new DistrictDto(district);
        }
        return null;
}
    @Transactional(readOnly = true)
    public List<SenatorDto> getSenatorsOf(Long id, int year) {
        District district = genericDao.getByKey(District.class, id);
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
            for (Senator senator : district.getSenators()) {
                if(senator.getYearObj().getYearInt().equals(year))
                senatorDtos.add(new SenatorDto(senator));
            }
        return senatorDtos;
    }
}
