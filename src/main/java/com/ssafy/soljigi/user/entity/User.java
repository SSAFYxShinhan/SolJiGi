package com.ssafy.soljigi.user.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import com.ssafy.soljigi.base.entity.BaseEntity;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisResultType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//입력
	@Column(unique = true)
	private String username;
	private String password;
	private int educationLevel;
	private String careGiverPhoneNumber;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String name;
	private LocalDate birthDate;
	private Address address;
	private String phoneNumber;
	private String accountNumber;

	@Builder.Default
	private LocalDateTime lastDiagnosisDate = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
	private int levelFactor;

	private int point;

	@Enumerated(EnumType.STRING)
	private Role role;

	public void increasePoint(int point) {
		this.point += point;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	public void updateLevelFactor(LocalDateTime diagnosisDate) {
		long days = Duration.between(lastDiagnosisDate, diagnosisDate).toDays();
		if (days <= 30) {
			return;
		} else if (days > 60) {
			levelFactor = 0;
		}
		levelFactor = Math.min(120, levelFactor + 10);
		lastDiagnosisDate = diagnosisDate;
	}

	public int getAge() {
		LocalDate now = LocalDate.now();
		int age = now.getYear() - birthDate.getYear();
		if (now.getMonthValue() < birthDate.getMonthValue()) {
			--age;
		} else if (now.getMonthValue() == birthDate.getMonthValue() &&
					now.getDayOfMonth() < birthDate.getDayOfMonth())
			--age;
		return age;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}