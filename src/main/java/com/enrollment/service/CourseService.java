package com.enrollment.service;

import com.enrollment.model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    // Simulating a database or predefined list of courses using Java Collections
    private final Map<String, Course> courseDirectory = new HashMap<>();

    public CourseService() {
        // Initialize default courses
        addCourse(new Course("CS101", "Introduction to Computer Science", 4));
        addCourse(new Course("MATH201", "Calculus I", 4));
        addCourse(new Course("ENG102", "Literature and Composition", 3));
        addCourse(new Course("PHY110", "General Physics", 4));
        addCourse(new Course("HIS105", "World History", 3));
        addCourse(new Course("ART101", "Art Appreciation", 2));
    }

    private void addCourse(Course course) {
        courseDirectory.put(course.getId(), course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseDirectory.values());
    }

    public Course getCourseById(String id) {
        return courseDirectory.get(id);
    }
}
