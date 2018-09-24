package org.wpa.TestingMains;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.wpa.BO.Committe;
import org.wpa.BO.Competency;
import org.wpa.BO.Deputy;
import org.wpa.BO.District;
import org.wpa.BO.Fraction;
import org.wpa.BO.Journalist;
import org.wpa.BO.Lobbyist;
import org.wpa.BO.Management;
import org.wpa.BO.Manager;
import org.wpa.BO.Organization;
import org.wpa.BO.Participation;
import org.wpa.BO.Person;
import org.wpa.BO.PoliticalParty;
import org.wpa.BO.Rank;
import org.wpa.BO.School;
import org.wpa.BO.Senator;
import org.wpa.BO.State;
import org.wpa.BO.Year;
import org.wpa.DAOImpl.GenericDAO;
import org.wpa.DTO.JournalistDto;
import org.wpa.Participation.ParticipationService;
import org.wpa.service.persons.PersonService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vít Hlaváček <hlava.vit at google.com>
 */
public class Pokusy {

    //SPRING
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GenericDAO dao = (GenericDAO) context.getBean("genericDAO");
    PersonService participantService = (PersonService) context.getBean("participantServiceImpl");
    ParticipationService participationService = (ParticipationService) context.getBean("participationServiceImpl");
    
    public static void main(String[] args) {
        //Pokusy pokusy = new Pokusy();
        //pokusy.participantService.addParticipant("ParticipantPokus2", "LastnamePArticipant2", "ParticipantPokus2@wpa.sam", "SFFAA1231");
        //kusy.testNamedQuery();
        //pokusy.deleteAll();
        //pokusy.createTables();
        //pokusy.addParticipant();
        //pokusy.fillTables();
        //pokusy.testParticipation();

    }

    private void testParticipation() {
        //Participant part = dao.findUniqBy(Participant.class, "id", "1");
        //System.out.println(part.toString());
//        Year year = dao.findUniqBy(Year.class, "opened", "true");
//        System.out.println(year.toString());
//        List<Journalist> list = dao.findBy(Journalist.class, "yearObj", year.getYearInt() + "");
//        for (Participation participation : list) {
//            System.out.println(participation.toString());
//        }
//        Competency competency = dao.findUniqBy(Competency.class, "name", "Tvorba účtů");
//        System.out.println(competency.toString());
        List<JournalistDto> list1 = participationService.getAllJournalistByOpenYear();
        for (JournalistDto participation : list1) {
            System.out.println(participation.toString());
        }

//        Year year1 = dao.getByNamedQuery("Year.findOpenedYear", Year.class).get(0);
//        System.out.println(year1.getYearInt());
//        List<ParticipationDto> list = participantService.getAllParticipation();
//        for (ParticipationDto participationDto : list) {
//            System.out.println(participationDto.toString());
//        }
//        System.out.println(participantService.addJournalist(new Long(3), new Long(1), new Long(1), year.getYearInt()));
//        System.out.println(participantService.addSenator(new Long(4), new Long(4), new Long(4), new Long(1), new Long(1), new Long(1), year.getYearInt()));
//        System.out.println(participantService.addDeputy(new Long(5), new Long(2), new Long(3), new Long(3), new Long(6), year.getYearInt()));
    }

    private void fillTables() {
//        addCommittes();
//        addDistricts();
//        addParty();
//        addFraction();
//        addOrganization();
//        addRank();
//        addSchool();
//        addState();
//        addYear();
    }

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

    private void addParty() {
        List<PoliticalParty> list = new ArrayList<PoliticalParty>();
        list.add(new PoliticalParty("Demokraté"));
        list.add(new PoliticalParty("Republikáni"));
        dao.persistAll(list);
    }

    private void addFraction() {
        List<Fraction> list = new ArrayList<Fraction>();
        PoliticalParty dem = dao.getByKey(PoliticalParty.class, new Long(1));
        PoliticalParty rep = dao.getByKey(PoliticalParty.class, new Long(2));
        list.add(new Fraction("Environmentalisté", dem));
        list.add(new Fraction("Modří psi", dem));
        list.add(new Fraction("Noví demokraté", dem));
        list.add(new Fraction("Progresivisté", dem));
        list.add(new Fraction("Sociální liberálové", dem));
        list.add(new Fraction("Libertariáni", rep));
        list.add(new Fraction("Paleokonzervativci", rep));
        list.add(new Fraction("Neokonzervatisti", rep));
        list.add(new Fraction("Tradicionalisté", rep));
        list.add(new Fraction("Nová pravice", rep));
        list.add(new Fraction("Liberální republikáni", rep));
        dao.persistAll(list);
    }

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

    private void addRank() {
        List<Rank> list = new ArrayList<Rank>();
        list.add(new Rank("Předseda"));
        list.add(new Rank("Významný člen"));
        dao.persistAll(list);
    }

    private void addSchool() {
        List<School> list = new ArrayList<School>();
        list.add(new School("Jméno školy1", "Ulice školy1", "Brno", "55", 40000));
        list.add(new School("Jméno školy2", "Ulice školy2", "Praha", "42", 30000));
        list.add(new School("Jméno školy3", "Ulice školy3", "Plzeň", "30", 40000));
        list.add(new School("Jméno školy4", "Ulice školy1", "Brno", "Krno", 40000));
        dao.persistAll(list);
    }

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

    private void addYear() {
        List<Year> list = new ArrayList<Year>();
        list.add(new Year(2013, true));
        list.add(new Year(2014, true));
        list.add(new Year(2015, true));
        dao.persistAll(list);

    }

    private void addParticipant() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("Karel", "Novy", "karel@novy.cz", "heslo"));
        list.add(new Person("Jan", "Hekz", "jan@heky.cz", "heslo"));
        list.add(new Person("Jana", "Leva", "jana@leva.cz", "heslo"));
        list.add(new Person("Veronika", "Unavena", "veronika@unavena.cz", "heslo"));
        dao.persistAll(list);
    }

    private void testNamedQuery() {
        //Query query = dao.getEm().createQuery("select e from " + Competency.class.getSimpleName() + " e");
        List results = dao.getByNamedQuery("Competency.findAll", Competency.class);
        for (Object competency : results) {
            System.out.println(competency.toString());
        }
    }

    @Transactional
    private void deleteAll() {
        dao.removeAll(Committe.class);
        //dao.removeAll(Competency.class);
        dao.removeAll(Deputy.class);
        dao.removeAll(District.class);
        dao.removeAll(Fraction.class);
        dao.removeAll(Journalist.class);
        dao.removeAll(Lobbyist.class);
        dao.removeAll(Management.class);
        dao.removeAll(Manager.class);
        dao.removeAll(Organization.class);
        dao.removeAll(Participation.class);
        dao.removeAll(Person.class);
        dao.removeAll(PoliticalParty.class);
        dao.removeAll(Rank.class);
        dao.removeAll(School.class);
        dao.removeAll(Senator.class);
        dao.removeAll(State.class);
        dao.removeAll(Year.class);

    }
}
