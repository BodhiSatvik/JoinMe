package dev.satvik.Neo4J.services;

import dev.satvik.Neo4J.models.Course;
import dev.satvik.Neo4J.repositories.CourseRepo;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Business logic, intermediary between controller and repository

@Service
public class CourseService {
	private final CourseRepo courseRepo;

	public CourseService(CourseRepo courseRepo) {
		this.courseRepo = courseRepo;
	}

	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}

	public Course getCourseByIdentifier(String identifier) {
		return courseRepo.findCourseByIdentifier(identifier)
				.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
	}
}
