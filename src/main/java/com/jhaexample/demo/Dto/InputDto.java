package com.jhaexample.demo.Dto;

public class InputDto {
	int i;
	String s;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "InputDto [i=" + i + ", s=" + s + "]";
	}
	
}
