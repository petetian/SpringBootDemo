package com.microsoft.devcon.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.devcon.demo.entity.User;
import com.microsoft.devcon.demo.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User save(User user) {
		User newUser = userRepo.save(user);
		return newUser;
	}
}
