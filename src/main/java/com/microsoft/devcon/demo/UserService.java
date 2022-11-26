package com.microsoft.devcon.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.devcon.demo.entity.Customer;
import com.microsoft.devcon.demo.repo.CustomerRepo;

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
