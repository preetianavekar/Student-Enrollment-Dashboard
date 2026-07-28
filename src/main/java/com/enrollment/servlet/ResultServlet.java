package com.enrollment.servlet;

import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.model.Enrollment;
import com.enrollment.service.ResultGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/results")
public class ResultServlet extends HttpServlet {

    private EnrollmentDAO enrollmentDAO;
    private ResultGenerator resultGenerator;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
        resultGenerator = new ResultGenerator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/results.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        
        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("error", "Please provide a valid student name.");
            request.getRequestDispatcher("/results.jsp").forward(request, response);
            return;
        }

        // 1. Fetch enrollments for the student from the database
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(fullName.trim());

        // 2. Generate personalized result string using the ResultGenerator
        String generatedHtmlResult = resultGenerator.generateResultString(fullName.trim(), enrollments);

        // 3. Set the generated result as a request attribute and forward to JSP
        request.setAttribute("generatedResult", generatedHtmlResult);
        request.setAttribute("searchedName", fullName.trim());
        
        request.getRequestDispatcher("/results.jsp").forward(request, response);
    }
}
