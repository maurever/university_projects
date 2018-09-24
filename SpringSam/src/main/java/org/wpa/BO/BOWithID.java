/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wpa.BO;

/**
 * Interface for Entities with ID, which have Long ID. 
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
public interface BOWithID {
    
    /**
     * Getter for getting id.
     * @return Long id;
     */
    public Long getId();
}
