package dev.satvik.neo4J.services;

import dev.satvik.neo4J.models.Course;
import dev.satvik.neo4J.repositories.CourseRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Business logic, intermediary between controller and repository

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseByIdentifier(String identifier) {
		return courseRepository.findCourseByIdentifier(identifier)
				.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
	}
}
