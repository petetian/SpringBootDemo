package com.pt.springdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription")
@Getter @Setter @NoArgsConstructor
public class Subscription {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String subscriptionId;
    private String subscriptionName;
    private String owner;
    private String status;
    private String email;

    public Subscription(String subscriptionId, String subscriptionName, String owner, String status, String email) {
        this.subscriptionId = subscriptionId;
        this.subscriptionName = subscriptionName;
        this.owner = owner;
        this.status = status;
        this.email = email;
    }

}
