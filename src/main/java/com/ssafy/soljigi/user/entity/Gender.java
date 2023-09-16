package com.ssafy.soljigi.user.entity;

public enum Gender {
	MALE, FEMALE;

	public static Gender isGenderType(String gender) {
		if (gender.equals("남자")) {
			return MALE;
		} else {
			return FEMALE;
		}
	}
}
