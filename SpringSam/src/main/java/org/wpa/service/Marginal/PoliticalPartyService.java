package org.wpa.service.Marginal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Deputy;
import org.wpa.BO.Fraction;
import org.wpa.BO.Management;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.Senator;
import org.wpa.DTO.DeputyDto;
import org.wpa.DTO.ManagementDto;
import org.wpa.DTO.PoliticalPartyDto;
import org.wpa.DTO.SenatorDto;
import org.wpa.service.AbstractDataAccessService.AbstractDataAccessService;

/**
 * Getting all information about Political Party.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Service("partyService")
@Scope(value = "singleton")
public class PoliticalPartyService extends AbstractDataAccessService {

    @Transactional(readOnly = true)
    public List<PoliticalPartyDto> getAllPoliticalParties() {
        List<PoliticalPartyDto> politicalPartyDtos = new ArrayList<PoliticalPartyDto>();
        List<PoliticalParty> politicalParties = genericDao.getAll(PoliticalParty.class, "politicalartyName", true);
        for (PoliticalParty politicalParty : politicalParties) {
            politicalPartyDtos.add(new PoliticalPartyDto(politicalParty));
        }
        return politicalPartyDtos;
    }

    @Transactional(readOnly = true)
    public PoliticalPartyDto getPoliticalParty(String name) {
        PoliticalParty politicalParty = genericDao.findUniqBy(PoliticalParty.class, "politicalartyName", name);
        if (politicalParty != null) {
            return new PoliticalPartyDto(politicalParty);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<SenatorDto> getSenatorsOf(Long partyId, int year) {
        PoliticalParty party = genericDao.getByKey(PoliticalParty.class, partyId);
        List<Fraction> fractions = party.getFractions();
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
        for (Fraction fraction : fractions) {
            for (Senator senator : fraction.getSenators()) {
                if(senator.getYearObj().getYearInt().equals(year))
                senatorDtos.add(new SenatorDto(senator));
            }
        }
        return senatorDtos;
    }

    @Transactional(readOnly = true)
    public List<DeputyDto> getDeputiesOf(Long partyId, int year) {
        PoliticalParty party = genericDao.getByKey(PoliticalParty.class, partyId);
        List<Fraction> fractions = party.getFractions();
        List<DeputyDto> deputyDtos = new ArrayList<DeputyDto>();
        for (Fraction fraction : fractions) {
            for (Deputy deputy : fraction.getDeputies()) {
                if(deputy.getYearObj().getYearInt().equals(year))
                deputyDtos.add(new DeputyDto(deputy));
            }
        }
        return deputyDtos;
    }

    @Transactional(readOnly = true)
    public List<ManagementDto> getManagementsOf(Long partyId, int year) {
        PoliticalParty party = genericDao.getByKey(PoliticalParty.class, partyId);
        List<ManagementDto> managementDtos = new ArrayList<ManagementDto>();
        for (Management management : party.getManagements()) {
            if(management.getYearObj().getYearInt().equals(year))
            managementDtos.add(new ManagementDto(management));
        }
        return managementDtos;
    }

    @Transactional(readOnly = true)
    public List<SenatorDto> getSenatorsOfFraction(Long id, int year) {
        Fraction fraction = genericDao.getByKey(Fraction.class, id);
        List<SenatorDto> senatorDtos = new ArrayList<SenatorDto>();
        for (Senator senator : fraction.getSenators()) {
            if(senator.getYearObj().getYearInt().equals(year))
            senatorDtos.add(new SenatorDto(senator));
        }
        return senatorDtos;
    }

    @Transactional(readOnly = true)
    public List<DeputyDto> getDeputiesOfFraction(Long id, int year) {
        Fraction fraction = genericDao.getByKey(Fraction.class, id);
        List<DeputyDto> deputyDtos = new ArrayList<DeputyDto>();
        for (Deputy deputy : fraction.getDeputies()) {
            if(deputy.getYearObj().getYearInt().equals(year))
            deputyDtos.add(new DeputyDto(deputy));
        }
        return deputyDtos;
    }
}
