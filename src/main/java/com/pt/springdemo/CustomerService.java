package com.pt.springdemo;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.springdemo.entity.Customer;
import com.pt.springdemo.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	public Customer save(Customer customer) {
		AtomicReference<Customer> newCustomer = new AtomicReference<>(customerRepo.save(customer));
		return newCustomer.get();
	}
}
