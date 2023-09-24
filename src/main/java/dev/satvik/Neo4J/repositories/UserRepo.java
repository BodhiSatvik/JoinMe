package dev.satvik.Neo4J.repositories;

import dev.satvik.Neo4J.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepo extends Neo4jRepository<User, Long> {
	Optional<User> findUserByUsername(String username);
}
