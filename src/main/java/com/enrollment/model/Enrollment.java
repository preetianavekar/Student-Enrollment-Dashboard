package com.enrollment.model;

public class Enrollment {
    private int id;
    private String studentName;
    private String courseId;
    private String courseName;
    
    // Exam score will not be persistently saved in this example, but can be added 
    // dynamically when calculating results.
    private int score; 

    public Enrollment() {}

    public Enrollment(String studentName, String courseId, String courseName) {
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
