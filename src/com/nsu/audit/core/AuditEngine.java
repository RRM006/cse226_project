package com.nsu.audit.core;

import com.nsu.audit.models.*;
import java.util.*;

public class AuditEngine {
    private Transcript transcript;
    private ProgramRequirements program;
    private CreditTallyEngine creditEngine;
    private CGPACalculator cgpaCalculator;
    private Set<String> waivedCourses;

    public AuditEngine(Transcript transcript, ProgramRequirements program) {
        this.transcript = transcript;
        this.program = program;
        this.creditEngine = new CreditTallyEngine(transcript);
        this.cgpaCalculator = new CGPACalculator(transcript);
        this.waivedCourses = new HashSet<>();
    }

    public void addWaiver(String courseCode) {
        waivedCourses.add(courseCode.toUpperCase());
        cgpaCalculator.addWaiver(courseCode);
    }

    public AuditReport performAudit() {
        CreditTallyEngine.CreditReport creditReport = creditEngine.calculateCredits();
        CGPACalculator.CGPAReport cgpaReport = cgpaCalculator.calculateCGPA();
        
        double totalCredits = creditReport.getTotalEarnedCredits();
        double cgpa = cgpaReport.getCGPA();
        
        Map<String, List<MissingCourse>> missingByCategory = new HashMap<>();
        Map<String, Integer> creditsByCategory = new HashMap<>();
        
        Set<String> completedCourses = new HashSet<>();
        for (Course c : transcript.getCourses()) {
            if (c.isValidGrade() || c.getCredits() == 0) {
                completedCourses.add(c.getCourseCode().toUpperCase());
            }
        }
        
        for (String waiver : waivedCourses) {
            completedCourses.add(waiver.toUpperCase());
        }
        
        for (Map.Entry<String, List<ProgramRequirements.CourseRequirement>> entry : 
             program.getCategories().entrySet()) {
            
            String category = entry.getKey();
            List<ProgramRequirements.CourseRequirement> required = entry.getValue();
            List<MissingCourse> missing = new ArrayList<>();
            int categoryCredits = 0;
            
            for (ProgramRequirements.CourseRequirement req : required) {
                String code = req.getCode().toUpperCase();
                
                if (!completedCourses.contains(code) && !waivedCourses.contains(code)) {
                    missing.add(new MissingCourse(req.getCode(), req.getName(), 
                               category, req.getCredits()));
                } else {
                    categoryCredits += req.getCredits();
                }
            }
            
            if (!missing.isEmpty()) {
                missingByCategory.put(category, missing);
            }
            creditsByCategory.put(category, categoryCredits);
        }
        
        List<String> missingCapstones = new ArrayList<>();
        for (String capstone : program.getCapstoneCourses()) {
            if (!completedCourses.contains(capstone.toUpperCase())) {
                missingCapstones.add(capstone);
            }
        }
        
        ElectiveStatus electiveStatus = checkElectiveTrails(completedCourses);
        
        boolean isEligible = totalCredits >= program.getTotalCreditsRequired() && 
                           cgpa >= program.getMinCGPA() &&
                           missingByCategory.isEmpty() &&
                           missingCapstones.isEmpty() &&
                           electiveStatus.isValid();
        
        return new AuditReport(isEligible, totalCredits, cgpa, cgpaReport.getAcademicStanding(),
                             missingByCategory, missingCapstones, electiveStatus, 
                             creditReport.getZeroCreditLabs(), program.getTotalCreditsRequired());
    }
    
    private ElectiveStatus checkElectiveTrails(Set<String> completedCourses) {
        Map<String, Integer> trailCounts = new HashMap<>();
        int totalElectives = 0;
        
        for (Map.Entry<String, List<String>> trail : program.getElectiveTrails().entrySet()) {
            String trailName = trail.getKey();
            List<String> courses = trail.getValue();
            int count = 0;
            
            for (String course : courses) {
                if (completedCourses.contains(course.toUpperCase())) {
                    count++;
                    totalElectives++;
                }
            }
            
            if (count > 0) {
                trailCounts.put(trailName, count);
            }
        }
        
        boolean hasConcentration = false;
        String concentrationTrail = null;
        
        for (Map.Entry<String, Integer> entry : trailCounts.entrySet()) {
            if (entry.getValue() >= 2) {
                hasConcentration = true;
                concentrationTrail = entry.getKey();
                break;
            }
        }
        
        return new ElectiveStatus(totalElectives >= 3, hasConcentration, 
                                 concentrationTrail, trailCounts);
    }

    public static class MissingCourse {
        private String code;
        private String name;
        private String category;
        private double credits;

        public MissingCourse(String code, String name, String category, double credits) {
            this.code = code;
            this.name = name;
            this.category = category;
            this.credits = credits;
        }

        public String getCode() { return code; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getCredits() { return credits; }
    }

    public static class ElectiveStatus {
        private boolean hasEnoughElectives;
        private boolean hasConcentration;
        private String concentrationTrail;
        private Map<String, Integer> trailCounts;

        public ElectiveStatus(boolean enough, boolean concentration, String trail, 
                            Map<String, Integer> counts) {
            this.hasEnoughElectives = enough;
            this.hasConcentration = concentration;
            this.concentrationTrail = trail;
            this.trailCounts = counts;
        }

        public boolean isValid() { return hasEnoughElectives && hasConcentration; }
        public boolean hasEnoughElectives() { return hasEnoughElectives; }
        public boolean hasConcentration() { return hasConcentration; }
        public String getConcentrationTrail() { return concentrationTrail; }
        public Map<String, Integer> getTrailCounts() { return trailCounts; }
    }

    public static class AuditReport {
        private boolean eligible;
        private double totalCredits;
        private double cgpa;
        private String academicStanding;
        private Map<String, List<MissingCourse>> missingByCategory;
        private List<String> missingCapstones;
        private ElectiveStatus electiveStatus;
        private int zeroCreditLabs;

        private int creditsRequired;

        public AuditReport(boolean eligible, double credits, double cgpa, String standing,
                         Map<String, List<MissingCourse>> missing, List<String> capstones,
                         ElectiveStatus electives, int labs, int creditsRequired) {
            this.eligible = eligible;
            this.totalCredits = credits;
            this.cgpa = cgpa;
            this.academicStanding = standing;
            this.missingByCategory = missing;
            this.missingCapstones = capstones;
            this.electiveStatus = electives;
            this.zeroCreditLabs = labs;
            this.creditsRequired = creditsRequired;
        }

        public boolean isEligible() { return eligible; }
        public double getTotalCredits() { return totalCredits; }
        public double getCGPA() { return cgpa; }
        public String getAcademicStanding() { return academicStanding; }
        public Map<String, List<MissingCourse>> getMissingByCategory() { return missingByCategory; }
        public List<String> getMissingCapstones() { return missingCapstones; }
        public ElectiveStatus getElectiveStatus() { return electiveStatus; }
        public int getZeroCreditLabs() { return zeroCreditLabs; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("=== GRADUATION AUDIT REPORT ===\n");
            sb.append(String.format("GRADUATION STATUS: %s\n", eligible ? "ELIGIBLE" : "NOT ELIGIBLE"));
            sb.append(String.format("CGPA: %.2f\n", cgpa));
            sb.append(String.format("Total Credits: %.1f / %d required\n", totalCredits, 
                                   creditsRequired));
            sb.append(String.format("Academic Standing: %s\n", academicStanding));
            
            if (!missingByCategory.isEmpty()) {
                sb.append("\nDEFICIENCIES:\n");
                sb.append("Missing Core Courses:\n");
                
                for (Map.Entry<String, List<MissingCourse>> entry : missingByCategory.entrySet()) {
                    sb.append(String.format("  %s:\n", entry.getKey()));
                    for (MissingCourse mc : entry.getValue()) {
                        sb.append(String.format("    - %s: %s (%.1f credits)\n", 
                            mc.getCode(), mc.getName(), mc.getCredits()));
                    }
                }
            }
            
            if (!missingCapstones.isEmpty()) {
                sb.append("\nMissing Capstone Projects:\n");
                for (String capstone : missingCapstones) {
                    sb.append(String.format("  - %s\n", capstone));
                }
            }
            
            if (!electiveStatus.isValid()) {
                sb.append("\nElective Trail Status: VIOLATION\n");
                if (!electiveStatus.hasEnoughElectives()) {
                    sb.append("  - Insufficient elective credits\n");
                }
                if (!electiveStatus.hasConcentration()) {
                    sb.append("  - Must take minimum 2 courses from one trail\n");
                }
            }
            
            return sb.toString();
        }
    }
}
