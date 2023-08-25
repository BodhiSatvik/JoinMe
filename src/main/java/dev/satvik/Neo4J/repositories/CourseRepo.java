package dev.satvik.Neo4J.repositories;

import dev.satvik.Neo4J.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.awt.*;
import java.util.Optional;

public interface CourseRepo extends Neo4jRepository<Course, Long> {
	Optional<Course> findCourseByIdentifier(String identifier);
}
