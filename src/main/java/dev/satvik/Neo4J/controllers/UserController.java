package dev.satvik.Neo4J.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/vi/auth")
public class UserController  {
	@GetMapping("/me")
	public String loggedInUser(Principal principal) {
		return principal.getName();
	}
}
