package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wpa.BO.Rank;
import org.wpa.DTO.RankDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about Rank.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("rankService")
@Scope(value = "singleton")
public class RankService extends AbstractDataAccessService {

    public RankDto getRank(String rankName) {
        Rank rank = genericDao.findUniqBy(Rank.class, "rankName", rankName);
        if (rank != null) {
            return new RankDto(rank);
        }
        return null;
    }

    public List<RankDto> getAllRanks() {
        List<RankDto> rankDtos = new ArrayList<RankDto>();
        List<Rank> ranks = genericDao.getAll(Rank.class, "rankName", true);
        for (Rank rank : ranks) {
            rankDtos.add(new RankDto(rank));
        }
        return rankDtos;
    }
}
