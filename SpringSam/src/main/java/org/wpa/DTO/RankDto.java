package org.wpa.DTO;

import org.wpa.BO.Rank;

/**
 *
 * @author Veronika Maurerova
 */
public class RankDto extends AbstractDto {

    private String rankName;

    public RankDto() {
    }

    public RankDto(Long id, String rankName) {
        super(id);
        this.rankName = rankName;
    }

    public RankDto(Rank rank) {
        super(rank.getId());
        this.rankName = rank.getRankName();
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    @Override
    public String toString() {
        return "org.wpa.DTO.RankDto[ id=" + id + ", rankName=" + rankName + " ]";
    }

}
