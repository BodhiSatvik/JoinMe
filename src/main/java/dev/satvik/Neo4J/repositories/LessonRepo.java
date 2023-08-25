package dev.satvik.Neo4J.repositories;

import dev.satvik.Neo4J.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LessonRepo extends Neo4jRepository<Lesson, Long> {
}
