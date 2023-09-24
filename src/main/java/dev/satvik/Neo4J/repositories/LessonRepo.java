package dev.satvik.Neo4J.repositories;

import dev.satvik.Neo4J.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface LessonRepo extends Neo4jRepository<Lesson, Long> {
	@Query("MATCH (:Course {identifier: $identifier})<-[:BELONGS_TO]-(lessons:Lesson) RETURN lessons")
	List<Lesson> findLessonsByCourseIdentifier(String identifier);
}
