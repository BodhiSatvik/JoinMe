package dev.satvik.neo4J.repositories;

import dev.satvik.neo4J.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

// Repositories interact with the database, so it's the middle man between the application and the database.
public interface CourseRepository extends Neo4jRepository<Course, Long> {
	Optional<Course> findCourseByIdentifier(String identifier);
	@Query("MATCH (:User {username: $username})-[:ENROLLED_IN]->(courses:Course) RETURN courses")
	List<Course> findAllEnrolledCoursesByUsername(String username);
}
