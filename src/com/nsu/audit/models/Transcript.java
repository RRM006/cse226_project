package com.nsu.audit.models;

import java.util.*;

public class Transcript {
    private List<Course> courses;
    private String studentId;
    private String program;

    public Transcript() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getCoursesByCode(String courseCode) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                result.add(c);
            }
        }
        return result;
    }

    public Set<String> getUniqueCourseCodes() {
        Set<String> codes = new HashSet<>();
        for (Course c : courses) {
            codes.add(c.getCourseCode());
        }
        return codes;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
}
