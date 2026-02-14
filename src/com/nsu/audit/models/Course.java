package com.nsu.audit.models;

public class Course {
    private String courseCode;
    private String courseName;
    private double credits;
    private String grade;
    private String semester;
    private int attemptNumber;
    private boolean retakeFlag;
    private boolean withdrawalStatus;

    public Course(String courseCode, String courseName, double credits, String grade, 
                  String semester, int attemptNumber, boolean retakeFlag, boolean withdrawalStatus) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
        this.semester = semester;
        this.attemptNumber = attemptNumber;
        this.retakeFlag = retakeFlag;
        this.withdrawalStatus = withdrawalStatus;
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public double getCredits() { return credits; }
    public String getGrade() { return grade; }
    public String getSemester() { return semester; }
    public int getAttemptNumber() { return attemptNumber; }
    public boolean isRetake() { return retakeFlag; }
    public boolean isWithdrawn() { return withdrawalStatus; }

    public boolean isValidGrade() {
        return !grade.equals("F") && !grade.equals("I") && !grade.equals("W") && !grade.equals("X");
    }

    public boolean countsForCGPA() {
        return isValidGrade() && credits > 0;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%.1f credits, Grade: %s)", courseCode, courseName, credits, grade);
    }
}
