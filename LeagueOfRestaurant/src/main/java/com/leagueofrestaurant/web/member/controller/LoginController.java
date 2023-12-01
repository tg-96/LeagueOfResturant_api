package com.leagueofrestaurant.web.member.controller;

import com.leagueofrestaurant.web.member.dto.JoinReq;
import com.leagueofrestaurant.web.member.dto.LoginReq;
import com.leagueofrestaurant.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class LoginController {
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginReq loginReq, HttpSession session){
        memberService.login(loginReq,session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        memberService.logout(session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody @Valid JoinReq joinReq,HttpSession session){
        memberService.join(joinReq,session);
        return ResponseEntity.ok().build();
    }

}
