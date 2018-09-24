/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wpa.BO;

/**
 * Interface for Entities with foreing key.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
public interface BOWithFK {

    /**
     * Getter for getting year´s id.
     * @return Integer year;
     */
    Integer getYearId();

    /**
     * Getter for getting person´s id.
     * @return Long id;
     */
    Long getPersonId();
}
