package com.microsoft.devcon.demo.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.microsoft.devcon.demo.entity.User;

public interface UserRepo extends PagingAndSortingRepository<User, Long>{
	public List<User> findAll();
}
