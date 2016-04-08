package org.academia.model;



/**
 * Created by codecadet on 15/03/16.
 */
public class User {

    private int uid;
    private String password;
    private String username;
    private String email;


    public User(){}

    public User(String username, String email, String password){
        this.username=username;
        this.password=password;
        this.email = email;

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
