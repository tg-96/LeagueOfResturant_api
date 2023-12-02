package com.leagueofrestaurant.web.member.controller;

import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.dto.MemberEditReq;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.leagueofrestaurant.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.leagueofrestaurant.web.common.SessionKey.LOGIN_SESSION_KEY;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> getAllMember() {
        return memberService.getAllMember();
    }

    @GetMapping("/members/member")
    public MemberDto getMember(HttpSession session) {
        return memberService.getMemberById((Long) session.getAttribute(LOGIN_SESSION_KEY));
    }

    @GetMapping("/members/condition")
    public List<MemberDto> getMemberByCondition(@ModelAttribute MemberSearchCondition condition) {
        return memberService.getByCondition(condition);
    }

    @PutMapping("/members/edit")
    public ResponseEntity<Void> editMemberInfo(HttpSession session, @RequestBody MemberEditReq req) {
        memberService.editMember(req, (Long) session.getAttribute(LOGIN_SESSION_KEY));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/members/delete")
    public ResponseEntity<Void> deleteMember(HttpSession session) {
        boolean success = memberService.deleteMember((Long) session.getAttribute(LOGIN_SESSION_KEY));
        if (success) {
            session.removeAttribute(LOGIN_SESSION_KEY);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/auth")
    public ResponseEntity<Void> authentication(HttpSession session) {
        return memberService.authentication(session);
    }
}
