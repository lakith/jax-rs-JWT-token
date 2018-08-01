package com.arimac.projectManegement.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class User implements Serializable {
    private int user_id;
    private String name;
    private String email;
    private String position;
    private String contact_no;
    private String user_role;
    private String password;
    private String  salt;
    private int UserToken;

    public User(){

    }

    public User(String name, String email, String position, String contact_no, String user_role, String password, String salt, int userToken) {

        this.name = name;
        this.email = email;
        this.position = position;
        this.contact_no = contact_no;
        this.user_role = user_role;
        this.password = password;
        this.salt = salt;
        UserToken = userToken;
    }

    public User(String name, String email, String position, String contact_no, String user_role, String password) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.contact_no = contact_no;
        this.user_role = user_role;
        this.password = password;
    }

    public User(int user_id, String name, String email, String position, String contact_no, String user_role, String password) {
        this.user_id=user_id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.contact_no = contact_no;
        this.user_role = user_role;
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public int getUserToken() {
        return UserToken;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUserToken(int userToken) {
        UserToken = userToken;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }


    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

