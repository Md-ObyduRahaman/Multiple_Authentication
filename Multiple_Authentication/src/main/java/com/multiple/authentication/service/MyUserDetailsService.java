package com.multiple.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.multiple.authentication.model.SecureUser;
import com.multiple.authentication.model.User;
import com.multiple.authentication.repo.UserDetailsRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDetailsRepo userDetailsRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User u = userDetailsRepo.findByUsername(username).orElse(new User());

		return new SecureUser(u);
	}

}
