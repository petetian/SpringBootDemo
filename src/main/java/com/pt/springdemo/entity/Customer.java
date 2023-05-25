package com.pt.springdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter @Setter @NoArgsConstructor
public class Customer extends BaseEntity{

	private String firstName;
	private String lastName;
	private String accountNumber;
	
	public Customer(String firstName, String lastName, String accountNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
	}
}
