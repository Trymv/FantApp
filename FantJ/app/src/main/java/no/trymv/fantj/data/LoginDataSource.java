package no.trymv.fantj.data;

import no.trymv.fantj.FantService;
import no.trymv.fantj.data.model.LoggedInUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// TODO: Rename class to DataSource?
/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        HttpURLConnection c = null;
        try {
            URL url = new URL(FantService.BASE_URL + "Fant/resources/fant/login?email=" + username + "&pwd=" + password);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if(c.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                String token = br.readLine();
                System.out.println("\nToken is: " + token + "\n");
                LoggedInUser fakeUser = new LoggedInUser(username,token);
                c.getInputStream().close(); // Why?
                return new Result.Success<>(fakeUser);
            } else {
                System.out.println("ERROR: response code " + c.getResponseCode() + " " + c.getResponseMessage());
                System.out.println(url);
            }

            return new Result.Error(new IOException("Error logging in " + c.getResponseMessage()));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        } finally {
            if(c != null) c.disconnect();
        }
    }


    public void logout() {
        // TODO: revoke authentication
    }
}