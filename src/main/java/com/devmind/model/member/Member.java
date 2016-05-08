package com.devmind.model.member;

import static com.devmind.util.StringUtils.*;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.devmind.model.FlatView;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.Email;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Member<T extends Member> implements Comparable<Member> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({FlatView.class})
    private Long id;

    @NotNull
    @Size(max = 100)
    private String login;

    @Email
    @Size(max = 250)
    private String email;

    @Size(max = 100)
    @JsonView(FlatView.class)
    private String firstname;

    @Size(max = 100)
    @JsonView(FlatView.class)
    private String lastname;

    @Size(max = 100)
    @JsonView({FlatView.class})
    private String company;

    @Size(max = 300)
    @JsonView({FlatView.class})
    private String shortDescription;

    @Lob
    private String longDescription;

    @Size(max = 255)
    private String token;

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getLogin() {
        return login;
    }

    public T setLogin(String login) {
        this.login = login;
        return (T) this;
    }

    public String getEmail() {
        return email;
    }

    public T setEmail(String email) {
        this.email = lowercase(email);
        return (T) this;
    }

    public String getFirstname() {
        return firstname;
    }

    public T setFirstname(String firstname) {
        this.firstname = capitalize(lowercase(firstname));
        return (T) this;
    }

    public String getLastname() {
        return lastname;
    }

    public T setLastname(String lastname) {
        this.lastname = uppercase(lastname);
        return (T) this;
    }

    public String getCompany() {
        return company;
    }

    public T setCompany(String company) {
        this.company = company;
        return (T) this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public T setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return (T) this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public T setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return (T) this;
    }

    public String getToken() {
        return token;
    }

    public T setToken(String token) {
        this.token = token;
        return (T) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(login, member.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public int compareTo(Member member) {
        return ComparisonChain.start()
                .compare(this.lastname, member.lastname)
                .compare(this.firstname, member.firstname)
                .result();
    }
}