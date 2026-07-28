package com.enrollment.model;

public class Student {
    private int id;
    private String fullName;
    private String firstName;
    private String lastName;

    public Student() {}

    public Student(String fullName) {
        this.fullName = fullName;
        parseName(fullName);
    }

    public Student(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        parseName(fullName);
    }

    private void parseName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty()) {
            String[] parts = fullName.trim().split("\\s+");
            this.firstName = parts[0];
            if (parts.length > 1) {
                this.lastName = parts[parts.length - 1];
            } else {
                this.lastName = "";
            }
        }
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
        parseName(fullName);
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
