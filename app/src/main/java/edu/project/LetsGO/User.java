package edu.project.LetsGO;

/**
 * Created by JAY on 3/7/2017.
 */

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicUrl;
    private String phoneNumber;

    public User(){

    }

    public User(String firstName, String lastName, String email, String profilePicUrl, String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.profilePicUrl=profilePicUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
