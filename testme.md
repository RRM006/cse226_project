# NSU Audit Core - Complete Testing Guide for Beginners

## What is this?
This is a tool that checks if NSU students can graduate. It looks at their grades and tells them if they passed or what they're missing.

## What do you need?
1. A computer with Java installed (most computers have this)
2. This project folder
3. Basic knowledge of how to open a terminal/command prompt

---

## STEP 1: Open Your Terminal

### Windows:
1. Press `Windows key + R`
2. Type `cmd` and press Enter
3. A black window opens - this is your terminal

### Mac:
1. Press `Command + Space`
2. Type `Terminal` and press Enter
3. A white/black window opens

### Linux:
1. Press `Ctrl + Alt + T`
2. Terminal opens

---

## STEP 2: Navigate to the Project Folder

You need to tell the terminal where your project is.

### Example:
If your project is in `Downloads/NSU_Audit_Project`, type:

```bash
cd Downloads/NSU_Audit_Project
```

### How to find your path:
**Windows:**
1. Open File Explorer
2. Go to your project folder
3. Click on the address bar at the top
4. Copy the path (like `C:\Users\YourName\Downloads\NSU_Audit_Project`)
5. In terminal type: `cd ` then paste the path

**Mac/Linux:**
1. Open Finder/File Manager
2. Go to your project folder
3. Right-click and select "Get Info" or "Properties"
4. Copy the path
5. In terminal type: `cd ` then paste the path

### Check you're in the right place:
Type:
```bash
ls
```

You should see folders like:
- `src`
- `data`
- `tests`
- `README.md`

If you see these, you're in the right place!

---

## STEP 3: Compile the Program (One Time Only)

Before you can use the tool, you need to "build" it. This turns the code into something the computer can run.

### Type this command:

```bash
javac -d bin src/com/nsu/audit/*.java src/com/nsu/audit/**/*.java
```

### What should happen:
- You might see some warnings (yellow text) - that's OK
- If you see red text with "ERROR", something went wrong
- If nothing happens and you just see the prompt, it worked!

### Check it worked:
Type:
```bash
ls bin
```

You should see a folder called `com`

---

## STEP 4: Run the Tests

There are 3 levels of tests. You can run any of them.

---

## LEVEL 1: Credit Count Test

This counts how many credits a student has earned.

### How to run:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_1.csv
```

### What you should see:
```
=== NSU AUDIT CORE - LEVEL 1 ===
Credit Tally Engine

=== CREDIT AUDIT REPORT ===
Total Earned Credits: 130.0
Valid Course Count: 48
Excluded Courses (F/I/W/X): 0
0-Credit Labs Completed: 6

RESULT: PASSED
```

### Try another test:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_2.csv
```

This one has some failing grades, so you'll see:
```
Total Earned Credits: 125.0
Excluded Courses (F/I/W/X): 3
```

### Try all Level 1 tests:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_3.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_4.csv
```

---

## LEVEL 2: GPA Calculator Test

This calculates the student's GPA (grade point average).

### How to run:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_1.csv
```

### What happens:
The program will ask you a question:
```
Waivers? (comma-separated course codes, or NONE):
```

### What to type:
**Option 1:** If the student has no waivers, type:
```
NONE
```

**Option 2:** If the student has waivers (like ENG102 and MAT116), type:
```
ENG102,MAT116
```

### What you should see (with NONE):
```
=== NSU AUDIT CORE - LEVEL 2 ===
CGPA Calculator & Waiver Handler

Waivers? (comma-separated course codes, or NONE): NONE

=== CGPA CALCULATION REPORT ===
Total Grade Points: 442.00
Total Credits Counted: 130.0
CGPA: 3.40
Academic Standing: First Class (Good Standing)

RESULT: PASSED
```

### Try with waivers:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_3.csv
```

When it asks for waivers, type:
```
ENG102,MAT116
```

You'll see the CGPA calculated without those courses.

### Try the retake test:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_2.csv
```

Type `NONE` for waivers.

You should see:
```
Retaken Courses: 3
  - CSE115: D -> A
  - MAT120: C -> B+
  - CSE225: F -> A-
```

This shows the student retook courses and improved their grades!

---

## LEVEL 3: Full Graduation Check

This checks everything: credits, GPA, required courses, electives, and capstone projects.

### How to run for BSCSE (Computer Science):

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_1.csv BSCSE data/program_knowledge_BSCSE.md
```

### What happens:
1. It asks for waivers - type `NONE` and press Enter
2. It checks everything

### What you should see (for test_L3_1.csv - a good student):
```
=== NSU AUDIT CORE - LEVEL 3 ===
Audit Engine & Deficiency Reporter

Program: BSCSE
Waivers? (comma-separated course codes, or NONE): NONE

=== GRADUATION AUDIT REPORT ===
GRADUATION STATUS: ELIGIBLE
CGPA: 3.55
Total Credits: 130.0 / 130 required
Academic Standing: Cum Laude

RESULT: PASSED - ELIGIBLE FOR GRADUATION
```

### Try a student who is missing courses:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_2.csv BSCSE data/program_knowledge_BSCSE.md
```

Type `NONE` for waivers.

You should see:
```
GRADUATION STATUS: NOT ELIGIBLE

DEFICIENCIES:
Missing Core Courses:
  University Core:
    - HIS103: Emergence of Bangladesh (3.0 credits)
  SEPS Core:
    - MAT350: Engineering Mathematics (3.0 credits)
  Major Core:
    - CSE373: Design and Analysis of Algorithms (3.0 credits)

RESULT: FAILED - DEFICIENCIES DETECTED
```

### Try a student with low GPA:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_3.csv BSCSE data/program_knowledge_BSCSE.md
```

Type `NONE` for waivers.

You should see:
```
GRADUATION STATUS: NOT ELIGIBLE
CGPA: 1.85
Academic Standing: PROBATION - Not Eligible for Graduation

RESULT: FAILED - DEFICIENCIES DETECTED
```

### Try a student with wrong electives:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_4.csv BSCSE data/program_knowledge_BSCSE.md
```

Type `NONE` for waivers.

You should see:
```
Elective Trail Status: VIOLATION
  - Must take minimum 2 courses from one trail
```

---

## Testing BSEEE (Electrical Engineering)

The tool also works for Electrical Engineering students!

### Try the complete BSEEE test:

```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/edge_cases/test_E9_BSEEE_complete.csv BSEEE data/program_knowledge_BSEEE.md
```

Type `NONE` for waivers.

This student should be ELIGIBLE with all EEE courses completed.

---

## Testing Edge Cases

Try these interesting tests:

### Empty Transcript (No courses):
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E10_empty_transcript.csv
```

Result: 0 credits

### All Failing Grades:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/edge_cases/test_E4_all_failures.csv
```
Type `NONE`

Result: 0.00 CGPA

### Perfect Student (All A's):
```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/edge_cases/test_E3_summa_cum_laude.csv
```
Type `NONE`

Result: 4.00 CGPA, "Summa Cum Laude"

### All Withdrawals:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E6_all_withdrawals.csv
```

Result: 0 credits (W grades don't count)

### Transfer Student:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E1_transfer_credits.csv
```

This shows how transfer credits are handled.

---

## Understanding the Output

### Credit Report shows:
- **Total Earned Credits**: How many credits count toward graduation
- **Valid Course Count**: How many courses passed
- **Excluded Courses**: Failed, withdrawn, or incomplete courses
- **0-Credit Labs**: Lab courses completed (don't add to credit total)

### CGPA Report shows:
- **Total Grade Points**: Sum of (grade points × credits)
- **Total Credits Counted**: Credits used in GPA calculation
- **CGPA**: Grade Point Average (should be 2.0 or higher to graduate)
- **Academic Standing**: 
  - Summa Cum Laude (3.80-4.00) - Highest honors
  - Magna Cum Laude (3.65-3.79) - High honors
  - Cum Laude (3.50-3.64) - Honors
  - First Class (3.00-3.49) - Good standing
  - Second Class (2.50-2.99) - Good standing
  - Third Class (2.00-2.49) - Good standing
  - PROBATION (Below 2.00) - Cannot graduate

### Audit Report shows:
- **GRADUATION STATUS**: ELIGIBLE or NOT ELIGIBLE
- **DEFICIENCIES**: What's missing (courses, credits, etc.)
- **Missing Core Courses**: Required courses not taken
- **Elective Trail Status**: Whether elective requirements are met

---

## Common Problems and Solutions

### Problem 1: "javac: command not found"
**Solution:** Java is not installed or not in your PATH.
- Download Java from: https://www.oracle.com/java/technologies/downloads/
- Install it
- Restart your terminal
- Try again

### Problem 2: "Could not find or load main class"
**Solution:** You're not in the right folder or the code isn't compiled.
1. Make sure you're in the project root folder (where you see src/, data/, tests/)
2. Run the compile command again (STEP 3)
3. Make sure the `bin` folder exists

### Problem 3: "File not found"
**Solution:** The test file path is wrong.
- Check that the file exists: `ls tests/level1/`
- Make sure you type the path exactly: `tests/level1/test_L1_1.csv`
- Use forward slashes `/` even on Windows

### Problem 4: Nothing happens when I type
**Solution:** The program is waiting for input.
- Level 2 and 3 ask for waivers
- Type `NONE` or course codes like `ENG102,MAT116`
- Press Enter

### Problem 5: Red error messages
**Solution:** Read the error message.
- If it says "cannot find symbol" - compilation failed, try STEP 3 again
- If it says "ArrayIndexOutOfBounds" - the CSV file might be malformed
- If it says "NullPointerException" - there's a bug in the code

---

## Quick Reference: All Commands

### Compile (do this first):
```bash
javac -d bin src/com/nsu/audit/*.java src/com/nsu/audit/**/*.java
```

### Level 1 Tests:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_1.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_2.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_3.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/level1/test_L1_4.csv
```

### Level 2 Tests:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_1.csv
# Type: NONE

java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_2.csv
# Type: NONE

java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_3.csv
# Type: ENG102,MAT116

java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_4.csv
# Type: ENG102,MAT116
```

### Level 3 Tests (BSCSE):
```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_1.csv BSCSE data/program_knowledge_BSCSE.md
# Type: NONE

java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_2.csv BSCSE data/program_knowledge_BSCSE.md
# Type: NONE

java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_3.csv BSCSE data/program_knowledge_BSCSE.md
# Type: NONE

java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_4.csv BSCSE data/program_knowledge_BSCSE.md
# Type: NONE
```

### Level 3 Test (BSEEE):
```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/edge_cases/test_E9_BSEEE_complete.csv BSEEE data/program_knowledge_BSEEE.md
# Type: NONE
```

### Edge Case Tests:
```bash
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E10_empty_transcript.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E4_all_failures.csv
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/edge_cases/test_E3_summa_cum_laude.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E6_all_withdrawals.csv
java -cp bin com.nsu.audit.NSUAuditTool level1 tests/edge_cases/test_E1_transfer_credits.csv
```

---

## Creating Your Own Test

Want to test with your own grades?

### Step 1: Copy the template
```bash
cp data/transcript_template.csv my_transcript.csv
```

### Step 2: Edit the file
Open `my_transcript.csv` in any text editor (Notepad, TextEdit, etc.)

### Step 3: Add your courses
Follow this format:
```
course_code,course_name,credits,grade,semester,attempt_number,retake_flag,withdrawal_status
CSE115,Programming Language I,4,A,Spring2023,1,false,false
MAT120,Calculus I,3,B+,Summer2023,1,false,false
```

### Step 4: Run the test
```bash
java -cp bin com.nsu.audit.NSUAuditTool level3 my_transcript.csv BSCSE data/program_knowledge_BSCSE.md
```

---

## Summary

You did it! Now you can:
1. ✅ Compile the program
2. ✅ Run Level 1 tests (credit counting)
3. ✅ Run Level 2 tests (GPA calculation with waivers)
4. ✅ Run Level 3 tests (full graduation check)
5. ✅ Test both BSCSE and BSEEE programs
6. ✅ Test edge cases
7. ✅ Create your own test files

### Remember:
- Always compile first (STEP 3)
- Level 2 and 3 ask for waivers - type `NONE` if there are none
- Use forward slashes `/` in file paths
- If you get stuck, check the "Common Problems" section

---

**Need more help?**
- Read `README.md` for technical details
- Read `testing_plan.md` for test specifications
- Check the `NSU_Audit_Core_PRD.md` for the original requirements

**Happy Testing!** 🎓
