package com.enrollment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    // H2 Embedded database path: stored locally in user's home directory or app folder
    private static final String URL = "jdbc:h2:~/student_enrollment_db;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            // Load H2 Driver
            Class.forName("org.h2.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create Students Table
            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "full_name VARCHAR(100) NOT NULL" +
                    ")");

            // Create Enrollments Table
            stmt.execute("CREATE TABLE IF NOT EXISTS enrollments (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "student_name VARCHAR(100) NOT NULL, " +
                    "course_id VARCHAR(50) NOT NULL, " +
                    "course_name VARCHAR(100) NOT NULL" +
                    ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
