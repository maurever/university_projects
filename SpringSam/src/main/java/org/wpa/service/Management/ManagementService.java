package org.wpa.service.Management;

import org.springframework.transaction.annotation.Transactional;
import org.wpa.DTO.ManagementDto;

/**
 * Interface for Management Service.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public interface ManagementService {
    
    /**
     * Get ManagementDto by ID.
     * @param year
     * @param managerId
     * @return ManagementDto or null;
     */
    @Transactional(readOnly = true)
    public ManagementDto getManagement(Integer year, Long managerId);
    
    /**
     * Save or update input Management.
     * @param managementDto
     * @return ManagementDto.
     */
    @Transactional(readOnly = false)
    public ManagementDto saveOrUpdateManagement(ManagementDto managementDto);
}
