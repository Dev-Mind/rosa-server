package com.devmind.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devmind.exception.BadCredentialsException;
import com.devmind.exception.UserNotFoundException;
import com.devmind.model.FlatView;
import com.devmind.model.member.Member;
import com.devmind.model.security.CookieService;
import com.devmind.repository.MemberRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to authenticate users
 */
@Controller
@Transactional
@RequestMapping("/app")
public class LoginController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CookieService cookieService;

    /**
     * Authenticates the user and returns the user token which has to be sent in the header of every request
     *
     * @see com.devmind.security.AuthenticationFilter
     */
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(FlatView.class)
    public ResponseEntity<Member> authenticate(HttpServletRequest request, HttpServletResponse response) {
        String[] username = request.getParameterValues("username");
        String[] password = request.getParameterValues("password");

        if (username == null || password == null) {
            throw new IllegalArgumentException("User and password are required");
        }

        Member account = memberRepository.findByLogin(username[0]);

        if (account == null) {
            throw new UserNotFoundException();
        }
        else if (!account.getToken().equals(password[0])) {
            throw new BadCredentialsException();
        }

        cookieService.setCookieInResponse(response, account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
