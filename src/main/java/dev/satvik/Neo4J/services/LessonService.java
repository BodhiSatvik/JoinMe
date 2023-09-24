package dev.satvik.Neo4J.services;

import dev.satvik.Neo4J.models.Lesson;
import dev.satvik.Neo4J.repositories.LessonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
	private final LessonRepo lessonRepo;

	public LessonService(LessonRepo lessonRepo) {
		this.lessonRepo = lessonRepo;
	}

	public List<Lesson> getAllLessonsByCourseIdentifier(String identifier) {
		return lessonRepo.findLessonsByCourseIdentifier(identifier);
	}
}
