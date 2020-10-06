package no.trymv.fantj.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userEmail;
    private String token;

    public LoggedInUser(String userEmail, String token) {
        this.userEmail = userEmail;
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getToken() {
        return token;
    }
}