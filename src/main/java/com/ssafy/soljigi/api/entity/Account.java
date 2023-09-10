package com.ssafy.soljigi.api.entity;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<>();

	@Builder
	public Account(String accountNumber, String productName, long balance, String customerName) {
		this.accountNumber = accountNumber;
		this.productName = productName;
		this.balance = balance;
		this.customerName = customerName;
	}

	protected Account() {

	}
}