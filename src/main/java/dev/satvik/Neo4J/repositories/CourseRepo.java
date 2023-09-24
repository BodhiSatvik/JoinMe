package dev.satvik.Neo4J.repositories;

import dev.satvik.Neo4J.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.awt.*;
import java.util.Optional;

// Repositories interact with the database, so it's the middle man between the application and the database.
public interface CourseRepo extends Neo4jRepository<Course, Long> {
	Optional<Course> findCourseByIdentifier(String identifier);
}
