package com.pt.springdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.springdemo.entity.Customer;
import com.pt.springdemo.repo.CustomerRepo;

@Service
public class UserService {
	@Autowired
	private CustomerRepo userRepo;

	public List<Customer> findAll() {
		return userRepo.findAll();
	}

	public Customer save(Customer user) {
		Customer newUser = userRepo.save(user);
		return newUser;
	}
}
