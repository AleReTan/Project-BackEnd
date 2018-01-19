package ru.vsu.entity;

import org.springframework.stereotype.Component;

@Component
public class UserEntity {

    public static String LOGIN = "login";
    public static String PASSWORD = "password";
    public static String ROLE = "role";

    private String login;
    private String password;
    private String role;

    public UserEntity() {
    }

    public UserEntity(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return UserEntity.class.getName() + " [login: " + login + ", role: " + role + " ]";
    }
}
