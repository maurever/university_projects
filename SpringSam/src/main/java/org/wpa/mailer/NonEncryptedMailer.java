package org.wpa.mailer;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * NonEncrypted mailer for sending email after registration. Use
 * org.codemonkey.simplejavamail.Email and org.codemonkey.simplejavamail.Mailer.
 *
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika
 * at maurerova.cz>
 */
@Service("nonEncryptedMailer")
@Scope(value = "singleton")
public class NonEncryptedMailer implements MyMailer {

    private String smtp;
    private int smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private String fromName;
    private String fromAddress;

    public NonEncryptedMailer() {
    }

    @Override
    public void sendMail(String recipient, String subject, String textHTML) {
        List<String> recipients = new ArrayList<String>();
        recipients.add(recipient);
        sendMail(recipients, subject, textHTML);
    }

    @Override
    public void sendMail(List<String> recipients, String subject, String textHTML) {
        Email email = new Email();
        email.setFromAddress(fromName, fromAddress);
        for (String recipient : recipients) {
            email.addRecipient(recipient, recipient, Message.RecipientType.TO);
        }
        email.setSubject(subject);
        email.setTextHTML(textHTML);
        new Mailer(smtp, smtpPort, smtpUsername, smtpPassword).sendMail(email);
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

}
