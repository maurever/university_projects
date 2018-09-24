package org.wpa.enums;

/**
 * Enum for participations ROLEs
 * @author Vit Hlavacek <hlava.vit at google.com> &  Veronika Maurerova <veronika at maurerova.cz>
 */
public enum Role {
    LOBBYIST("Lobbista"),
    JOURNALIST("Novinář"),
    DEPUTY("Poslanec"),
    SENATOR("Senátor");

    String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }    
}
