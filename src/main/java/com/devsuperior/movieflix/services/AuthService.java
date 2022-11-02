package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User athenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = repository.findByEmail(username);
			return user;
		}catch (Exception e) {
			throw new UnauthorizedException("Invalid user!");
		}
	}
	
	public void ValidateSelfOrAdmin(Long userId) {
		User user = athenticated();
		if(!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied!");
		}
	}

}
