package com.enrollment.servlet;

import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.model.Course;
import com.enrollment.model.Enrollment;
import com.enrollment.model.Student;
import com.enrollment.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {

    private EnrollmentDAO enrollmentDAO;
    private CourseService courseService;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prepare courses for the dropdown
        List<Course> courses = courseService.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/enroll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String[] selectedCourseIds = request.getParameterValues("courseIds");

        if (fullName == null || fullName.trim().isEmpty() || selectedCourseIds == null || selectedCourseIds.length == 0) {
            request.setAttribute("error", "Please provide a name and select at least one course.");
            doGet(request, response);
            return;
        }

        Student student = new Student(fullName); // Parses the name

        // Save each enrollment
        for (String courseId : selectedCourseIds) {
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                Enrollment enrollment = new Enrollment(student.getFullName(), course.getId(), course.getName());
                enrollmentDAO.saveEnrollment(enrollment);
            }
        }

        request.setAttribute("successMessage", "Successfully enrolled " + student.getFullName() + " in " + selectedCourseIds.length + " course(s)!");
        doGet(request, response);
    }
}
