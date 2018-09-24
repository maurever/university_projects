package org.wpa.managedBeans.userManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.ManagementDto;
import org.wpa.service.persons.ManagerService;
import org.wpa.supportOperations.Authenticator;

/**
 * Managed Bean for view list of management.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("managementList")
@Scope(value = "view")
public class ManagementList {

    private List<ManagementDto> managements;
    private Long id;
    @Autowired
    ManagerService managerService;
    @Autowired
    private Authenticator authenticator;

    /**
     * Creates a new instance of Managements
     */
    public ManagementList() {
    }

    /**
     * Load all management for login user.
     *
     * @param id
     */
    public void load(long id) {
        if (id == 0) {
            id = authenticator.getCurrentUsersId();
        }
        managements = managerService.getManagementsOfManagerById(id);
        this.id = id;
    }

//GETTERS
    public List<ManagementDto> getManagements() {
        return managements;
    }

    public Long getId() {
        return id;
    }

}
