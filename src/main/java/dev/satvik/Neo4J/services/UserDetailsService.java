package dev.satvik.Neo4J.services;

import dev.satvik.Neo4J.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	private final UserRepo userRepo;

	public UserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo
				.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
	}
}
