package com.leagueofrestaurant.web.member.controller;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.dto.MemberEditReq;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.leagueofrestaurant.web.common.SessionKey.LOGIN_SESSION_KEY;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

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

    @GetMapping("/members/dupCheck/{phoneNumber}")
    public ResponseEntity<Void> PhoneNumberDupCheck(
            @PathVariable
            @Pattern(regexp = "^010\\d{8}$", message = "010으로 시작하고, '-' 없이 입력해주세요.")
            @Valid String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new LORException(ErrorCode.BLANK_PHONE_NUMBER);
        }
        memberService.phoneNumDuplicated(phoneNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/validate/{password}")
    public ResponseEntity<Void> validatePassword(@PathVariable String password, HttpSession session) {
        Member member = memberRepository.findById((Long) session.getAttribute(LOGIN_SESSION_KEY)).get();
        memberService.checkPassword(password, member);
        return ResponseEntity.ok().build();
    }
}
