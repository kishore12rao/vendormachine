package com.jio.vm.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.jio.vm.users.User;
import com.jio.vm.users.UserDAOImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserDAOImpl user;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User usr = user.loadUserByUsername(username);

		if (usr != null) {
			if(usr.getUsername() != null && usr.getUsername().equalsIgnoreCase(username) && usr.getPassword() != null && usr.getPassword().equalsIgnoreCase(password)) {
				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
				grantedAuthorities.add(new SimpleGrantedAuthority(usr.getRole()));
				
				return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);//Collections.emptyList()
			}else {
				throw new BadCredentialsException("Authentication failed");
			}
		} else {
			throw new BadCredentialsException("Authentication failed");
		}
	}
	@Override
	public boolean supports(Class<?>aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}