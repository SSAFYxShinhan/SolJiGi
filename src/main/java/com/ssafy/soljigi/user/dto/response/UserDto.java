package com.ssafy.soljigi.user.dto.response;

import com.ssafy.soljigi.user.entity.Address;
import com.ssafy.soljigi.user.entity.Gender;
import com.ssafy.soljigi.user.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Data
@Builder
public class UserDto {

    private static final int[] scoreCut = {0, 50, 200, 500, 700, 1000};

    private Long id;
    private String username;
    private int educationLevel;
    private String careGiverPhoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String name;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String accountNumber;
    private LocalDateTime lastDiagnosisDate;
    private int level;
    private int score;

    private int levelFactor;
    private int point;

    public static UserDto of(User user) {
        int score = (int) Math.round(user.getLevelFactor() * log2(user.getPoint() + 1));
        Address address = user.getAddress();
        String addr = "";
        if (address != null)
            addr = address.getCity() + " " + address.getDong() + " " + address.getCode();

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .educationLevel(user.getEducationLevel())
                .phoneNumber(user.getPhoneNumber())
                .careGiverPhoneNumber(user.getCareGiverPhoneNumber())
                .gender(user.getGender())
                .name(user.getName())
                .address(addr)
                .accountNumber(user.getAccountNumber())
                .lastDiagnosisDate(user.getLastDiagnosisDate())
                .levelFactor(user.getLevelFactor())
                .level(getLevel(score))
                .point(user.getPoint())
                .birthDate(user.getBirthDate())
                .build();
    }

    private static int getLevel(int score) {
        for (int i = scoreCut.length - 1; i >= 0; --i) {
            if (score > scoreCut[i]) {
                return i;
            }
        }
        return 0;
    }

    private static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }
}
