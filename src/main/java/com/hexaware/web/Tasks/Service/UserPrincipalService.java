package com.hexaware.web.Tasks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.web.Tasks.Entity.Users;
import com.hexaware.web.Tasks.Repository.UserPrincipal;
import com.hexaware.web.Tasks.Repository.UserRepo;

@Component
public class UserPrincipalService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        
        return new UserPrincipal(user);
	}
	
}
