package com.pt.springdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subscription")
@Getter
@Setter
public class Subscription extends BaseEntity {

	private String subscriptionId;

	private String subscriptionName;

	private String owner;

	private String status;

	private String email;

	public Subscription() {
	}

	public Subscription(String subscriptionId, String subscriptionName, String owner, String status, String email) {
		this.subscriptionId = subscriptionId;
		this.subscriptionName = subscriptionName;
		this.owner = owner;
		this.status = status;
		this.email = email;
	}

}
