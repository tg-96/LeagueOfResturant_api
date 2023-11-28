package com.leagueofrestaurant.web.member.dto;

import com.leagueofrestaurant.web.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEditReq {
    private String name;
    private String password;
    private Gender gender;
    private LocalDate birthday;
}
