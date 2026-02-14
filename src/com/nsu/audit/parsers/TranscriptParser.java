package com.nsu.audit.parsers;

import com.nsu.audit.models.Course;
import com.nsu.audit.models.Transcript;
import java.io.*;
import java.util.*;

public class TranscriptParser {
    
    public Transcript parse(String filePath) throws IOException {
        Transcript transcript = new Transcript();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        String line;
        boolean isFirstLine = true;
        
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            
            Course course = parseLine(line);
            if (course != null) {
                transcript.addCourse(course);
            }
        }
        
        reader.close();
        return transcript;
    }

    private Course parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) return null;
        
        try {
            String courseCode = parts[0].trim();
            String courseName = parts[1].trim();
            double credits = Double.parseDouble(parts[2].trim());
            String grade = parts[3].trim();
            String semester = parts[4].trim();
            
            int attemptNumber = 1;
            boolean retakeFlag = false;
            boolean withdrawalStatus = false;
            
            if (parts.length > 5) {
                attemptNumber = Integer.parseInt(parts[5].trim());
            }
            if (parts.length > 6) {
                retakeFlag = Boolean.parseBoolean(parts[6].trim());
            }
            if (parts.length > 7) {
                withdrawalStatus = Boolean.parseBoolean(parts[7].trim());
            }
            
            return new Course(courseCode, courseName, credits, grade, semester, 
                            attemptNumber, retakeFlag, withdrawalStatus);
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }
}
