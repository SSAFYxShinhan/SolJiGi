package com.ssafy.soljigi.member.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
