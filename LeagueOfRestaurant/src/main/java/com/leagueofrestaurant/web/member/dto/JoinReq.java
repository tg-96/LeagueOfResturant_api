package com.leagueofrestaurant.web.member.dto;

import com.leagueofrestaurant.web.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class JoinReq {
    private String name;
    private String phoneNumber;
    private String password;
    private Gender gender;
    private LocalDate birthday;
}
