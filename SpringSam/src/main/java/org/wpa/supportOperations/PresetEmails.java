package org.wpa.supportOperations;

/**
 * Preset eamil to html before sending.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */
public class PresetEmails {

    public static String newManagerMail(String email, String password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=WordSection1>")
                .append("<h2 align=center style='text-align:center'>")
                .append("Byl vám vytvořen Organizátorský účet,")
                .append("<br>")
                .append("heslo si prosím, co nejdříve změňte")
                .append("<br>")
                .append("<br>")
                .append("Email: „")
                .append(email)
                .append("“<br>")
                .append("Heslo: „")
                .append(password)
                .append("“</h2>")
                .append("</div>");
        return stringBuilder.toString();
    }

        public static String newParticipantMail(String email, String password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=WordSection1>")
                .append("<h2 align=center style='text-align:center'>")
                .append("Byl vám vytvořen účastnický účet,")
                .append("<br>")
                .append("heslo si prosím, co nejdříve změňte")
                .append("<br>")
                .append("<br>")
                .append("Email: „")
                .append(email)
                .append("“<br>")
                .append("Heslo: „")
                .append(password)
                .append("“</h2>")
                .append("</div>");
        return stringBuilder.toString();
    }
;
    
}
