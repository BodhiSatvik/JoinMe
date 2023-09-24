package dev.satvik.Neo4J.controllers;

import dev.satvik.Neo4J.models.Course;
import dev.satvik.Neo4J.objects.CourseDTO;
import dev.satvik.Neo4J.services.CourseService;
import dev.satvik.Neo4J.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// Controllers are the entry point of any application. They handle the HTTP requests and responses. Delegate incoming requests to other components
// of the application

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
	private final CourseService courseService;
	private final LessonService lessonService;

	public CourseController(CourseService courseService, LessonService lessonService) {
		this.courseService = courseService;
		this.lessonService = lessonService;
	}

	@GetMapping("/")
	public ResponseEntity<List<CourseDTO>> courseIndex() {
		List<Course> courses = courseService.getAllCourses();

		List<CourseDTO> responseCourses = courses.stream().map(
				(course) -> {
					CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
					responseCourse.setLessons((lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier())));

					return responseCourse;
				}
		).toList();
		return new ResponseEntity<>(responseCourses, HttpStatus.OK);
	}

	@GetMapping("/{identifier}")
	public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier) {
		Course course = courseService.getCourseByIdentifier(identifier);
		CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
		responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
		return new ResponseEntity<>(responseCourse, HttpStatus.OK);
	}
}
