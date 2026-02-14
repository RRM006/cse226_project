package com.nsu.audit.models;

import java.util.*;

public class ProgramRequirements {
    private String programName;
    private int totalCreditsRequired;
    private double minCGPA;
    
    private Map<String, List<CourseRequirement>> categories;
    private Map<String, List<String>> electiveTrails;
    private List<String> capstoneCourses;
    private List<String> waivableCourses;
    private Map<String, List<String>> prerequisites;

    public ProgramRequirements(String programName, int totalCreditsRequired) {
        this.programName = programName;
        this.totalCreditsRequired = totalCreditsRequired;
        this.minCGPA = 2.0;
        this.categories = new HashMap<>();
        this.electiveTrails = new HashMap<>();
        this.capstoneCourses = new ArrayList<>();
        this.waivableCourses = new ArrayList<>();
        this.prerequisites = new HashMap<>();
    }

    public void addCategory(String categoryName, List<CourseRequirement> courses) {
        categories.put(categoryName, courses);
    }

    public void addElectiveTrail(String trailName, List<String> courses) {
        electiveTrails.put(trailName, courses);
    }

    public void addCapstoneCourse(String courseCode) {
        capstoneCourses.add(courseCode);
    }

    public void addWaivableCourse(String courseCode) {
        waivableCourses.add(courseCode);
    }

    public void addPrerequisite(String course, List<String> prereqs) {
        prerequisites.put(course, prereqs);
    }

    public String getProgramName() { return programName; }
    public int getTotalCreditsRequired() { return totalCreditsRequired; }
    public double getMinCGPA() { return minCGPA; }
    public Map<String, List<CourseRequirement>> getCategories() { return categories; }
    public Map<String, List<String>> getElectiveTrails() { return electiveTrails; }
    public List<String> getCapstoneCourses() { return capstoneCourses; }
    public List<String> getWaivableCourses() { return waivableCourses; }
    public Map<String, List<String>> getPrerequisites() { return prerequisites; }

    public static class CourseRequirement {
        private String code;
        private String name;
        private double credits;
        private boolean isWaivable;

        public CourseRequirement(String code, String name, double credits, boolean isWaivable) {
            this.code = code;
            this.name = name;
            this.credits = credits;
            this.isWaivable = isWaivable;
        }

        public String getCode() { return code; }
        public String getName() { return name; }
        public double getCredits() { return credits; }
        public boolean isWaivable() { return isWaivable; }
    }
}
