package com.nsu.audit.parsers;

import com.nsu.audit.models.ProgramRequirements;
import java.io.*;
import java.util.*;

public class ProgramKnowledgeParser {
    
    public ProgramRequirements parse(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String programName = "";
        int totalCredits = 130;
        
        ProgramRequirements program = null;
        String currentCategory = null;
        List<ProgramRequirements.CourseRequirement> currentCourses = new ArrayList<>();
        Map<String, List<String>> electiveTrails = new HashMap<>();
        List<String> currentTrail = null;
        String currentTrailName = null;
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            
            if (line.startsWith("## Program Information") || line.isEmpty()) {
                continue;
            }
            
            if (line.startsWith("- **Program Name:**")) {
                programName = extractValue(line);
                program = new ProgramRequirements(programName, totalCredits);
            }
            
            if (line.startsWith("- **Total Credits Required:**")) {
                String val = extractValue(line);
                try {
                    totalCredits = Integer.parseInt(val);
                    if (program != null) {
                        program = new ProgramRequirements(programName, totalCredits);
                    }
                } catch (NumberFormatException e) {}
            }
            
            if (line.startsWith("## ") && !line.contains("Program Information") && 
                !line.contains("Credit Distribution") && !line.contains("Waivable Courses") &&
                !line.contains("Prerequisites") && !line.contains("Grading") &&
                !line.contains("Academic Standing")) {
                
                if (currentCategory != null && !currentCourses.isEmpty()) {
                    program.addCategory(currentCategory, new ArrayList<>(currentCourses));
                    currentCourses.clear();
                }
                
                currentCategory = line.replace("## ", "").replace(" (", "_").split("_")[0];
            }
            
            if (line.startsWith("### ") && line.contains("Trail")) {
                if (currentTrailName != null && currentTrail != null) {
                    electiveTrails.put(currentTrailName, currentTrail);
                }
                currentTrailName = line.replace("### ", "").replace(" Trail", "");
                currentTrail = new ArrayList<>();
            }
            
            if (line.startsWith("| ") && !line.contains("Course Code") && 
                !line.contains("---") && !line.startsWith("| -")) {
                
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String code = parts[1].trim();
                    String name = parts[2].trim();
                    double credits = 3.0;
                    boolean waivable = line.toLowerCase().contains("waivable");
                    
                    try {
                        if (parts.length >= 4) {
                            String creditStr = parts[3].trim();
                            credits = Double.parseDouble(creditStr);
                        }
                    } catch (NumberFormatException e) {}
                    
                    if (!code.isEmpty() && !code.equals("Course Code")) {
                        if (currentTrail != null && currentCategory != null && 
                            currentCategory.toLowerCase().contains("elective")) {
                            currentTrail.add(code);
                        } else if (currentCategory != null) {
                            currentCourses.add(new ProgramRequirements.CourseRequirement(code, name, credits, waivable));
                        }
                    }
                }
            }
            
            if (line.startsWith("- ") && line.contains(":") && !line.contains("**")) {
                String[] parts = line.substring(2).split(":");
                if (parts.length >= 1) {
                    String code = parts[0].trim();
                    if (!code.isEmpty() && code.length() >= 3 && code.length() <= 10) {
                        if (currentTrail != null && currentCategory != null && 
                            currentCategory.toLowerCase().contains("elective")) {
                            currentTrail.add(code);
                        }
                    }
                }
            }
        }
        
        if (currentCategory != null && !currentCourses.isEmpty() && program != null) {
            program.addCategory(currentCategory, currentCourses);
        }
        
        if (currentTrailName != null && currentTrail != null) {
            electiveTrails.put(currentTrailName, currentTrail);
        }
        
        for (Map.Entry<String, List<String>> trail : electiveTrails.entrySet()) {
            if (program != null) {
                program.addElectiveTrail(trail.getKey(), trail.getValue());
            }
        }
        
        reader.close();
        return program;
    }
    
    private String extractValue(String line) {
        int start = line.indexOf(":") + 1;
        int end = line.indexOf("**", start);
        if (end == -1) end = line.length();
        return line.substring(start, end).replace("**", "").trim();
    }
}
