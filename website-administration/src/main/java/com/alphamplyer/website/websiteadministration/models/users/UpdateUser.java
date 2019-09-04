package com.alphamplyer.website.websiteadministration.models.users;

import utils.validation.email.ValidEmail;
import utils.validation.password.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UpdateUser implements IUserValidation {

    @NotNull
    @NotEmpty
    private String username;

    private String password;

    private String matchingPassword;

    @ValidEmail
    private String email;

    public UpdateUser() { }

    /**
     * Get user username
     * @return user username
     */
    public String getUsername() { return username; }

    /**
     * Get user email
     * @return user email
     */
    public String getEmail() { return email; }

    /**
     * Get user password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get matching password
     * @return matching password
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }

    @Override
    public String getPasswordForValidation() {
        return password;
    }
    @Override
    public String getMatchingPasswordForValidation() {
        return matchingPassword;
    }

    /**
     * Define user username
     * @param username user username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Define user email
     * @param email user email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Define user password
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Define matching password
     * @param matchingPassword  matching password
     */
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public String toString() {
        return "UpdateUser{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", matchingPassword='" + matchingPassword + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
