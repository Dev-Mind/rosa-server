package com.devmind.security;

import java.util.Optional;

import com.devmind.model.member.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Current user bean sored in the request scope
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class CurrentUser {

    private Member account;

    public Optional<Member> getCredentials() {
        return Optional.ofNullable(account);
    }

    public CurrentUser setCredentials(Member account) {
        this.account = account;
        return this;
    }
}