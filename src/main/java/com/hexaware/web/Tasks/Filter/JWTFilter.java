package com.hexaware.web.Tasks.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.web.Tasks.Service.JWTService;
import com.hexaware.web.Tasks.Service.UserPrincipalService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		// if there is an auth header named authorization and if it starts with Bearer 
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}
		
		// if username exists in database and user not authenticated yet then
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// getting userDetails of that user
			UserDetails userDetails = context.getBean(UserPrincipalService.class).loadUserByUsername(username);
			
			// token is valid
			if(jwtService.validateToken(token, userDetails)) {
				
				// create a new authentication object
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// setting the authentication( because it was null)
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		// now process other filters
		filterChain.doFilter(request, response);
	}

}
