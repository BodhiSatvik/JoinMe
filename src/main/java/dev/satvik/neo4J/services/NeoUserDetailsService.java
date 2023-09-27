package dev.satvik.neo4J.services;

import dev.satvik.neo4J.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NeoUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	private final UserRepository userRepository;

	public NeoUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository
				.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
	}
}
