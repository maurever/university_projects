package org.wpa.DTO;

import org.wpa.BO.District;

/**
 *
 * @author Veronika Maurerova
 */
public class DistrictDto extends AbstractDto {

    private String districtName;

    public DistrictDto() {
    }

    public DistrictDto(District district) {
        super(district.getId());
        this.districtName = district.getDistrictName();
    }

    public DistrictDto(Long id, String districtName) {
        super(id);
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.DistrictDto[ id=" + id + ", commiteName=" + districtName + " ]";
    }
}
