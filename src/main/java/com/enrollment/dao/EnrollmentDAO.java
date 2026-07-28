package com.enrollment.dao;

import com.enrollment.model.Enrollment;
import com.enrollment.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public void saveEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_name, course_id, course_name) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, enrollment.getStudentName());
            pstmt.setString(2, enrollment.getCourseId());
            pstmt.setString(3, enrollment.getCourseName());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentName) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments WHERE LOWER(student_name) = LOWER(?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentName);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentName(rs.getString("student_name"));
                e.setCourseId(rs.getString("course_id"));
                e.setCourseName(rs.getString("course_name"));
                enrollments.add(e);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return enrollments;
    }
}
