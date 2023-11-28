package com.leagueofrestaurant.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @NotBlank(message = "핸드폰 번호가 입력되지 않음")
    private String phoneNumber;
    @NotBlank(message = "비밀번호가 입력되지 않음")
    private String password;
}
