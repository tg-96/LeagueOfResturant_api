package com.leagueofrestaurant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Member {
    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private long id;
    private String name;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private MemberType type;

    public Member(String name, String phoneNumber, String password,
                  Gender gender, LocalDate birthday, MemberType type) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.type = type;
    }

    /**
     * 유저 정보 변경
     */
    public void changeName(String name){
        this.name =name;
    }
    public void changePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(String password){
        this.password = password;
    }
}
