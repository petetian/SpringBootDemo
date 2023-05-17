package com.pt.springdemo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pt.springdemo.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
	List<Customer> findAll();
}
