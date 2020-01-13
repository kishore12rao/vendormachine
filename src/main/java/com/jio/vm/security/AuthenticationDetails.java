package com.jio.vm.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationDetails  {
 
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}