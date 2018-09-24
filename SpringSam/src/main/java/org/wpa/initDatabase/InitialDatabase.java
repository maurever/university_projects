package org.wpa.initDatabase;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wpa.BO.Committe;
import org.wpa.BO.Competency;
import org.wpa.BO.District;
import org.wpa.BO.Organization;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.Rank;
import org.wpa.BO.School;
import org.wpa.BO.State;
import org.wpa.BO.Year;
import org.wpa.DAOImpl.GenericDAO;
import org.wpa.service.persons.ManagerService;
import org.wpa.Participation.ParticipationService;

/**
 * If the database has been completly cleared, this class reinitial it. Fill
 * tables pernaments data.
 *
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika
 * at maurerova.cz>
 */
public class InitialDatabase {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GenericDAO dao = (GenericDAO) context.getBean("genericDAO");
    ManagerService managerService = (ManagerService) context.getBean("managerServiceImpl");
    ParticipationService participationService = (ParticipationService) context.getBean("participationServiceImpl");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InitialDatabase database = new InitialDatabase();
//        database.fillCompetencies();
//        database.addCommittes();
//        database.addDistricts();
//        database.addParty();
        database.addFraction();
//        database.addOrganization();
//        database.addRank();
//        database.addSchool();
//        database.addState();
//        database.addYear(); 
//        database.createInitialAdmin();

    }

    /**
     * Create admin account.
     */
    public void createInitialAdmin() {
        managerService.addManager("Admin", "Admin", "admin@admin.com", "admin", "100");
    }

    /**
     * Add pernament competencies.
     */
    public void fillCompetencies() {
        List<Competency> competencies = new ArrayList<Competency>();

        competencies.add(new Competency(new Long(100), "Administrator", "Kompletní přístup."));
        competencies.add(new Competency(new Long(1), "Seznam uživatelů", "Prohlížení seznamu uživatelů."));
        competencies.add(new Competency(new Long(2), "Tvorba účtů", "Vytvoření účtu organizátora."));
        competencies.add(new Competency(new Long(3), "Úprava uživatelů", "upravování a mazaní uživatelských účtů"));
        competencies.add(new Competency(new Long(4), "Zobrazení všech účastí a řizení", "Zobrazení účastí"));

        dao.mergeAll(competencies);
    }

    /**
     * Add pernament committes.
     */
    private void addCommittes() {
        List<Committe> list = new ArrayList<Committe>();
        list.add(new Committe("Výbor pro energii a obchod"));
        list.add(new Committe("Výbor pro justici a soudnictví"));
        list.add(new Committe("Výbor pro ozbrojené složky"));
        list.add(new Committe("Výbor pro přírodní zdroje"));
        list.add(new Committe("Výbor pro vládní dohled"));
        list.add(new Committe("Výbor pro zahraniční vztahy"));
        dao.persistAll(list);
    }

    /**
     * Add pernament districts.
     */
    private void addDistricts() {
        List<District> list = new ArrayList<District>();
        list.add(new District("1"));
        list.add(new District("2"));
        list.add(new District("3"));
        list.add(new District("4"));
        list.add(new District("5"));
        list.add(new District("6"));
        list.add(new District("7"));
        dao.persistAll(list);
    }

    /**
     * Add pernament political party.
     */
    private void addParty() {
        List<PoliticalParty> list = new ArrayList<PoliticalParty>();
        list.add(new PoliticalParty("Demokraté"));
        list.add(new PoliticalParty("Republikáni"));
        dao.persistAll(list);
    }

    /**
     * Add pernament fraction.
     */
    private void addFraction() {
        managerService.addFraction("Environmentalisté", new Long(1));
        managerService.addFraction("Modří psi", new Long(1));
        managerService.addFraction("Noví demokraté", new Long(1));
        managerService.addFraction("Progresivisté", new Long(1));
        managerService.addFraction("Sociální liberálové", new Long(1));
        managerService.addFraction("Libertariáni", new Long(2));
        managerService.addFraction("Paleokonzervativci", new Long(2));
        managerService.addFraction("Neokonzervatisti", new Long(2));
        managerService.addFraction("Tradicionalisté", new Long(2));
        managerService.addFraction("Nová pravice", new Long(2));
        managerService.addFraction("Liberální republikáni", new Long(2));
    }

    /**
     * Add pernament organization.
     */
    private void addOrganization() {
        List<Organization> list = new ArrayList<Organization>();
        list.add(new Organization("Economist", "Noviny", 1));
        list.add(new Organization("New York Times", "Noviny", 1));
        list.add(new Organization("Dalas", "Noviny", 1));
        list.add(new Organization("Shell", "Lobby", 2));
        list.add(new Organization("Microsoft", "Lobby", 2));
        list.add(new Organization("Siemens", "Lobby", 2));
        dao.persistAll(list);
    }

    /**
     * Add pernament rank.
     */
    private void addRank() {
        List<Rank> list = new ArrayList<Rank>();
        list.add(new Rank("Předseda"));
        list.add(new Rank("Významný člen"));
        dao.persistAll(list);
    }

    /**
     * Add test school.
     */
    private void addSchool() {
        List<School> list = new ArrayList<School>();
        list.add(new School("Jméno školy1", "Ulice školy1", "Brno", "55", 40000));
        list.add(new School("Jméno školy2", "Ulice školy2", "Praha", "42", 30000));
        list.add(new School("Jméno školy3", "Ulice školy3", "Plzeň", "30", 40000));
        list.add(new School("Jméno školy4", "Ulice školy1", "Brno", "Krno", 40000));
        dao.persistAll(list);
    }

    /**
     * Add part of pernament states.
     */
    private void addState() {
        List<State> list = new ArrayList<State>();
        list.add(new State("Alabama"));
        list.add(new State("Alaska"));
        list.add(new State("American Samoa"));
        list.add(new State("Arizona"));
        list.add(new State("Arkansas"));
        list.add(new State("California"));
        list.add(new State("Connecticut"));
        list.add(new State("Delaware"));
        list.add(new State("District of Columbia"));
        list.add(new State("Florida"));
        list.add(new State("Georgia"));
        list.add(new State("Guam"));
        list.add(new State("Hawaii"));
        list.add(new State("Idaho"));
        list.add(new State("Illinois"));
        list.add(new State("Iowa"));
        list.add(new State("Kansas"));
        list.add(new State("Kentucky"));
        list.add(new State("Louisiana"));
        list.add(new State("Maine"));
        dao.persistAll(list);
    }

    /**
     * Add some years.
     */
    private void addYear() {
        List<Year> list = new ArrayList<Year>();
        list.add(new Year(2013, true));
        list.add(new Year(2014, true));
        list.add(new Year(2015, true));
        dao.persistAll(list);

    }

}
