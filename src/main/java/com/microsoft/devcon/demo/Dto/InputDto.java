package com.microsoft.devcon.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDto {
	int i;
	String s;

	@Override
	public String toString() {
		return "InputDto [i=" + i + ", s=" + s + "]";
	}

}
