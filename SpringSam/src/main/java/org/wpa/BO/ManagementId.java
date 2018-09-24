/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wpa.BO;

import java.io.Serializable;

/**
 * Composit key form manager, management and year.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
public class ManagementId implements Serializable {

    private Integer yearObj;
    private Long manager;

    public ManagementId() {
    }

    public ManagementId(Integer yearObj, Long manager) {
        this.yearObj = yearObj;
        this.manager = manager;
    }

    public Integer getYearObj() {
        return yearObj;
    }

    public void setYearObj(Integer yearObj) {
        this.yearObj = yearObj;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ManagementId) {
            ManagementId pk = (ManagementId) object;
            return yearObj.equals(pk.yearObj) && manager.equals(pk.manager);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += yearObj.hashCode();
        hash += manager.hashCode();
        return hash;
    }
}
