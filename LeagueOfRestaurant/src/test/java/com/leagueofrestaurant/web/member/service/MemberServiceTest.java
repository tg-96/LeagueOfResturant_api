package com.leagueofrestaurant.web.member.service;

import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.dto.MemberDto;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
import com.leagueofrestaurant.web.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("모든 멤버 조회")
    public void getAllMember() {
        Member member1 = new Member("a", "1234", "1234", Gender.MALE,
                LocalDate.of(1996, 05, 28), MemberType.USER);
        Member member2 = new Member("b", "1234", "1234", Gender.MALE,
                LocalDate.of(2000, 5, 28), MemberType.USER);
        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberRepository.saveAllAndFlush(memberList);

        List<MemberDto> allMember = memberService.getAllMember();
        assertThat(allMember.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("멤버아이디로 조회하기")
    public void getMemberById() {
        Member member1 = new Member("a", "1234", "1234", Gender.MALE,
                LocalDate.of(1996, 05, 28), MemberType.USER);
        Member member2 = new Member("b", "1234", "1234", Gender.MALE,
                LocalDate.of(2000, 5, 28), MemberType.USER);
        Member entity1 = memberRepository.saveAndFlush(member1);
        Member entity2 = memberRepository.saveAndFlush(member2);

        Long member1Id = entity1.getId();
        Long member2Id = entity2.getId();

        MemberDto memberDto1 = memberService.getMemberById(member1Id);
        MemberDto memberDto2 = memberService.getMemberById(member2Id);

        assertThat(memberDto1.getName()).isEqualTo("a");
        assertThat(memberDto2.getName()).isEqualTo("b");
    }

    @Test
    @DisplayName("중복검사")
    public void phoneNumDuplicated(){
        Member member1 = new Member("a", "1234", "1234", Gender.MALE,
                LocalDate.of(1996, 05, 28), MemberType.USER);

        memberRepository.saveAndFlush(member1);
        boolean duplicated = memberService.phoneNumDuplicated("1234");
        assertThat(duplicated).isTrue();
    }
    @Test
    @DisplayName("조건으로 검색")
    public void getByCondition(){
        Member member1 = new Member("a", "1234", "1234", Gender.MALE,
                LocalDate.of(1996, 05, 28), MemberType.USER);
        Member member2 = new Member("b", "1235", "1234", Gender.MALE,
                LocalDate.of(2000, 5, 28), MemberType.USER);
        Member member3 = new Member("a", "2345", "1234", Gender.FEMALE,
                LocalDate.of(2000, 5, 28), MemberType.USER);
        Member entity1 = memberRepository.saveAndFlush(member1);
        Member entity2 = memberRepository.saveAndFlush(member2);
        Member entity3 = memberRepository.saveAndFlush(member3);

        MemberSearchCondition cond1 = MemberSearchCondition.create("a",null);
        MemberSearchCondition cond2 = MemberSearchCondition.create("b",null);
        MemberSearchCondition cond3 = MemberSearchCondition.create("a","2345");

        List<MemberDto> memberDto1 = memberService.getByCondition(cond1);
        List<MemberDto> memberDto2 = memberService.getByCondition(cond2);
        List<MemberDto> memberDto3 = memberService.getByCondition(cond3);

        assertThat(memberDto1.get(0).getName()).isEqualTo("a");
        assertThat(memberDto2.get(0).getName()).isEqualTo("b");
        assertThat(memberDto3.get(0).getGender()).isEqualTo(Gender.FEMALE);
    }
}