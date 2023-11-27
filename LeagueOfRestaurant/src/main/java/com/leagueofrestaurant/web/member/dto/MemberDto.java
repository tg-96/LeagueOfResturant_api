package com.leagueofrestaurant.web.member.dto;

import com.leagueofrestaurant.web.member.domain.Gender;
import lombok.Builder;

import java.time.LocalDate;

public class MemberDto {
    private String name;
    private String phoneNumber;
    private String password;
    private Gender gender;
    private LocalDate birthday;
    @Builder
    public MemberDto(String name, String phoneNumber, String password, Gender gender, LocalDate birthday) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }

}
