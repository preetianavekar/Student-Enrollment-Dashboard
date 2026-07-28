package com.enrollment.service;

import com.enrollment.model.Enrollment;
import com.enrollment.model.Student;

import java.util.List;
import java.util.Random;

public class ResultGenerator {

    /**
     * Generates a personalized result string for a given student based on their enrollments.
     * It randomly generates an exam score (for demonstration) and formats a result.
     */
    public String generateResultString(String studentNameString, List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            return String.format("<div class='result-box info'>No enrollments found for student: %s</div>", studentNameString);
        }

        // Parse student name
        Student student = new Student(studentNameString);
        String greeting = String.format("<h3>Exam Results for %s %s</h3>", student.getFirstName(), student.getLastName());
        
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<div class='result-box'>");
        resultBuilder.append(greeting);
        resultBuilder.append("<ul class='result-list'>");

        Random random = new Random();
        int totalScore = 0;
        
        for (Enrollment e : enrollments) {
            // Generate a random score between 40 and 100 for demonstration purposes
            int score = 40 + random.nextInt(61);
            e.setScore(score);
            totalScore += score;
            
            String grade = calculateGrade(score);
            resultBuilder.append("<li>")
                         .append("<strong>").append(e.getCourseId()).append(" - ").append(e.getCourseName()).append("</strong>: ")
                         .append(score).append("% (").append(grade).append(")")
                         .append("</li>");
        }
        
        resultBuilder.append("</ul>");
        
        double average = (double) totalScore / enrollments.size();
        resultBuilder.append(String.format("<p class='summary'>Average Score: <strong>%.2f%%</strong></p>", average));
        resultBuilder.append("</div>");

        return resultBuilder.toString();
    }

    private String calculateGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}
