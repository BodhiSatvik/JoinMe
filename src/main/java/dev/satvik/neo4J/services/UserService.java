package dev.satvik.neo4J.services;

import dev.satvik.neo4J.models.User;
import dev.satvik.neo4J.repositories.UserRepository;
import dev.satvik.neo4J.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUser(CreateUserRequest request) {
		Optional<User> existingUser = userRepository.findUserByUsername(request.getUsername());
		if (existingUser.isPresent()) {
			throw new RuntimeException("User already Exists");
		}
		User user = new User();

		user.setName(request.getName());
		// TODO: make sure that this username doesn't exist.
		user.setUsername(request.getUsername());
		user.setRoles(request.getRoles());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepository.save(user);

		return user;
	}
}
