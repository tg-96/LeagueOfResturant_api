package com.leagueofrestaurant.web.member.service;

import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDto> getAllMember(){
        List<Member> memberList = memberRepository.findAll();
        Iterator<Member> iter = memberList.iterator();

        while(iter.hasNext()){

        }


        ;
    }

}
