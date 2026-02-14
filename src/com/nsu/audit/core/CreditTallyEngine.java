package com.nsu.audit.core;

import com.nsu.audit.models.*;
import java.util.*;

public class CreditTallyEngine {
    private Transcript transcript;
    private List<String> excludedCourses;
    private int zeroCreditLabs;

    public CreditTallyEngine(Transcript transcript) {
        this.transcript = transcript;
        this.excludedCourses = new ArrayList<>();
        this.zeroCreditLabs = 0;
    }

    public CreditReport calculateCredits() {
        double totalEarned = 0.0;
        int validCourseCount = 0;
        excludedCourses.clear();
        zeroCreditLabs = 0;
        
        Set<String> processedCourses = new HashSet<>();
        
        for (Course course : transcript.getCourses()) {
            if (course.isWithdrawn() || course.getGrade().equals("W")) {
                excludedCourses.add(String.format("%s (%s) - %.1f credits - Withdrawn", 
                    course.getCourseCode(), course.getGrade(), course.getCredits()));
                continue;
            }
            
            if (course.getCredits() == 0) {
                if (course.isValidGrade()) {
                    zeroCreditLabs++;
                }
                continue;
            }
            
            if (!course.isValidGrade()) {
                excludedCourses.add(String.format("%s (%s) - %.1f credits", 
                    course.getCourseCode(), course.getGrade(), course.getCredits()));
                continue;
            }
            
            String courseKey = course.getCourseCode();
            if (processedCourses.contains(courseKey)) {
                continue;
            }
            
            List<Course> attempts = transcript.getCoursesByCode(courseKey);
            Course bestAttempt = getBestAttempt(attempts);
            
            if (bestAttempt != null && bestAttempt.isValidGrade()) {
                totalEarned += bestAttempt.getCredits();
                validCourseCount++;
                processedCourses.add(courseKey);
            }
        }
        
        return new CreditReport(totalEarned, validCourseCount, excludedCourses.size(), 
                               excludedCourses, zeroCreditLabs);
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

    public static class CreditReport {
        private double totalEarnedCredits;
        private int validCourseCount;
        private int excludedCourseCount;
        private List<String> excludedCourses;
        private int zeroCreditLabs;

        public CreditReport(double totalEarned, int validCount, int excludedCount, 
                           List<String> excluded, int zeroLabs) {
            this.totalEarnedCredits = totalEarned;
            this.validCourseCount = validCount;
            this.excludedCourseCount = excludedCount;
            this.excludedCourses = new ArrayList<>(excluded);
            this.zeroCreditLabs = zeroLabs;
        }

        public double getTotalEarnedCredits() { return totalEarnedCredits; }
        public int getValidCourseCount() { return validCourseCount; }
        public int getExcludedCourseCount() { return excludedCourseCount; }
        public List<String> getExcludedCourses() { return excludedCourses; }
        public int getZeroCreditLabs() { return zeroCreditLabs; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("=== CREDIT AUDIT REPORT ===\n");
            sb.append(String.format("Total Earned Credits: %.1f\n", totalEarnedCredits));
            sb.append(String.format("Valid Course Count: %d\n", validCourseCount));
            sb.append(String.format("Excluded Courses (F/I/W/X): %d\n", excludedCourseCount));
            
            if (!excludedCourses.isEmpty()) {
                for (String excluded : excludedCourses) {
                    sb.append(String.format("  - %s\n", excluded));
                }
            }
            
            sb.append(String.format("0-Credit Labs Completed: %d\n", zeroCreditLabs));
            
            return sb.toString();
        }
    }
}
