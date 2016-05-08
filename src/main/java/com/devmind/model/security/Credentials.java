package com.devmind.model.security;

import java.io.Serializable;

import com.devmind.model.member.Member;

/**
 * This element is not serialized in database.
 */
public class Credentials implements Serializable {

    private String login;
    private String token;
    private String firstname;
    private String lastname;

    public static Credentials build(Member member) {
        return new Credentials()
                .setToken(member.getToken())
                .setLogin(member.getLogin())
                .setLastname(member.getLastname())
                .setFirstname(member.getFirstname());
    }

    public String getLogin() {
        return login;
    }

    public Credentials setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Credentials setToken(String token) {
        this.token = token;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Credentials setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Credentials setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
