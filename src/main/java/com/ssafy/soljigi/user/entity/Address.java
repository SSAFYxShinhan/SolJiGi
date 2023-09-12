package com.ssafy.soljigi.user.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String city;
	private String dong;
	private String code;
}
