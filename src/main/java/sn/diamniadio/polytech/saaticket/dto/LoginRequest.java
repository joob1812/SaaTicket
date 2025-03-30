package sn.diamniadio.polytech.saaticket.dto;

// DTO pour la demande de connexion
public class LoginRequest {
    private String username;
    private String password;

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}