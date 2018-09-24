package org.wpa.TestingMains;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wpa.DTO.*;
import org.wpa.DAOImpl.GenericDAO;
import org.wpa.service.persons.ManagerService;;
import org.wpa.Participation.ParticipationService;
import org.wpa.service.persons.PersonService;

/**
 *
 * @author Veronika Maurerova
 */
public class CPTest {

    //SPRING
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GenericDAO dao = (GenericDAO) context.getBean("genericDAO");
    PersonService personService = (PersonService) context.getBean("participantServiceImpl");
    ParticipationService participationService = (ParticipationService) context.getBean("participationServiceImpl");
    ManagerService managerService = (ManagerService) context.getBean("managerServiceImpl");

    public static void main(String[] args) {
        CPTest test = new CPTest();
        test.testParticipation();
    }

    public void testParticipation() {
        try {
            participationService.addLobbyist(new Long(524289), new Long(2), new Long(2), new Long(2), 2013);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    public void testPart() {
        System.out.println("----TEST PRINT ALL PARTICIPANTs----");
        List<PersonDto> listPartDto = personService.getAllPersons();
        for (PersonDto participantDto : listPartDto) {
            System.out.println(participantDto.toString());
        }
        System.out.println("----TEST PRINT ALL PARTICIPANTs----");

        System.out.println("-----TEST ADD NEW PARTICIPANT------");
        Long id = personService.addPerson("Veronika", "Maurerová", "veronika@maurerova.cz", "424242424242");
        System.out.println("-----TEST ADD NEW PARTICIPANT------");

        System.out.println("----TEST PRINT ALL PARTICIPANTs----");
        List<PersonDto> listPartDto1 = personService.getAllPersons();
        for (PersonDto participantDto : listPartDto1) {
            System.out.println(participantDto.toString());
        }
        System.out.println("----TEST PRINT ALL PARTICIPANTs----");

        System.out.println("---TEST DELETE by ID PARTICIPANT---");
        personService.deletePerson(id);
        System.out.println("---TEST DELETE by ID PARTICIPANT---");

        System.out.println("----TEST PRINT ALL PARTICIPANTs----");
        List<PersonDto> listPartDto3 = personService.getAllPersons();
        for (PersonDto participantDto : listPartDto3) {
            System.out.println(participantDto.toString());
        }
        System.out.println("----TEST PRINT ALL PARTICIPANTs----");

    }

}
