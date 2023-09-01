package com.ssafy.soljigi.member.entity;

import java.time.LocalDate;

import com.ssafy.soljigi.member.dto.MemberJoinDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	public static Member createMember(MemberJoinDto memberJoinDto, PasswordEncoder passwordEncoder){
		return Member.builder()
				.username(memberJoinDto.getUsername())
				.name(memberJoinDto.getName())
				.password(passwordEncoder.encode(memberJoinDto.getPassword()))
				//Default
				.gender(Gender.MALE)
				.birthDate(memberJoinDto.getBirthDate())
				.number(memberJoinDto.getNumber())
				.educationLevel(memberJoinDto.getEducationLevel())
				.address(memberJoinDto.getAddress())
				.accountVerification(memberJoinDto.getAccountVerification())
				.careGiverNumber(memberJoinDto.getCareGiverNumber())
				//인증필요
				.accountNumber(memberJoinDto.getAccountNumber())
				.build();

	}


}
