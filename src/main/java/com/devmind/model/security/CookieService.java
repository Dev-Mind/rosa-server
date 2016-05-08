package com.devmind.model.security;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.devmind.model.member.Member;
import com.devmind.repository.MemberRepository;
import com.devmind.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CookieService {

    @Autowired
    private MemberRepository accountRepository;

    /**
     * Create token if it does'nt exist
     */
    public void setCookieInResponse(HttpServletResponse response, Member account) {
        if (account.getToken() == null) {
            account.setToken(UUID.randomUUID().toString());
            accountRepository.save(account);
        }

        Cookie cookie = new Cookie(AuthenticationFilter.TOKEN_COOKIE_NAME, account.getToken());
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.of(1, ChronoUnit.HOURS).getSeconds());
        response.addCookie(cookie);
    }

    /**
     * Delete token in response
     */
    public void deleteCookieInResponse(HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthenticationFilter.TOKEN_COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.of(1, ChronoUnit.HOURS).getSeconds());
        response.addCookie(cookie);
    }

}
