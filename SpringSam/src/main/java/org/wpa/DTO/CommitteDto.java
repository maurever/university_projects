package org.wpa.DTO;

import org.wpa.BO.Committe;

/**
 *
 * @author Veronika Maurerova
 */
public class CommitteDto extends AbstractDto {

    private String committeName;

    public CommitteDto() {
    }

    public CommitteDto(Long id, String committeName) {
        super(id);
        this.committeName = committeName;
    }

    public CommitteDto(Committe committe) {
        super(committe.getId());
        this.committeName = committe.getCommitteName();
    }

    public String getCommitteName() {
        return committeName;
    }

    public void setCommitteName(String committeName) {
        this.committeName = committeName;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.CommitteDto[ id=" + id + " commiteName=" + committeName + " ]";
    }

}
