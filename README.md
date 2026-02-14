<<<<<<< HEAD
# cse226_project
Project 1 : Audit Core System
=======
# NSU Audit Core System

## Overview

The NSU Audit Core System is a command-line tool designed to automate graduation eligibility verification for North South University students. It validates student transcripts against NSU program requirements, calculates CGPA, handles waivers and retakes, and generates comprehensive deficiency reports.

## Supported Programs

- **BSCSE** - Bachelor of Science in Computer Science and Engineering (130 credits)
- **BSEEE** - Bachelor of Science in Electrical and Electronic Engineering (130 credits)

## Project Structure

```
project_root/
├── src/
│   └── com/nsu/audit/
│       ├── NSUAuditTool.java              # Main CLI entry point
│       ├── core/
│       │   ├── CreditTallyEngine.java     # Level 1: Credit calculation
│       │   ├── CGPACalculator.java        # Level 2: CGPA & waivers
│       │   └── AuditEngine.java           # Level 3: Full audit
│       ├── models/
│       │   ├── Course.java                # Course data model
│       │   ├── Transcript.java            # Transcript collection
│       │   ├── GradeMapping.java          # NSU grading scale
│       │   └── ProgramRequirements.java   # Program structure
│       └── parsers/
│           ├── TranscriptParser.java      # CSV transcript parser
│           └── ProgramKnowledgeParser.java # Knowledge file parser
├── data/
│   ├── transcript_template.csv            # Enhanced transcript template
│   ├── program_knowledge_BSCSE.md         # BSCSE requirements
│   └── program_knowledge_BSEEE.md         # BSEEE requirements
├── tests/
│   ├── level1/                            # Credit tally test cases
│   ├── level2/                            # CGPA calculation test cases
│   ├── level3/                            # Full audit test cases
│   └── edge_cases/                        # Edge case test scenarios
├── README.md                              # This file
└── testing_plan.md                        # Test documentation

```

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command line terminal

### Setup

1. Clone or extract the project to your local machine
2. Navigate to the project root directory
3. Compile the Java source files:

```bash
javac -d bin src/com/nsu/audit/*.java src/com/nsu/audit/**/*.java
```

## Usage

### Level 1: Credit Tally Engine

Calculates total valid earned credits from a transcript, excluding invalid grades and handling 0-credit labs.

```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 <transcript.csv>
```

**Example:**
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_1.csv
```

### Level 2: CGPA Calculator & Waiver Handler

Calculates weighted CGPA according to NSU grading scale, handles course retakes, and processes course waivers.

```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 <transcript.csv>
```

**Example:**
```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_2.csv
# Then enter waivers when prompted: ENG102,MAT116
```

### Level 3: Audit Engine & Deficiency Reporter

Compares student transcript against complete program requirements, identifies missing courses, checks prerequisites, and generates comprehensive audit report.

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 <transcript.csv> <program> <knowledge_file>
```

**Example:**
```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_1.csv BSCSE data/program_knowledge_BSCSE.md
# Then enter waivers when prompted: NONE
```

## Transcript Format

The transcript CSV format includes the following columns:

| Column | Description |
|--------|-------------|
| `course_code` | Course identifier (e.g., CSE115, MAT120) |
| `course_name` | Full course name |
| `credits` | Credit value (0, 1, 3, 4, etc.) |
| `grade` | Letter grade (A, A-, B+, B, ... F, I, W, X) |
| `semester` | When course was taken (e.g., Spring2023) |
| `attempt_number` | Attempt count (1 for first attempt) |
| `retake_flag` | true if this is a retake |
| `withdrawal_status` | true if course was withdrawn |

### Example Row:
```csv
CSE115,Programming Language I,4,A-,Summer2023,1,false,false
```

## NSU Grading System

| Numerical Score | Letter Grade | Grade Points |
|----------------|--------------|--------------|
| 93 and above | A | 4.0 |
| 90 - 92 | A- | 3.7 |
| 87 - 89 | B+ | 3.3 |
| 83 - 86 | B | 3.0 |
| 80 - 82 | B- | 2.7 |
| 77 - 79 | C+ | 2.3 |
| 73 - 76 | C | 2.0 |
| 70 - 72 | C- | 1.7 |
| 67 - 69 | D+ | 1.3 |
| 60 - 66 | D | 1.0 |
| Below 60 | F | 0.0 |

**Note:** Grades I, W, X are excluded from CGPA calculation and do not count toward graduation credits.

## Academic Standing

| CGPA Range | Status |
|------------|--------|
| 3.80 - 4.00 | Summa Cum Laude |
| 3.65 - 3.79 | Magna Cum Laude |
| 3.50 - 3.64 | Cum Laude |
| 3.00 - 3.49 | First Class (Good Standing) |
| 2.50 - 2.99 | Second Class (Good Standing) |
| 2.00 - 2.49 | Third Class (Good Standing) |
| **Below 2.00** | **PROBATION - Not Eligible** |

## Credit Requirements

| Category | BSCSE | BSEEE |
|----------|-------|-------|
| University Core | 34 | 34 |
| SEPS Core | 38 | 38 |
| Major Core | 42 | 42 |
| Major Capstone Design Project | 4 | 4 |
| Major Electives | 9 | 9 |
| Open Electives | 3 | 3 |
| **Total** | **130** | **130** |

## Test Cases

The project includes 24 comprehensive test files:

### Level 1 Tests (Credit Tally)
- `test_L1_1.csv` - Standard successful student (130 credits)
- `test_L1_2.csv` - Mixed grades with failures
- `test_L1_3.csv` - Incomplete and withdrawn courses
- `test_L1_4.csv` - Zero-credit labs edge case

### Level 2 Tests (CGPA)
- `test_L2_1.csv` - Standard CGPA calculation
- `test_L2_2.csv` - Retake scenario
- `test_L2_3.csv` - Course waivers
- `test_L2_4.csv` - Complex scenario (retakes + waivers + F grades)

### Level 3 Tests (Full Audit)
- `test_L3_1.csv` - Graduation ready student
- `test_L3_2.csv` - Missing core courses
- `test_L3_3.csv` - Probation status (Low CGPA)
- `test_L3_4.csv` - Elective trail violation

### Edge Cases
- `test_E1_transfer_credits.csv` - Transfer credit handling
- `test_E2_all_incompletes.csv` - All I grades
- `test_E3_summa_cum_laude.csv` - Perfect 4.0 CGPA
- `test_E4_all_failures.csv` - All F grades
- `test_E5_multiple_retakes.csv` - Multiple retakes of same course
- `test_E6_all_withdrawals.csv` - All W grades
- `test_E7_missing_capstones.csv` - Missing capstone projects
- `test_E8_prereq_violations.csv` - Prerequisite violations
- `test_E9_BSEEE_complete.csv` - Complete BSEEE transcript
- `test_E10_empty_transcript.csv` - Empty transcript
- `test_E11_all_marked_X.csv` - All X grades
- `test_E12_borderline_cgpa.csv` - Borderline CGPA scenarios

See `testing_plan.md` for detailed test specifications.

## Architecture

### Design Principles

1. **Modularity**: Each level is implemented as a separate class with clear responsibilities
2. **Extensibility**: Easy to add new programs by creating new knowledge files
3. **Reusability**: Common functionality (grade mapping, parsers) is shared
4. **Testability**: Each component can be tested independently

### Class Hierarchy

```
NSUAuditTool (Main)
├── CreditTallyEngine (Level 1)
│   └── CreditReport
├── CGPACalculator (Level 2)
│   ├── CGPAReport
│   └── RetakeInfo
└── AuditEngine (Level 3)
    ├── AuditReport
    ├── MissingCourse
    └── ElectiveStatus

Models
├── Course
├── Transcript
├── GradeMapping
└── ProgramRequirements

Parsers
├── TranscriptParser
└── ProgramKnowledgeParser
```

### Key Features

- **Retake Handling**: Only best grade counts for CGPA
- **Waiver Support**: Waived courses excluded from CGPA but count toward credits
- **0-Credit Labs**: Properly tracked but don't affect credit totals
- **Elective Trail Validation**: Ensures minimum 2 courses from one trail
- **Prerequisite Tracking**: Validates course prerequisites
- **Academic Standing**: Calculates honors and probation status

## Known Limitations

1. Program knowledge parser uses basic markdown parsing - complex formatting may not be recognized
2. Transfer credits must be manually marked in transcript
3. No semester progression validation (courses taken out of sequence not flagged)
4. Program assumes valid CSV format - malformed data may cause errors

## Future Enhancements

- PDF report generation
- Web interface
- Multi-program support (BBA, BA, etc.)
- Semester progression tracking
- What-if analysis mode
- Transfer credit database

## License

This project is for academic use as part of CSE226 coursework at North South University.

## Support

For questions or issues:
- Review the PRD document: `NSU_Audit_Core_PRD.md`
- Check test specifications: `testing_plan.md`
- Consult course instructor during office hours

