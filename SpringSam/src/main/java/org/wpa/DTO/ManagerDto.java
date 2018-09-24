package org.wpa.DTO;

import org.wpa.BO.Manager;

/**
 *
 * @author Veronika Maurerova
 */
public class ManagerDto extends PersonDto {

    public ManagerDto() {
    }

    public ManagerDto(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public <E extends Manager> ManagerDto(E manager) {
        super(manager);
    }

    public boolean isManager() {
        return true;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.ManagerDto{" + toTmpString() + '}';
    }

}
