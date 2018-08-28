package edu.otaviotarelho.springrestcrud.domain.dto;

import edu.otaviotarelho.springrestcrud.domain.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String email;
    private String login;
    private String name;
    private String surname;

    public UserDTO(User obj) {
        this.email = obj.getEmail();
        this.login = obj.getLogin();
        this.name = obj.getName();
        this.surname = obj.getSurname();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
