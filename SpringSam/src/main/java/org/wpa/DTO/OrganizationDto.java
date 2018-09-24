package org.wpa.DTO;

import org.wpa.BO.Organization;

/**
 *
 * @author Veronika Maurerova
 */
public class OrganizationDto extends AbstractDto {

    String organizationName;
    String description;
    Integer type;

    public OrganizationDto() {
    }

    public OrganizationDto(Long id, String organizationName, String description) {
        super(id);
        this.organizationName = organizationName;
        this.description = description;
    }

    public OrganizationDto(Organization org) {
        super(org.getId());
        this.organizationName = org.getOrganizationName();
        this.description = org.getDescription();
        this.type = org.getType();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.FractionDto[ id=" + id + ", organizationName=" + organizationName + ", description=" + description + " ]";
    }

}
