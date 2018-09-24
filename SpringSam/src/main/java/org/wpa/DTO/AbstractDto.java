package org.wpa.DTO;

import java.io.Serializable;

/**
 *
 * @author Veronika Maurerova
 */
public abstract class AbstractDto implements Serializable {

    protected Long id;

    public AbstractDto(Long id) {
        this.id = id;
    }

    public AbstractDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDto other = (AbstractDto) obj;
        return this.id == other.id || (this.id != null && this.id.equals(other.id));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public abstract String toString();

}
