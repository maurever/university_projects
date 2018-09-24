package org.wpa.mailer;

import java.util.List;

/**
 * Interface for class to send mail after registration.
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika at maurerova.cz>
 */
public interface MyMailer {
    /**
     * Send mail one recipient.
     * @param recipient
     * @param subject
     * @param textHTML 
     */
    public void sendMail(String recipient, String subject, String textHTML);
    
    /**
     * Send mail list of recepients.
     * @param recipients
     * @param subject
     * @param textHTML 
     */
    public void sendMail(List<String> recipients, String subject, String textHTML);
    
}
