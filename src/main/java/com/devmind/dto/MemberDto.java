package com.devmind.dto;

import java.util.Objects;

import com.devmind.model.member.Member;
import com.devmind.model.member.Speaker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * Member DTO for API HATEOAS
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class MemberDto extends ResourceSupport {

    private Long idMember;
    private String login;
    private String firstname;
    private String lastname;
    private String company;
    private String logo;
    private String shortDescription;
    private String longDescription;

    public static <T extends Member<T>> MemberDto convert(T member) {
        return new MemberDto()
                .setIdMember(member.getId())
                .setLogin(member.getLogin())
                .setFirstname(member.getFirstname())
                .setLastname(member.getLastname())
                .setCompany(member.getCompany())
                .setShortDescription(member.getShortDescription())
                .setLongDescription(member.getLongDescription());
    }

    public Speaker toSpeaker() {
        return new Speaker()
                .setId(idMember)
                .setLogin(login)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setToken(firstname)
                .setCompany(company)
                .setShortDescription(shortDescription)
                .setLongDescription(longDescription);
    }

    public Long getIdMember() {
        return idMember;
    }

    public MemberDto setIdMember(Long idMember) {
        this.idMember = idMember;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public MemberDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public MemberDto setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public MemberDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public MemberDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public MemberDto setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public MemberDto setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public MemberDto setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MemberDto that = (MemberDto) o;
        return Objects.equals(idMember, that.idMember) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idMember, login);
    }
}
