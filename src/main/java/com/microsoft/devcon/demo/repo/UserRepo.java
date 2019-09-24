package com.microsoft.devcon.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.devcon.demo.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
	public List<User> findAll();
}
