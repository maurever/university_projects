package org.wpa.TestingMains;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wpa.DAOImpl.GenericDAO;
import org.wpa.DTO.CompetencyDto;
import org.wpa.DTO.ManagerDto;
import org.wpa.service.persons.ManagerService;

/**
 *
 * @author Vít Hlaváček <hlava.vit at google.com>
 */
public class ManagerTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GenericDAO dao = (GenericDAO) context.getBean("genericDAO");
    ManagerService ms = (ManagerService) context.getBean("managerServiceImpl");

    public static void main(String[] args) {
        ManagerTest test = new ManagerTest();
        test.test();
    }

    public void test() {
        getAll();

        Long id = createManager();

        getAll();
        getCompetencies(id);
        changeCompetencies(id, "1, 2, 3");
        getCompetencies(id);
        DeleteManager(id);
        getAll();

    }

    private void getAll() {
        System.out.println("----TEST PRINT ALL MANAGERS----");
        List<ManagerDto> managerDtos = ms.getAllManagers();
        for (ManagerDto mDto : managerDtos) {
            System.out.println(mDto.toString());
        }
        System.out.println("////TEST PRINT ALL MANAGERS////");

    }

    private Long createManager() {
        System.out.println("-----TEST ADD NEW MANAGER-----");
        Long id = ms.addManager("Vít", "Hlaváček", "hlavavi1@fel.cvut.cz", "HASHYATIMNEPOZIT", "1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
        System.out.println("/////TEST ADD NEW PARTICIPANT/////");
        return id;
    }

    private void DeleteManager(Long id) {
        System.out.println("-----TEST DELETE MANAGER-----");

        ms.deleteManager(id);
        System.out.println("/////TEST DELETE NEW MANAGER/////");
    }

    private void changeCompetencies(Long id, String competencies) {
        System.out.println("-----TEST CHANGE MANAGERS COMPETENCIES-----");
        ms.changeCompetencies(id, competencies);
        System.out.println("/////TEST CHANGE MANAGERS COMPETENCIES/////");
    }

    private void getCompetencies(Long id) {
        System.out.println("-----TEST GET MANAGERS COMPETENCIES-----");

        List<CompetencyDto> competencies = ms.getCompeteciesOfManagerById(id);
        for (CompetencyDto c : competencies) {
            System.out.println(c.toString());
        }
        System.out.println("/////TEST GET MANAGERS COMPETENCIES/////");
    }
}
