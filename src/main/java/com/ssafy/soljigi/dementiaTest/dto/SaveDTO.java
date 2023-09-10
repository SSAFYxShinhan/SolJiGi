package com.ssafy.soljigi.dementiaTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SaveDTO {
	private int orient;
	private int memory;
	private int attention;
	private int space;
	private int exec;
	private int lang;
	private int total;

	public int getOrient() {
		return orient;
	}
	public int getMemory() {
		return memory;
	}
	public int getAttention() {
		return attention;
	}
	public int getSpace() {
		return space;
	}
	public int getExec() {
		return exec;
	}
	public int getLang() {
		return lang;
	}
	public int getTotal() {
		return total;
	}


}