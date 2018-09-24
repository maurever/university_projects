package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Deputy;
import org.wpa.BO.Fraction;
import org.wpa.BO.Management;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.Senator;
import org.wpa.BO.State;
import org.wpa.DTO.DeputyDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.DTO.StateDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about State.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("stateService")
@Scope(value = "singleton")
public class StateService extends AbstractDataAccessService {

    @Transactional(readOnly = true)
    public StateDto getState(String stateName) {
        State state = genericDao.findUniqBy(State.class, "stateName", stateName);
        if (state != null) {
            return new StateDto(state);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<StateDto> getAllStates() {
        List<StateDto> stateDtos = new ArrayList<StateDto>();
        List<State> states = getGenericDao().getAll(State.class, "stateName", true);
        for (State state : states) {
            stateDtos.add(new StateDto(state));
        }
        return stateDtos;
    }
    @Transactional(readOnly = true)
    public List<SenatorDto> getSenatorsOf(Long id, int year){
        State state = genericDao.getByKey(State.class, id);
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
            for(Senator senator:state.getSenators()){
                if(senator.getYearObj().getYearInt().equals(year))
                senatorDtos.add(new SenatorDto(senator));
            }
        return senatorDtos;
    }
    @Transactional(readOnly = true)
    public List<DeputyDto> getDeputiesOf(Long id, int year){
        State state = genericDao.getByKey(State.class, id);
        List<DeputyDto> deputyDtos = new ArrayList<DeputyDto>();
            for(Deputy deputy:state.getDeputies()){
                if(deputy.getYearObj().getYearInt().equals(year))
                deputyDtos.add(new DeputyDto(deputy));
            }
        return deputyDtos;
    }
    @Transactional(readOnly = true)
    public List<ManagementDto> getManagementsOf(Long id, int year){
        State state = genericDao.getByKey(State.class, id);
        List<ManagementDto>managementDtos = new ArrayList<ManagementDto>();
        for(Management management:state.getManagements()){
            if(management.getYearObj().getYearInt().equals(year))
            managementDtos.add(new ManagementDto(management));
        }
        return managementDtos;
    }
}
