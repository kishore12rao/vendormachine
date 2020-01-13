package com.jio.vm.users;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetails implements UserDetailsService{

	@Autowired
	UserDAO userDAO;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.loadUserByUsername(username);

		Collection<? extends GrantedAuthority> grantedAuthorities = null;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

	}



}

