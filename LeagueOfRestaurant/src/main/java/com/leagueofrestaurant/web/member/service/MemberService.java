package com.leagueofrestaurant.web.member.service;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.dto.MemberEditReq;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //모든 멤버 memberDto 형태로 반환
    public List<MemberDto> getAllMember() {
        List<Member> memberList = memberRepository.findAll();
        return getMemberDtoList(memberList);
    }

    public MemberDto getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return new MemberDto(member.getName(), member.getPhoneNumber()
                , member.getPassword(), member.getGender(), member.getBirthday());
    }

    /**
     * @return 중복됐으면 true, 중복 아니면 false
     */
    public boolean isDuplicated(String phoneNumber) {
        Member memberByPhoneNumber = memberRepository.findMemberByPhoneNumber(phoneNumber);
        if (memberByPhoneNumber != null) {
            return true;
        }
        return false;
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
    public boolean editMember(MemberEditReq req, Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).get();
            if (req.getName() != null)
                member.changeName(req.getName());

            if (req.getBirthday() != null)
                member.changeBirthday(req.getBirthday());

            if (req.getGender() != null)
                member.changeGender(req.getGender());

            if (req.getPassword() != null)
                member.changePassword(req.getPassword());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 멤버 전화번호와 비밀번호가 일치하는지 확인
     * 일치하면 true, 일치하지 않으면 false
     */
    public boolean checkPassword(String password, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        if (member.getPassword().equals(password)) return true;

        return false;
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


}
