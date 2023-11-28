package com.leagueofrestaurant.web.member.service;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //모든 멤버 memberDto 형태로 반환
    public List<MemberDto> getAllMember(){
        List<Member> memberList = memberRepository.findAll();
        return getMemberDtoList(memberList);
    }
    public MemberDto getMemberById(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        return new MemberDto(member.getName(),member.getPhoneNumber()
                ,member.getPassword(),member.getGender(),member.getBirthday());
    }
    /**
     *
     * @return 중복됐으면 true, 중복 아니면 false
     */
    public boolean isDuplicated(String phoneNumber){
        Member memberByPhoneNumber = memberRepository.findMemberByPhoneNumber(phoneNumber);
        if(memberByPhoneNumber != null){
            return true;
        }
        return false;
    }

    public List<MemberDto> getByCondition(MemberSearchCondition cond){
        List<Member> memberList = memberRepository.findByCondition(cond);
        return getMemberDtoList(memberList);
    }


    /**
     * 중복코드 extraction
     */
    private static List<MemberDto> getMemberDtoList(List<Member> memberList) {
        Iterator<Member> iter = memberList.iterator();
        List<MemberDto> memberDtoList = new ArrayList<>();
        while(iter.hasNext()){
            Member member = iter.next();
            MemberDto memberDto = new MemberDto(member.getName(), member.getPhoneNumber()
                    ,member.getPassword(),member.getGender(),member.getBirthday());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }


}
