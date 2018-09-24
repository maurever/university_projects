package org.wpa.DTO;

import java.io.Serializable;
import org.wpa.BO.Year;

/**
 *
 * @author Veronika Maurerova
 */
public class YearDto implements Serializable {

    private Integer year;
    private boolean opened;

    public YearDto() {
    }

    public YearDto(Integer year, boolean opened) {
        this.year = year;
        this.opened = opened;
    }

    public YearDto(Year year) {
        this.year = year.getYearInt();
        this.opened = year.isOpened();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return "YearDto{" + "year=" + year + ", opened=" + opened + '}';
    }

    @Override
    public int hashCode() {
        return year.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final YearDto other = (YearDto) obj;
        if (this.year != other.year && (this.year == null || !this.year.equals(other.year))) {
            return false;
        }
        return true;
    }

}
