package com.leagueofrestaurant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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
}
