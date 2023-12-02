package com.leagueofrestaurant.web.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leagueofrestaurant.web.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoinReq {
    @NotBlank(message = "이름을 작성하세요.")
    private String name;
    @NotBlank(message = "핸드폰 번호를 작성하세요.")
    private String phoneNumber;
    @NotBlank(message = "비밀번호를 작성하세요.")
    private String password;
    @NotNull(message = "성별을 입력하세요.")
    private Gender gender;

    @NotNull(message = "생일을 입력하세요.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

}
