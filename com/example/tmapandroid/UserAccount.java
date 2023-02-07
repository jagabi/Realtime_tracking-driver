package com.example.tmapandroid;

public class UserAccount {
    private String emailid;
    private String idToken;
    private String password;

    public String getIdToken() {
        return this.idToken;
    }

    public void setIdToken(String idToken2) {
        this.idToken = idToken2;
    }

    public String getEmailid() {
        return this.emailid;
    }

    public void setEmailid(String emailid2) {
        this.emailid = emailid2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }
}
