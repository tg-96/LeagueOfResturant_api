package com.leagueofrestaurant.web.member.controller;

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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
    public ResponseEntity<String> join(@RequestBody @Valid JoinReq joinReq, HttpSession session) {
        try {
            memberService.join(joinReq, session);
            return ResponseEntity.ok("회원 가입 성공");
        } catch (Exception e) {
            // 예외 발생 시 클라이언트에게 에러 메시지 반환
            String errorMessage = "회원 가입 실패: " + e.getMessage();
            e.printStackTrace(); // 예외 내용을 콘솔에 출력하는 예시
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


}
