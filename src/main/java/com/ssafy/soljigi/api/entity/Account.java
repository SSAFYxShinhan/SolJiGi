package com.ssafy.soljigi.api.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.soljigi.user.entity.Address;
import com.ssafy.soljigi.user.entity.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNumber;
	private String productName;
	private long balance;
	private String customerName;

	private String name;
	private Address address;
	private Gender gender;
	private String phoneNumber;
	private LocalDate birthDate;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<>();

	@Builder
	public Account(String accountNumber, String productName, long balance, String customerName, String name,
		Address address, Gender gender, String phoneNumber, LocalDate birthDate) {
		this.accountNumber = accountNumber;
		this.productName = productName;
		this.balance = balance;
		this.customerName = customerName;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	protected Account() {

	}
}