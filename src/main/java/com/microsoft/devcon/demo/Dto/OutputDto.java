package com.microsoft.devcon.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDto {
	int outputI;
	String outputS;

	public OutputDto(int i, String string) {
		this.outputI = i;
		this.outputS = string;
	}

}
