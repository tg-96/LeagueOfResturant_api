package com.leagueofrestaurant.web.member.dto;

import com.leagueofrestaurant.web.member.domain.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberEditReq {
    private String name;
    private String password;
    private Gender gender;
    private LocalDate birthday;

    @Builder
    public MemberEditReq(String name, String password, Gender gender, LocalDate birthday) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }
}
