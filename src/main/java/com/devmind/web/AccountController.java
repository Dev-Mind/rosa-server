package com.devmind.web;

import javax.servlet.http.HttpServletResponse;

import com.devmind.model.member.Member;
import com.devmind.model.security.CookieService;
import com.devmind.repository.MemberRepository;
import com.devmind.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller used to authenticate users
 */
@RestController
@RequestMapping("/app/account")
public class AccountController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CookieService cookieService;


    /**
     * When user wants to access to a secure page we see if he is already connected on backend
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity<Member> oauthCallback(HttpServletResponse response) {
        CurrentUser currentUser = applicationContext.getBean(CurrentUser.class);

        //If no current user we want an authentication
        if (currentUser == null || currentUser.getCredentials() == null || !currentUser.getCredentials().isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Member());
        }

        Member account = memberRepository.findByToken(currentUser.getCredentials().get().getToken());
        if (account == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Member());
        }
        cookieService.setCookieInResponse(response, account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

}
