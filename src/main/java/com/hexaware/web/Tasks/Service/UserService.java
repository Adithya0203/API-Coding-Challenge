package com.hexaware.web.Tasks.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.web.Tasks.Entity.Users;
import com.hexaware.web.Tasks.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    
    @Autowired
	JWTService jwtService;
	
	@Autowired
	AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Add a new user
    public Users addUser(Users user) {
    	user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
    
    // Get all users
    public List<Users> getAllUsers() {
        return repo.findAll();
    }
    
    // verifying a user
 		public String verify(Users user) {
 			
 			Authentication authentication = 
 					authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
 			
 			if(authentication.isAuthenticated()) {
 				return jwtService.generateToken(user.getUsername());
 			}
 			return "fail";
 		}
}
