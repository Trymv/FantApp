package no.trymv.fantj.data.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    String firstName;
    String lastName;
    String userId;
    String phoneNumber;
    String email;

    public User() {
    }

    public User(JSONObject jo) throws JSONException {
        setUserid(jo.getString("userId"));
        if(jo.has("firstName")) {
            setFirstName(jo.getString("firstName"));
            System.out.println("First name is: " + this.firstName + "\n");
        }
        if(jo.has("lastName")) {
            setLastName(jo.getString("lastName"));
            System.out.println("Last name is: " + this.lastName + "\n");
        }
        if(jo.has("phoneNumber")) {
            setPhoneNumber(jo.getString("phoneNumber"));
            System.out.println("Phone number is: " + this.phoneNumber + "\n");
        }
        if(jo.has("email")) {
            setEmail(jo.getString("email"));
            System.out.println("Email is: " + this.email + "\n");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}