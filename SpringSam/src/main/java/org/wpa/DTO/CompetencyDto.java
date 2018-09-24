package org.wpa.DTO;

import org.wpa.BO.Competency;

/**
 *
 * @author Veronika Maurerova
 */
public class CompetencyDto extends AbstractDto {

    private String name;
    private String description;
    private boolean selected = false;
    public CompetencyDto() {
    }

    public CompetencyDto(Long id, String name, String description) {
        super.id = id;
        this.name = name;
        this.description = description;
    }

    public CompetencyDto(Competency competency) {
        super(competency.getId());
        this.name = competency.getName();
        this.description = competency.getDescrition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.CompetencyDto[ id=" + id + " name=" + name + " description=" + description + " ]";
    }

}
