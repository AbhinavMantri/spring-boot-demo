package com.javaprojects.course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
//	private List<Course> Courses = new ArrayList<>(Arrays.asList(
//		new Course(1, "Java", "Core Java"),
//		new Course(2, "Java.SpringBoot", "Spring Boot Framework"),
//		new Course(3, "JS", "Javascript"),
//		new Course(4, "Python", "Core Python"),
//		new Course(5, "Machine Learnig", "Machine Learning Concepts")
//	));
	
	public List<Course> getAllCourses(int topicId) {
		List<Course> courses = new ArrayList<>();
		
		courseRepository.findByTopicId(topicId).forEach(courses :: add);
		
		return courses;
	}
	
	public Course getCourse(int id) {
		//return Courses.stream().filter(t -> t.getId() == id).findFirst().get();
		return courseRepository.findById(id).get();
	}
	
	public void addCourse(Course course) {
		List<Course> courses = getAllCourses(course.getTopic().getId());
		
		if(courses.size() > 0) {
			Comparator<Course> comparator = Comparator.comparing(Course:: getId);
			Course mCourse = courses.stream().max(comparator).get();
			
			course.setId(mCourse.getId() + 1);
		} else {
			course.setId(1);
		}
		
		courseRepository.save(course);
	}
	
	public void updateCourse(int id, Course course) {
		
		course.setId(id);
		
		List<Course> courses = getAllCourses(course.getTopic().getId());
		
		OptionalInt index = IntStream.range(0, courses.size() -1).filter(i -> courses.get(i).getId() == id).findFirst();
		
		
		course.setName(course.getName() != null && course.getName() != "" ? course.getName() :  courses.get(index.getAsInt()).getName());
		course.setDescription(course.getDescription() != null && course.getDescription() != "" ? course.getDescription() :  courses.get(index.getAsInt()).getDescription());
		
		courseRepository.save(course);
	}
	
	public void deleteCourse(int id) {
		// OptionalInt index = IntStream.range(0, Courses.size() -1).filter(i -> Courses.get(i).getId() == id).findFirst();
		
		// Courses.remove(index.getAsInt());
		
		courseRepository.deleteById(id);
	}
}
