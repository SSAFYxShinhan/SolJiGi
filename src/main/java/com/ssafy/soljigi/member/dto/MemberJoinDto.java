package com.ssafy.soljigi.member.dto;

import com.ssafy.soljigi.member.entity.Address;
import com.ssafy.soljigi.member.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

    private String username;
    private String name;
    private String password;
    private Gender gender;
    private LocalDate birthDate;
    private Address address;
    private Long number;
    private Long educationLevel;
    private Long accountNumber;
    private Boolean accountVerification;
    private Long careGiverNumber;

}
