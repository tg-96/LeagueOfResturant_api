package com.leagueofrestaurant.web.member.repository;

import com.leagueofrestaurant.web.common.CommonService;
import com.leagueofrestaurant.web.member.domain.Gender;
import com.leagueofrestaurant.web.member.domain.Member;
import com.leagueofrestaurant.web.member.domain.MemberType;
import com.leagueofrestaurant.web.member.dto.MemberSearchCondition;
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
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CommonService commonService;

    @Test
    public void 전체멤버_조회() {
        memberRepository.save(
                new Member("jeong", "010-5913-5935", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER)
        );
        memberRepository.save(
                new Member("han", "010-1593-1234", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER)
        );

        List<Member> memberList = memberRepository.findAll();
//        assertThat(memberList.get(0).getName()).isEqualTo("jeong");
//        assertThat(memberList.get(1).getName()).isEqualTo("han");
    }

    @Test
    public void 멤버_id로조회하기() {
        Member jeong = new Member("jeong", "010-5913-5935", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER);
        Member han = new Member("han", "010-1593-1234", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER);

        Member jeong_entity = memberRepository.saveAndFlush(jeong);
        Member han_entity = memberRepository.saveAndFlush(han);

        Long jeong_id = jeong_entity.getId();
        Long han_id = han_entity.getId();

        Member test_jeong = memberRepository.findById(jeong_id).get();
        Member test_han = memberRepository.findById(han_id).get();

//        assertThat(jeong_entity).isEqualTo(test_jeong);
//        assertThat(han_entity).isEqualTo(test_han);
    }

    @Test
    public void 핸드폰번호로조회() {
        Member jeong = new Member("jeong", "010-5913-5935", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER);
        Member han = new Member("han", "010-1593-1234", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER);

        Member jeong_entity = memberRepository.saveAndFlush(jeong);
        Member han_entity = memberRepository.saveAndFlush(han);

        Member jeong_phone = memberRepository.findMemberByPhoneNumber("010-5913-5935");
        Member han_phone = memberRepository.findMemberByPhoneNumber("010-1593-1234");

//        assertThat(jeong_entity).isEqualTo(jeong_phone);
//        assertThat(han_entity).isEqualTo(han_phone);
    }

    @Test
    public void 조건으로조회() {
        Member jeong = new Member("jeong", "010-5913-5935", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER);
        Member jeong2 = new Member("jeong", "010-5913-5934", "123", Gender.MALE, LocalDate.of(1996, 05, 28), MemberType.USER);
        Member han = new Member("han", "010-1593-1234", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER);
        Member han2 = new Member("han", "010-1593-1235", "321", Gender.MALE, LocalDate.of(2000, 04, 07), MemberType.USER);

        List<Member> memberList = new ArrayList<>();

        memberList.add(jeong);
        memberList.add(jeong2);
        memberList.add(han);
        memberList.add(han2);

        List<Member> list = memberRepository.saveAllAndFlush(memberList);

        //jeong,"010-5913-5935" 조건일때
        MemberSearchCondition jeong_cond = new MemberSearchCondition("jeong", "010-5913-5935");
        MemberSearchCondition jeong2_cond = new MemberSearchCondition("jeong", "010-5913-5934");
        MemberSearchCondition han_cond = new MemberSearchCondition("han", "010-1593-1234");
        MemberSearchCondition han2_cond = new MemberSearchCondition("han", "010-1593-1235");

        List<MemberSearchCondition> conditionList = new ArrayList<>();
        conditionList.add(jeong_cond);
        conditionList.add(jeong2_cond);
        conditionList.add(han_cond);
        conditionList.add(han2_cond);

        //name과 phoneNumber 둘다 조건으로 들어 있을때
        for (int i = 0; i < memberList.size(); i++) {
            List<Member> member = memberRepository.findByCondition(conditionList.get(i));
            assertThat(member.get(0)).isEqualTo(list.get(i));
        }

        //name만 조건으로 들어 있을때
        MemberSearchCondition jeong_cond2 = new MemberSearchCondition("jeong", "");
        MemberSearchCondition han_cond2 = new MemberSearchCondition("han", "");

        List<Member> member2 = memberRepository.findByCondition(jeong_cond2);
        List<Member> member3 = memberRepository.findByCondition(han_cond2);

        //jeong이라는 이름의 멤버 두명이 맞는지
        assertThat(member2.get(0)).isEqualTo(list.get(0));
        assertThat(member2.get(1)).isEqualTo(list.get(1));

        //han이라는 이름의 멤버 두명이 맞는지
        assertThat(member3.get(0)).isEqualTo(list.get(2));
        assertThat(member3.get(1)).isEqualTo(list.get(3));

    }

    @Test
    public void getPath() {
        String path = commonService.getPath("src\\main\\resources\\static\\images\\0d87be2c-82ef-4eac-83eb-5870b92d333d.png");
        System.out.println(path);
    }


}


