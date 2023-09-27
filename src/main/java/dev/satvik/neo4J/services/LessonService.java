package dev.satvik.neo4J.services;

import dev.satvik.neo4J.models.Lesson;
import dev.satvik.neo4J.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
	private final LessonRepository lessonRepository;

	public LessonService(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public List<Lesson> getAllLessonsByCourseIdentifier(String identifier) {
		return lessonRepository.findLessonsByCourseIdentifier(identifier);
	}
}
