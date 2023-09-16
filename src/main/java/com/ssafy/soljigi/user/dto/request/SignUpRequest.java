package com.ssafy.soljigi.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	private String username;
	private String password;
	private String email;
	private String grade;
	private String parentNumber;
	private String gender;
	private String birth;
	private String address;
	private String phoneNumber;
	private String accountNumber;
}
