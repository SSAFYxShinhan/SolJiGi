package com.ssafy.soljigi.user.dto.response;

import com.ssafy.soljigi.user.entity.Address;
import com.ssafy.soljigi.user.entity.Gender;
import com.ssafy.soljigi.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserInfoResponse {
    private Gender gender;
    private String phoneNumber;
    private String name;
    private LocalDate birthDate;
    private Address address;

    public static UserInfoResponse of(User user){
        return UserInfoResponse.builder()
                .address(user.getAddress())
                .birthDate(user.getBirthDate())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .build();
    }
}
