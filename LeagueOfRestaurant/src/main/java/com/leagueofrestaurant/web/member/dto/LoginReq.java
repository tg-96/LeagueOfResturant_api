package com.leagueofrestaurant.web.member.dto;

import lombok.Getter;

@Getter
public class LoginReq {
    private String phoneNumber;
    private String password;
}
