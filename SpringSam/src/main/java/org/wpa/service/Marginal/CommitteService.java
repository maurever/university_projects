package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Committe;
import org.wpa.BO.Deputy;
import org.wpa.BO.Lobbyist;
import org.wpa.BO.Management;
import org.wpa.BO.Senator;
import org.wpa.DTO.CommitteDto;
import org.wpa.DTO.DeputyDto;
import org.wpa.DTO.LobbyistDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about Committe.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("committeService")
@Scope(value = "singleton")
public class CommitteService extends AbstractDataAccessService {

    @Transactional(readOnly = true)
    public CommitteDto getCommitte(String committeName) {
        Committe committe = genericDao.findUniqBy(Committe.class, "committeName", committeName);
        if (committe != null) {
            return new CommitteDto(committe);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<CommitteDto> getAllCommittes() {
        List<CommitteDto> committeDtos = new ArrayList<CommitteDto>();
        List<Committe> committes = genericDao.getAll(Committe.class, "committeName", true);
        for (Committe committe : committes) {
            committeDtos.add(new CommitteDto(committe));
        }
        return committeDtos;
    }
    @Transactional(readOnly = true)
    public List<SenatorDto> getSenatorsOf(Long id, int year){
        Committe committe = genericDao.getByKey(Committe.class, id);
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
            for(Senator senator:committe.getSenators()){
                if(senator.getYearObj().getYearInt().equals(year))
                senatorDtos.add(new SenatorDto(senator));
            }
        return senatorDtos;
    }
    @Transactional(readOnly = true)
    public List<DeputyDto> getDeputiesOf(Long id, int year){
        Committe committe = genericDao.getByKey(Committe.class, id);
        List<DeputyDto> deputyDtos = new ArrayList<DeputyDto>();
            for(Deputy deputy:committe.getDeputies()){
                if(deputy.getYearObj().getYearInt().equals(year))
                deputyDtos.add(new DeputyDto(deputy));
            }
        return deputyDtos;
    }
    @Transactional(readOnly = true)
    public List<LobbyistDto> getLobbyistsOf(Long id, int year){
        Committe committe = genericDao.getByKey(Committe.class, id);
        List<LobbyistDto> lobbyistDtos = new ArrayList<LobbyistDto>();
            for(Lobbyist lobbyist:committe.getLobbyists()){
                if(lobbyist.getYearObj().getYearInt().equals(year))
                lobbyistDtos.add(new LobbyistDto(lobbyist));
            }
        return lobbyistDtos;
    }
    @Transactional(readOnly = true)
    public List<ManagementDto> getManagementsOf(Long id, int year){
        Committe committe = genericDao.getByKey(Committe.class, id);
        List<ManagementDto>managementDtos = new ArrayList<ManagementDto>();
        for(Management management:committe.getManagements()){
            if(management.getYearObj().getYearInt().equals(year))
            managementDtos.add(new ManagementDto(management));
        }
        return managementDtos;
    }
}
