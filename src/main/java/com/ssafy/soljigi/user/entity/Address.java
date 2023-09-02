package com.ssafy.soljigi.user.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String dong;
	private String code;
}
