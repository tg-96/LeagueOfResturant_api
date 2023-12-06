package com.leagueofrestaurant.web.member.service;

import com.leagueofrestaurant.web.exception.ErrorCode;
import com.leagueofrestaurant.web.exception.LORException;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.dto.*;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import com.leagueofrestaurant.web.member.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.leagueofrestaurant.web.common.SessionKey.LOGIN_SESSION_KEY;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final Encryptor encryptor;

    //모든 멤버 memberDto 형태로 반환
    public List<MemberDto> getAllMember() {
        List<Member> memberList = memberRepository.findAll();
        return getMemberDtoList(memberList);
    }

    public MemberDto getMemberById(Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).get();
            return new MemberDto(member.getName(), member.getPhoneNumber()
                    , member.getPassword(), member.getGender(), member.getBirthday());
        } catch (NoSuchElementException e) {
            throw new LORException(ErrorCode.NO_SESSION);
        }
    }

    /**
     * @return 중복아니면 true, 중복이면 LORException
     */
    public boolean phoneNumDuplicated(String phoneNumber) {
        Member memberByPhoneNumber = memberRepository.findMemberByPhoneNumber(phoneNumber);
        if (memberByPhoneNumber == null) {
            return true;
        }
        throw new LORException(ErrorCode.PHONE_NUM_DUPLICATED);
    }

    public List<MemberDto> getByCondition(MemberSearchCondition cond) {
        List<Member> memberList = memberRepository.findByCondition(cond);
        return getMemberDtoList(memberList);
    }

    /**
     * 멤버 정보 변경
     * 정상적으로 멤버정보가 변경 되었으면 true 반환
     * 멤버정보가 변경되지 않았으면 false 반환
     */
    @Transactional
    public void editMember(MemberEditReq req, Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).get();
            if (req.getName() != null)
                member.changeName(req.getName());

            if (req.getBirthday() != null)
                member.changeBirthday(req.getBirthday());

            if (req.getGender() != null)
                member.changeGender(req.getGender());

            if (req.getPassword() != null)
                member.changePassword(encryptor.encrypt(req.getPassword()));
        } catch (NoSuchElementException e) {
            throw new LORException(ErrorCode.NO_SESSION);
        }
    }

    /**
     * 회원가입
     */
    @Transactional
    public void join(JoinReq req, HttpSession session) {
        try {
            Member memberByPhoneNumber = memberRepository.findMemberByPhoneNumber(req.getPhoneNumber());
            if (memberByPhoneNumber != null) throw new LORException(ErrorCode.ALREADY_EXISTS_USER);

            final Member member = new Member(
                    req.getName(),
                    req.getPhoneNumber(),
                    encryptor.encrypt(req.getPassword()),
                    req.getGender(),
                    req.getBirthday(),
                    MemberType.USER
            );
            Member memberEntity = memberRepository.saveAndFlush(member);
            // 세션에 키 부여
            session.setAttribute(LOGIN_SESSION_KEY, memberEntity.getId());
        } catch (Exception e) {
            throw new RuntimeException("join 문제");
        }
    }

    /**
     * 인증 (Authentication)
     * 세션이 이미 존재한다면 바로 로그인 처리.
     * 세션이 존재하지 않는다면,
     * 핸드폰 번호가 존재 하는지 파악 없으면, 예외처리
     * 비밀번호가 일치하는지 파악 일치하지 않으면, 예외처리
     */
    @Transactional
    public void login(LoginReq loginReq, HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (memberId != null) return;

        Member member = memberRepository.findMemberByPhoneNumber(loginReq.getPhoneNumber());
        if (member == null) throw new LORException(ErrorCode.NOT_EXIST_PHONE_NUMBER);
        if (checkPassword(loginReq.getPassword(), member)) {
            session.setAttribute(LOGIN_SESSION_KEY, member.getId());
        } else {
            throw new LORException(ErrorCode.PASSWORD_NOT_MATCHED);
        }
    }

    /**
     * 로그 아웃
     * 세션 제거
     */
    @Transactional
    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

    /**
     * 회원탈퇴
     * <p>
     * 멤버 soft delete
     */
    @Transactional
    public boolean deleteMember(Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).get();
            member.softDeleted();
            return true;
        } catch (IllegalArgumentException e) {
            throw new LORException(ErrorCode.FAIL_TO_DELETE);
        } catch (NoSuchElementException e) {
            throw new LORException(ErrorCode.NO_SESSION);
        }
    }
    /**
     * 인가(Authentication)
     */

    /**
     * 멤버 전화번호와 비밀번호가 일치하는지 확인
     * 일치하면 true, 일치하지 않으면 exception
     */
    public boolean checkPassword(String password, Member member) {
        if (encryptor.isMatch(password, member.getPassword())) return true;
        throw new LORException(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    /**
     * 중복코드 extraction
     */
    private static List<MemberDto> getMemberDtoList(List<Member> memberList) {
        Iterator<Member> iter = memberList.iterator();
        List<MemberDto> memberDtoList = new ArrayList<>();
        while (iter.hasNext()) {
            Member member = iter.next();
            MemberDto memberDto = new MemberDto(member.getName(), member.getPhoneNumber()
                    , member.getPassword(), member.getGender(), member.getBirthday());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    /**
     * 인가(Authentication)
     */
    public ResponseEntity<Void> authentication(HttpSession session) {
        if (session.getAttribute(LOGIN_SESSION_KEY) == null) {
            throw new LORException(ErrorCode.NO_SESSION);
        }
        return ResponseEntity.ok().build();
    }
}
