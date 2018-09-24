package org.wpa.managedBeans.userManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.DTO.PersonDto;
import org.wpa.service.persons.PersonService;
import org.wpa.supportOperations.Authenticator;

/**
 * Managed Bean for view list of persons.
 *
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
@Component("personListBean")
@Scope(value = "request")
public class PersonList extends AbstractBean {

    private List<Order> orderList;
    private String orderBy;
    private String inOrder;

    @Autowired
    private PersonService personService;

    /**
     * Check what type of ordering is set.
     * @return ASC or DESC string.
     */
    public String getOrder() {
        if (inOrder == null || inOrder.equals("DESC")) {
            return "ASC";
        } else {
            return "DESC";
        }
    }

    /**
     * Check if the user is logged in.
     * @return
     */
    public boolean isLoggedIn() {
        return Authenticator.haveCompetency(new Long(1));
    }

    
    @PostConstruct
    public void load() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        orderBy = request.getParameter("orderBy");
        inOrder = request.getParameter("inOrder");
        

        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = "id";
        }
        boolean ascending = true;
        if (inOrder == null || inOrder.isEmpty()) {
            inOrder = "ASC";
        }
        if (inOrder.equals("DESC")) {
            ascending = false;
        }
        List<PersonDto> users = personService.getAllPersons(orderBy, ascending);
        
        ArrayList<Order> listBuilder = new ArrayList<Order>();
        for (PersonDto p : users) {
            listBuilder.add(new Order(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName()));
        }
        orderList = listBuilder;
        
    }

    /**
     * Contructor
     */
    public PersonList() {
    }

    // GETTERS AND SETTERS
    public List<Order> getOrderList() {
        return orderList;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getInOrder() {
        return inOrder;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setInOrder(String inOrder) {
        this.inOrder = inOrder;
    }

    public class Order implements Serializable {

        final Long id;
        final String email;
        final String firstName;
        final String lastName;
        private Boolean permision = null;

        public Order(Long id, String email, String firstName, String lastName) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public boolean isPermitedToShowAlteringButtons() {
            if(permision!=null)return permision;
            if (!personService.isManagerById(id) && Authenticator.haveCompetency(new Long(3))) {
                permision = true;
                return permision;
            }
            permision = Authenticator.haveCompetency(new Long(100));
            return permision;
        }

        @Override
        public String toString() {
            return "ID=" + id + ", Email=" + email + ", FirstName=" + firstName + ", LastName=" + lastName;
        }
    }
}
