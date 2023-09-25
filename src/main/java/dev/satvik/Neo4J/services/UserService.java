package dev.satvik.Neo4J.services;

import dev.satvik.Neo4J.models.User;
import dev.satvik.Neo4J.repositories.UserRepo;
import dev.satvik.Neo4J.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUser (CreateUserRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setUsername(request.getUsername());
		user.setRoles(request.getRoles());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepo.save(user);

		return user;
	}
}
