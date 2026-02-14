package com.nsu.audit.core;

import com.nsu.audit.models.*;
import java.util.*;

public class CGPACalculator {
    private Transcript transcript;
    private Set<String> waivedCourses;
    private List<RetakeInfo> retakes;

    public CGPACalculator(Transcript transcript) {
        this.transcript = transcript;
        this.waivedCourses = new HashSet<>();
        this.retakes = new ArrayList<>();
    }

    public void addWaiver(String courseCode) {
        waivedCourses.add(courseCode.toUpperCase());
    }

    public CGPAReport calculateCGPA() {
        double totalGradePoints = 0.0;
        double totalCreditsCounted = 0.0;
        retakes.clear();
        
        Set<String> processedCourses = new HashSet<>();
        Map<String, List<Course>> courseAttempts = new HashMap<>();
        
        for (Course course : transcript.getCourses()) {
            String code = course.getCourseCode().toUpperCase();
            
            if (waivedCourses.contains(code)) {
                continue;
            }
            
            if (!courseAttempts.containsKey(code)) {
                courseAttempts.put(code, new ArrayList<>());
            }
            courseAttempts.get(code).add(course);
        }
        
        for (Map.Entry<String, List<Course>> entry : courseAttempts.entrySet()) {
            String code = entry.getKey();
            List<Course> attempts = entry.getValue();
            
            if (attempts.size() > 1) {
                Course best = getBestAttempt(attempts);
                Course worst = getWorstAttempt(attempts);
                
                if (best != null && worst != null && !best.equals(worst)) {
                    retakes.add(new RetakeInfo(code, worst.getGrade(), best.getGrade()));
                }
                
                if (best != null && best.countsForCGPA()) {
                    totalGradePoints += GradeMapping.getGradePoints(best.getGrade()) * best.getCredits();
                    totalCreditsCounted += best.getCredits();
                }
            } else {
                Course course = attempts.get(0);
                if (course.countsForCGPA()) {
                    totalGradePoints += GradeMapping.getGradePoints(course.getGrade()) * course.getCredits();
                    totalCreditsCounted += course.getCredits();
                }
            }
        }
        
        double cgpa = totalCreditsCounted > 0 ? totalGradePoints / totalCreditsCounted : 0.0;
        String standing = GradeMapping.getAcademicStanding(cgpa);
        
        return new CGPAReport(totalGradePoints, totalCreditsCounted, cgpa, standing, 
                             new ArrayList<>(waivedCourses), retakes);
    }
    
    private Course getBestAttempt(List<Course> attempts) {
        Course best = null;
        double bestPoints = -1;
        
        for (Course attempt : attempts) {
            double points = GradeMapping.getGradePoints(attempt.getGrade());
            if (points > bestPoints) {
                bestPoints = points;
                best = attempt;
            }
        }
        
        return best;
    }
    
    private Course getWorstAttempt(List<Course> attempts) {
        Course worst = null;
        double worstPoints = 5.0;
        
        for (Course attempt : attempts) {
            double points = GradeMapping.getGradePoints(attempt.getGrade());
            if (points < worstPoints) {
                worstPoints = points;
                worst = attempt;
            }
        }
        
        return worst;
    }

    public static class RetakeInfo {
        private String courseCode;
        private String oldGrade;
        private String newGrade;

        public RetakeInfo(String code, String oldGrade, String newGrade) {
            this.courseCode = code;
            this.oldGrade = oldGrade;
            this.newGrade = newGrade;
        }

        public String getCourseCode() { return courseCode; }
        public String getOldGrade() { return oldGrade; }
        public String getNewGrade() { return newGrade; }
    }

    public static class CGPAReport {
        private double totalGradePoints;
        private double totalCreditsCounted;
        private double cgpa;
        private String academicStanding;
        private List<String> waivers;
        private List<RetakeInfo> retakes;

        public CGPAReport(double points, double credits, double cgpa, String standing,
                         List<String> waivers, List<RetakeInfo> retakes) {
            this.totalGradePoints = points;
            this.totalCreditsCounted = credits;
            this.cgpa = cgpa;
            this.academicStanding = standing;
            this.waivers = waivers;
            this.retakes = retakes;
        }

        public double getTotalGradePoints() { return totalGradePoints; }
        public double getTotalCreditsCounted() { return totalCreditsCounted; }
        public double getCGPA() { return cgpa; }
        public String getAcademicStanding() { return academicStanding; }
        public List<String> getWaivers() { return waivers; }
        public List<RetakeInfo> getRetakes() { return retakes; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("=== CGPA CALCULATION REPORT ===\n");
            sb.append(String.format("Total Grade Points: %.2f\n", totalGradePoints));
            sb.append(String.format("Total Credits Counted: %.1f\n", totalCreditsCounted));
            sb.append(String.format("CGPA: %.2f\n", cgpa));
            
            if (!waivers.isEmpty()) {
                sb.append(String.format("Waivers Applied: %d\n", waivers.size()));
                for (String waiver : waivers) {
                    sb.append(String.format("  - %s\n", waiver));
                }
            }
            
            if (!retakes.isEmpty()) {
                sb.append(String.format("Retaken Courses: %d\n", retakes.size()));
                for (RetakeInfo retake : retakes) {
                    sb.append(String.format("  - %s: %s -> %s\n", 
                        retake.getCourseCode(), retake.getOldGrade(), retake.getNewGrade()));
                }
            }
            
            sb.append(String.format("Academic Standing: %s\n", academicStanding));
            
            return sb.toString();
        }
    }
}
