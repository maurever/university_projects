package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Journalist;
import org.wpa.BO.Lobbyist;
import org.wpa.BO.Organization;
import org.wpa.DTO.JournalistDto;
import org.wpa.DTO.LobbyistDto;
import org.wpa.DTO.OrganizationDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about Organization.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("oragnizationService")
@Scope(value = "singleton")
public class OrganizationService extends AbstractDataAccessService {

    @Transactional(readOnly = true)
    public OrganizationDto getOrganizationById(Long id) {
        Organization org = genericDao.getByKey(Organization.class, id);
        return org != null ? new OrganizationDto(org) : null;
    }

    @Transactional(readOnly = true)
    public OrganizationDto getOrganizationByName(String organizationName) {
        Organization org = genericDao.findUniqBy(Organization.class, "organizationName", organizationName);
        return org != null ? new OrganizationDto(org) : null;
    }

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllOrganization() {
        List<OrganizationDto> listOrgDto = new ArrayList<OrganizationDto>();
        List<Organization> listOrg = genericDao.getAll(Organization.class);
        for (Organization organization : listOrg) {
            listOrgDto.add(new OrganizationDto(organization));
        }
        return listOrgDto;
    }

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAllOrganizationByType(int type) {
        List<OrganizationDto> listOrgDto = new ArrayList<OrganizationDto>();
        List<Organization> listOrg = genericDao.findBy(Organization.class, "type", type + "");
        for (Organization organization : listOrg) {
            listOrgDto.add(new OrganizationDto(organization));
        }
        return listOrgDto;
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateOrganization(OrganizationDto orgDto) {
        Organization org = genericDao.getByKey(Organization.class, orgDto.getId());
        if (org == null) {
            org = new Organization();
        }
        org.setOrganizationName(orgDto.getOrganizationName());
        org.setDescription(orgDto.getDescription());
        org.setType(orgDto.getType());
        genericDao.mergeEntity(org);
    }

    @Transactional(readOnly = false)
    public void deleteOrganizationById(Long id) {
        genericDao.removeById(id, Organization.class);
    }
    @Transactional(readOnly = true)
    public List<LobbyistDto> getLobbyistsOf(Long id, int year){
        Organization organization = genericDao.getByKey(Organization.class, id);
        List<LobbyistDto> lobbyistDtos = new ArrayList<LobbyistDto>();
            for(Lobbyist lobbyist:organization.getLobbyists()){
                if(lobbyist.getYearObj().getYearInt().equals(year))
                lobbyistDtos.add(new LobbyistDto(lobbyist));
            }
        return lobbyistDtos;
    }
    @Transactional(readOnly = true)
    public List<JournalistDto> getJournalistsOf(Long id, int year){
        Organization organization = genericDao.getByKey(Organization.class, id);
        List<JournalistDto> journalistDtos = new ArrayList<JournalistDto>();
            for(Journalist journalist:organization.getJournalists()){
                if(journalist.getYearObj().getYearInt().equals(year))
                journalistDtos.add(new JournalistDto(journalist));
            }
        return journalistDtos;
    }
}
