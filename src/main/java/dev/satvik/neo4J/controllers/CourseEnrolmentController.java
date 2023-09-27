package dev.satvik.neo4J.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import dev.satvik.neo4J.objects.CourseDTO;
import dev.satvik.neo4J.objects.CourseEnrolmentDTO;
import dev.satvik.neo4J.queryresults.CourseEnrolmentQueryResult;
import dev.satvik.neo4J.services.CourseEnrolmentService;
import dev.satvik.neo4J.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.satvik.neo4J.models.Course;
import dev.satvik.neo4J.requests.CourseEnrolmentRequest;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrolmentController {
	private final CourseEnrolmentService courseEnrolmentService;
	private final LessonService lessonService;

	public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, LessonService lessonService) {
		this.courseEnrolmentService = courseEnrolmentService;
		this.lessonService = lessonService;
	}

	@GetMapping("/")
	public ResponseEntity<List<CourseDTO>> enrollments(Principal principal) {
		List<Course> courses = courseEnrolmentService.getAllEnrolledCoursesByUsername(principal.getName());

		List<CourseDTO> responseCourses = courses.stream().map(
				(course) -> {
					CourseDTO responseCourse = new CourseDTO();

					responseCourse.setIdentifier(course.getIdentifier());
					responseCourse.setTitle(course.getTitle());
					responseCourse.setTeacher(course.getTeacher());
					responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
					responseCourse.setEnrolled(true);

					return responseCourse;
				}
		).collect(Collectors.toList());

		return new ResponseEntity<>(responseCourses, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
		CourseEnrolmentQueryResult enrolment = courseEnrolmentService.enrollIn(principal.getName(), request.getCourseIdentifier());

		CourseEnrolmentDTO responseEnrolment = new CourseEnrolmentDTO();

		responseEnrolment.setName(enrolment.getUser().getName());
		responseEnrolment.setUsername(enrolment.getUser().getUsername());
		responseEnrolment.setCourse(enrolment.getCourse());

		return new ResponseEntity<>(responseEnrolment, HttpStatus.OK);
	}
}