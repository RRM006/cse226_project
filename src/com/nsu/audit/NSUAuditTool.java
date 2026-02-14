package com.nsu.audit;

import com.nsu.audit.core.*;
import com.nsu.audit.models.*;
import com.nsu.audit.parsers.*;
import java.io.*;
import java.util.*;

public class NSUAuditTool {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }
        
        String level = args[0].toLowerCase();
        String transcriptFile = args[1];
        
        try {
            TranscriptParser parser = new TranscriptParser();
            Transcript transcript = parser.parse(transcriptFile);
            
            switch (level) {
                case "level1":
                case "l1":
                    runLevel1(transcript);
                    break;
                    
                case "level2":
                case "l2":
                    runLevel2(transcript, args);
                    break;
                    
                case "level3":
                case "l3":
                    if (args.length < 4) {
                        System.out.println("Usage: java NSUAuditTool level3 <transcript.csv> <program> <knowledge_file>");
                        System.exit(1);
                    }
                    runLevel3(transcript, args[2], args[3]);
                    break;
                    
                default:
                    System.out.println("Unknown level: " + level);
                    printUsage();
                    System.exit(1);
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void runLevel1(Transcript transcript) {
        System.out.println("\n=== NSU AUDIT CORE - LEVEL 1 ===");
        System.out.println("Credit Tally Engine\n");
        
        CreditTallyEngine engine = new CreditTallyEngine(transcript);
        CreditTallyEngine.CreditReport report = engine.calculateCredits();
        
        System.out.println(report);
        
        boolean passed = report.getTotalEarnedCredits() > 0;
        System.out.println("RESULT: " + (passed ? "PASSED" : "FAILED"));
    }
    
    private static void runLevel2(Transcript transcript, String[] args) throws IOException {
        System.out.println("\n=== NSU AUDIT CORE - LEVEL 2 ===");
        System.out.println("CGPA Calculator & Waiver Handler\n");
        
        CGPACalculator calculator = new CGPACalculator(transcript);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Waivers? (comma-separated course codes, or NONE): ");
        String waiverInput = reader.readLine();
        
        if (!waiverInput.equalsIgnoreCase("NONE") && !waiverInput.trim().isEmpty()) {
            String[] waivers = waiverInput.split(",");
            for (String waiver : waivers) {
                calculator.addWaiver(waiver.trim());
            }
        }
        
        CGPACalculator.CGPAReport report = calculator.calculateCGPA();
        System.out.println("\n" + report);
        
        boolean passed = report.getCGPA() >= 2.0;
        System.out.println("RESULT: " + (passed ? "PASSED" : "FAILED - Below minimum CGPA"));
    }
    
    private static void runLevel3(Transcript transcript, String programName, String knowledgeFile) 
            throws IOException {
        System.out.println("\n=== NSU AUDIT CORE - LEVEL 3 ===");
        System.out.println("Audit Engine & Deficiency Reporter\n");
        System.out.println("Program: " + programName.toUpperCase());
        
        ProgramKnowledgeParser progParser = new ProgramKnowledgeParser();
        ProgramRequirements program = progParser.parse(knowledgeFile);
        
        AuditEngine engine = new AuditEngine(transcript, program);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Waivers? (comma-separated course codes, or NONE): ");
        String waiverInput = reader.readLine();
        
        if (!waiverInput.equalsIgnoreCase("NONE") && !waiverInput.trim().isEmpty()) {
            String[] waivers = waiverInput.split(",");
            for (String waiver : waivers) {
                engine.addWaiver(waiver.trim());
            }
        }
        
        AuditEngine.AuditReport report = engine.performAudit();
        System.out.println("\n" + report);
        
        String result = report.isEligible() ? "PASSED - ELIGIBLE FOR GRADUATION" 
                                           : "FAILED - DEFICIENCIES DETECTED";
        System.out.println("RESULT: " + result);
    }
    
    private static void printUsage() {
        System.out.println("NSU Audit Core Tool");
        System.out.println("Usage: java NSUAuditTool <level> <transcript.csv> [options]");
        System.out.println();
        System.out.println("Levels:");
        System.out.println("  level1 <transcript.csv>              - Credit tally");
        System.out.println("  level2 <transcript.csv>              - CGPA calculation");
        System.out.println("  level3 <transcript.csv> <prog> <kb>  - Full audit");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java NSUAuditTool level1 tests/level1/test_L1_1.csv");
        System.out.println("  java NSUAuditTool level2 tests/level2/test_L2_1.csv");
        System.out.println("  java NSUAuditTool level3 tests/level3/test_L3_1.csv BSCSE data/program_knowledge_BSCSE.md");
    }
}
