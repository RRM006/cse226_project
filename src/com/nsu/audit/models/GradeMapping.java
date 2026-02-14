package com.nsu.audit.models;

public class GradeMapping {
    private static final java.util.Map<String, Double> GRADE_POINTS = new java.util.HashMap<>();
    
    static {
        GRADE_POINTS.put("A", 4.0);
        GRADE_POINTS.put("A-", 3.7);
        GRADE_POINTS.put("B+", 3.3);
        GRADE_POINTS.put("B", 3.0);
        GRADE_POINTS.put("B-", 2.7);
        GRADE_POINTS.put("C+", 2.3);
        GRADE_POINTS.put("C", 2.0);
        GRADE_POINTS.put("C-", 1.7);
        GRADE_POINTS.put("D+", 1.3);
        GRADE_POINTS.put("D", 1.0);
        GRADE_POINTS.put("F", 0.0);
        GRADE_POINTS.put("I", 0.0);
        GRADE_POINTS.put("W", 0.0);
        GRADE_POINTS.put("X", 0.0);
    }

    public static double getGradePoints(String grade) {
        return GRADE_POINTS.getOrDefault(grade.toUpperCase(), 0.0);
    }

    public static boolean isValidGrade(String grade) {
        return !grade.equalsIgnoreCase("F") && 
               !grade.equalsIgnoreCase("I") && 
               !grade.equalsIgnoreCase("W") && 
               !grade.equalsIgnoreCase("X");
    }

    public static String getAcademicStanding(double cgpa) {
        if (cgpa >= 3.80) return "Summa Cum Laude";
        if (cgpa >= 3.65) return "Magna Cum Laude";
        if (cgpa >= 3.50) return "Cum Laude";
        if (cgpa >= 3.00) return "First Class (Good Standing)";
        if (cgpa >= 2.50) return "Second Class (Good Standing)";
        if (cgpa >= 2.00) return "Third Class (Good Standing)";
        return "PROBATION - Not Eligible for Graduation";
    }
}
