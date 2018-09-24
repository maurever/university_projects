package org.wpa.DTO;

import org.wpa.BO.State;

/**
 *
 * @author Veronika Maurerova
 */
public class StateDto extends AbstractDto {

    private String stateName;

    public StateDto() {
    }

    public StateDto(String stateName, Long id) {
        super(id);
        this.stateName = stateName;
    }

    public StateDto(State state) {
        super(state.getId());
        this.stateName = state.getStateName();
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.StateDto[ id=" + id + ", stateName=" + stateName + " ]";
    }

}
