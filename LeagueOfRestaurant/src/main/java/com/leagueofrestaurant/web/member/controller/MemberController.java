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

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> getAllMember() {
        return memberService.getAllMember();
    }

    @GetMapping("/members/{id}")
    public MemberDto getMemberById(@PathVariable("id") Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/members")
    public List<MemberDto> getMemberByCondition(@ModelAttribute MemberSearchCondition condition) {
        return memberService.getByCondition(condition);
    }
    @PostMapping("/members/edit/{id}")
    public ResponseEntity<Void> editMemberInfo(@PathVariable("id") Long memberId, @RequestBody MemberEditReq req) {
        memberService.editMember(req, memberId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/members/delete/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id")Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/members/auth")
    public ResponseEntity<Void> authentication(HttpSession session){
        return memberService.authentication(session);
    }
}
