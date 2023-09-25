package dev.satvik.Neo4J.controllers;

import dev.satvik.Neo4J.models.User;
import dev.satvik.Neo4J.objects.UserDTO;
import dev.satvik.Neo4J.requests.CreateUserRequest;
import dev.satvik.Neo4J.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/vi/auth")
public class UserController  {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	public String loggedInUser(Principal principal) {
		return principal.getName();
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> signUp(@RequestBody CreateUserRequest request) {
		User user = userService.createUser(request);
		UserDTO responseUser = new UserDTO(user.getName(), user.getUsername(), user.getRoles());
		return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
	}
}
