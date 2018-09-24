package org.wpa.managedBeans.userManagement;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wpa.supportOperations.Authenticator;
import org.wpa.supportOperations.WebMessage;

/**
 * Managed bean for login and logout.
 * 
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */

@Component("loginAndLogout")
@Scope(value = "request")
public class LoginAndLogout extends AbstractBean {

    public LoginAndLogout() {
    }

    public String login() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
            FacesContext.getCurrentInstance().responseComplete();
            return null;
        } catch (ServletException ex) {
            WebMessage.echoMessage(ex.getMessage());
        } catch (IOException ex) {
            WebMessage.echoMessage(ex.getMessage());
        }

        WebMessage.echoMessage("Nezadali jste údaje správně.");
        return "/UserManagement/LoginAndLogout.xhtml";
    }

    public String logout() {
        Authenticator.logout();
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return Authenticator.isLoggedIn();
    }

}
