package com.jhaexample.demo.Dto;

public class OutputDto {
	int outputI;
	String outputS;

	public OutputDto(int i, String string) {
		this.outputI = i;
		this.outputS = string;
	}

	public int getOutputI() {
		return outputI;
	}

	public void setOutputI(int outputI) {
		this.outputI = outputI;
	}

	public String getOutputS() {
		return outputS;
	}

	public void setOutputS(String outputS) {
		this.outputS = outputS;
	}

}
