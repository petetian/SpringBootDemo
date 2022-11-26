package com.microsoft.devcon.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.devcon.demo.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
	List<Customer> findAll();
}
