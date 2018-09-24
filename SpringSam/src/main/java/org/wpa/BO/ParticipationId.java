/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wpa.BO;

import java.io.Serializable;

/**
 * Composite key for person,year and participation. 
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
public class ParticipationId implements Serializable {

    private Integer yearObj;
    private Long participant;

    public ParticipationId() {
    }

    public ParticipationId(Integer yearObj, Long participant) {
        this.yearObj = yearObj;

        this.participant = participant;
    }

    public Integer getYearObj() {
        return yearObj;
    }

    public void setYearObj(Integer yearObj) {
        this.yearObj = yearObj;
    }

    public Long getParticipant() {
        return participant;
    }

    public void setParticipant(Long participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ParticipationId) {
            ParticipationId pk = (ParticipationId) object;
            return yearObj.equals(pk.yearObj) && participant.equals(pk.participant);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += yearObj.hashCode();
        hash += participant.hashCode();
        return hash;
    }

}
