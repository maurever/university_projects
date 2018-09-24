package org.wpa.supportOperations;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Write message to web page.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public class WebMessage {

    public static void echoMessage(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "Failed");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
