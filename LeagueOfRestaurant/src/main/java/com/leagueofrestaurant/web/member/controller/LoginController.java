package com.leagueofrestaurant.web.member.controller;

import com.leagueofrestaurant.web.common.SessionKey;
import com.leagueofrestaurant.web.member.dto.JoinReq;
import com.leagueofrestaurant.web.member.dto.LoginReq;
import com.leagueofrestaurant.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.leagueofrestaurant.web.common.SessionKey.LOGIN_SESSION_KEY;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginReq loginReq, HttpSession session, HttpServletResponse response){
        memberService.login(loginReq,session);
        Cookie sessionCookie = new Cookie(LOGIN_SESSION_KEY, session.getId());
        response.addCookie(sessionCookie);
        return ResponseEntity.ok().header(LOGIN_SESSION_KEY, session.getId()).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        memberService.logout(session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid JoinReq joinReq,HttpSession session,HttpServletResponse response){
        memberService.join(joinReq,session);
        Cookie sessionCookie = new Cookie(LOGIN_SESSION_KEY, session.getId());
        response.addCookie(sessionCookie);
        return ResponseEntity.ok().header(LOGIN_SESSION_KEY, session.getId()).build();
    }
}
