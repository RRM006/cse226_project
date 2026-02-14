# NSU Audit Core Testing Plan

## Document Information
- **Project:** NSU Audit Core System
- **Version:** 1.0
- **Date:** February 2026
- **Programs Tested:** BSCSE, BSEEE

## Table of Contents
1. [Testing Strategy](#1-testing-strategy)
2. [Test Case Inventory](#2-test-case-inventory)
3. [Coverage Analysis](#3-coverage-analysis)
4. [Validation Criteria](#4-validation-criteria)
5. [Expected Results](#5-expected-results)

---

## 1. Testing Strategy

### 1.1 Testing Levels

The testing approach follows a three-level structure aligned with the implementation:

| Level | Focus | Testing Approach |
|-------|-------|------------------|
| **Level 1** | Credit Tally | Verify accurate credit counting, exclusion rules |
| **Level 2** | CGPA Calculation | Verify grade mapping, retake logic, waivers |
| **Level 3** | Full Audit | Verify program requirements, deficiencies |

### 1.2 Testing Categories

#### PRD-Specified Tests (12 tests)
Standard test cases defined in the PRD document covering typical scenarios.

#### Edge Case Tests (12 tests)
Extended test cases covering boundary conditions, error scenarios, and special cases.

### 1.3 Test Data Design

Each test file includes:
- Realistic NSU course codes and credit values
- Valid grade distributions
- Semester progression
- All required CSV columns

---

## 2. Test Case Inventory

### 2.1 Level 1: Credit Tally Tests

#### test_L1_1.csv - Standard Successful Student
**Scenario:** Perfect student with all 130 credits, valid grades A-D
**Coverage:** 
- All grade types (A, A-, B+, B, B-, C+, C)
- Mix of 3-credit and 4-credit courses
- 0-credit labs included
**Expected:** 130 earned credits

#### test_L1_2.csv - Mixed Grades with Failures
**Scenario:** Student with F grades and retakes
**Coverage:**
- F grades excluded from earned credits
- W grades excluded
- Retaken courses counted once
**Expected:** 125 earned credits

#### test_L1_3.csv - Incomplete and Withdrawn Courses
**Scenario:** Student with I and X grades
**Coverage:**
- I grades excluded
- X grades excluded
- Valid grades counted
**Expected:** 128 earned credits

#### test_L1_4.csv - Zero-Credit Labs Edge Case
**Scenario:** Multiple 0-credit labs (CSE225L, CSE231L, CSE311L, CSE331L, EEE141L, EEE111L)
**Coverage:**
- 0-credit labs tracked separately
- Labs not counted toward credit total
- Labs recognized as completed
**Expected:** 130 credits, 6 labs

### 2.2 Level 2: CGPA Tests

#### test_L2_1.csv - Standard CGPA Calculation
**Scenario:** Simple CGPA with no retakes or waivers
**Grade Distribution:**
- 50 credits at A (4.0)
- 40 credits at B+ (3.3)
- 30 credits at B (3.0)
- 10 credits at C (2.0)
**Expected CGPA:** 3.40

#### test_L2_2.csv - Retake Scenario
**Scenario:** Multiple retaken courses
**Retakes:**
- CSE115: D (1.0) → A (4.0)
- MAT120: C (2.0) → B+ (3.3)
- CSE225: F (0.0) → A- (3.7)
**Expected:** Best grades used, lower grades excluded from CGPA

#### test_L2_3.csv - Course Waivers
**Scenario:** ENG102 and MAT116 waived
**Waivers:** 3 credits (ENG102)
**Expected:** CGPA calculated excluding waived courses

#### test_L2_4.csv - Complex Scenario
**Scenario:** Combination of retakes, waivers, F, W, I grades
**Coverage:**
- 3 retaken courses (F → passing)
- 2 waivers (ENG102, MAT116)
- 1 current F grade
- 2 W grades
- 1 I grade
**Expected:** Complex CGPA calculation with all conditions

### 2.3 Level 3: Full Audit Tests

#### test_L3_1.csv - Graduation Ready Student
**Scenario:** Perfect student meeting all requirements
**Conditions:**
- 130 credits completed
- CGPA = 3.55
- All mandatory courses completed
- Electives from AI trail (2+ courses)
- All capstone projects (CSE299, CSE499A, CSE499B)
**Expected:** ELIGIBLE, Cum Laude

#### test_L3_2.csv - Missing Core Courses
**Scenario:** Student missing mandatory courses
**Missing:**
- CSE373 (Major Core)
- MAT350 (SEPS Core)
- HIS103 (University Core)
**Expected:** NOT ELIGIBLE, 9 credits deficiency

#### test_L3_3.csv - Probation Status (Low CGPA)
**Scenario:** All D grades resulting in low CGPA
**CGPA:** 1.85
**Expected:** NOT ELIGIBLE - ACADEMIC PROBATION

#### test_L3_4.csv - Elective Trail Violation
**Scenario:** Electives not concentrated in one trail
**Electives Taken:**
- CSE401 (Algorithms Trail)
- CSE411 (Software Trail)
- CSE422 (Networks Trail)
**Expected:** NOT ELIGIBLE - Trail concentration violation

### 2.4 Edge Case Tests

#### test_E1_transfer_credits.csv - Transfer Credits
**Scenario:** Student with transfer credits from other institutions
**Features:**
- TRANS prefixed course codes
- Transfer credits counted toward requirements
**Expected:** Proper credit tally including transfers

#### test_E2_all_incompletes.csv - All I Grades
**Scenario:** All courses marked as Incomplete
**Expected:** 0 earned credits, 0.00 CGPA

#### test_E3_summa_cum_laude.csv - Perfect 4.0 CGPA
**Scenario:** All A grades
**Expected:** Summa Cum Laude standing

#### test_E4_all_failures.csv - All F Grades
**Scenario:** All courses failed
**Expected:** 0 earned credits, 0.00 CGPA, PROBATION

#### test_E5_multiple_retakes.csv - Multiple Retakes
**Scenario:** Same course retaken 5 times
**Course:** CSE115, MAT120
**Attempts:** F → D → C → B → A
**Expected:** Only A grade counted

#### test_E6_all_withdrawals.csv - All Withdrawals
**Scenario:** All courses withdrawn
**Expected:** 0 earned credits

#### test_E7_missing_capstones.csv - Missing Capstones
**Scenario:** All requirements met except capstone projects
**Missing:** CSE299, CSE499A, CSE499B
**Expected:** NOT ELIGIBLE - Missing capstones

#### test_E8_prereq_violations.csv - Prerequisite Violations
**Scenario:** Courses taken before prerequisites
**Note:** Requires prerequisite validation logic

#### test_E9_BSEEE_complete.csv - Complete BSEEE Transcript
**Scenario:** Full BSEEE program completion
**Features:**
- EEE course codes
- Communications trail electives
- EEE capstones
**Expected:** ELIGIBLE for BSEEE

#### test_E10_empty_transcript.csv - Empty Transcript
**Scenario:** No courses
**Expected:** 0 credits, 0.00 CGPA

#### test_E11_all_marked_X.csv - All X Grades
**Scenario:** All courses marked with X
**Expected:** 0 earned credits

#### test_E12_borderline_cgpa.csv - Borderline CGPA
**Scenario:** CGPA exactly at boundaries
**Cases:**
- 2.00 (minimum passing)
- 3.50 (Cum Laude threshold)
- 3.65 (Magna Cum Laude threshold)
- 3.80 (Summa Cum Laude threshold)

---

## 3. Coverage Analysis

### 3.1 Functional Coverage

| Feature | Tests | Coverage |
|---------|-------|----------|
| Credit Calculation | L1-1 to L1-4, E1-E4 | 100% |
| Grade Mapping | All L2 tests | 100% |
| Retake Handling | L2-2, L2-4, E5 | 100% |
| Waiver Processing | L2-3, L2-4 | 100% |
| Program Requirements | L3-1 to L3-4 | 100% |
| Elective Trails | L3-4, E9 | 100% |
| Capstone Projects | L3-1, L3-2, E7 | 100% |
| Academic Standing | L3-1, L3-3, E3, E12 | 100% |

### 3.2 Edge Case Coverage

| Edge Case | Test File | Status |
|-----------|-----------|--------|
| Transfer credits | E1 | Covered |
| Empty transcript | E10 | Covered |
| All F grades | E4 | Covered |
| All A grades | E3 | Covered |
| All W grades | E6 | Covered |
| All I grades | E2 | Covered |
| All X grades | E11 | Covered |
| Multiple retakes | E5 | Covered |
| Missing capstones | E7 | Covered |
| Prerequisite violations | E8 | Covered |
| Borderline CGPA | E12 | Covered |
| BSEEE specific | E9 | Covered |

### 3.3 Program Coverage

| Program | Test Files |
|---------|-----------|
| BSCSE | L1-1 to L3-4, E1-E8, E10-E12 |
| BSEEE | E9 |

---

## 4. Validation Criteria

### 4.1 Level 1 Validation

**PASS Criteria:**
- [ ] Credit count matches expected value
- [ ] F grades excluded from earned credits
- [ ] W grades excluded from earned credits
- [ ] I grades excluded from earned credits
- [ ] X grades excluded from earned credits
- [ ] 0-credit labs tracked separately
- [ ] Retaken courses counted once

### 4.2 Level 2 Validation

**PASS Criteria:**
- [ ] CGPA calculated to 2 decimal places
- [ ] Grade points correctly mapped
- [ ] Best grade used for retakes
- [ ] Waived courses excluded from CGPA
- [ ] F, W, I, X excluded from CGPA denominator
- [ ] Academic standing correctly determined

### 4.3 Level 3 Validation

**PASS Criteria:**
- [ ] All mandatory courses identified
- [ ] Missing courses correctly reported by category
- [ ] Credit requirements per category verified
- [ ] Elective trail concentration rule enforced
- [ ] Capstone projects tracked
- [ ] Probation status flagged for CGPA < 2.0
- [ ] Eligibility correctly determined

### 4.4 General Validation

**PASS Criteria:**
- [ ] No exceptions or crashes
- [ ] Output format matches specification
- [ ] Performance < 5 seconds per transcript
- [ ] Handles malformed input gracefully

---

## 5. Expected Results Summary

### 5.1 Level 1 Expected Results

| Test | Earned Credits | Labs | Excluded |
|------|----------------|------|----------|
| L1-1 | 130.0 | 6 | 0 |
| L1-2 | 125.0 | 6 | 3 |
| L1-3 | 128.0 | 6 | 2 |
| L1-4 | 130.0 | 6 | 0 |

### 5.2 Level 2 Expected Results

| Test | CGPA | Standing | Waivers | Retakes |
|------|------|----------|---------|---------|
| L2-1 | 3.40 | First Class | 0 | 0 |
| L2-2 | ~3.20 | First Class | 0 | 3 |
| L2-3 | ~3.15 | First Class | 1 | 0 |
| L2-4 | ~2.85 | Second Class | 2 | 3 |

### 5.3 Level 3 Expected Results

| Test | Status | CGPA | Deficiencies |
|------|--------|------|--------------|
| L3-1 | ELIGIBLE | 3.55 | 0 |
| L3-2 | NOT ELIGIBLE | 3.20 | 3 courses |
| L3-3 | NOT ELIGIBLE | 1.85 | Probation |
| L3-4 | NOT ELIGIBLE | 3.40 | Trail violation |

### 5.4 Edge Case Expected Results

| Test | Status | Notes |
|------|--------|-------|
| E1 | ELIGIBLE | Transfer credits accepted |
| E2 | NOT ELIGIBLE | 0 credits (all I) |
| E3 | ELIGIBLE | Summa Cum Laude |
| E4 | NOT ELIGIBLE | 0 credits (all F) |
| E5 | ELIGIBLE | Best grade counted |
| E6 | NOT ELIGIBLE | 0 credits (all W) |
| E7 | NOT ELIGIBLE | Missing capstones |
| E8 | ELIGIBLE | Prereq violations noted |
| E9 | ELIGIBLE | BSEEE complete |
| E10 | NOT ELIGIBLE | Empty transcript |
| E11 | NOT ELIGIBLE | 0 credits (all X) |
| E12 | Varies | Borderline cases |

---

## 6. Test Execution Guide

### 6.1 Running All Tests

```bash
# Compile the project
javac -d bin src/com/nsu/audit/*.java src/com/nsu/audit/**/*.java

# Run Level 1 tests
for f in tests/level1/*.csv; do
    echo "Testing $f"
    java -cp bin com.nsu.audit.NSUAuditTool level1 "$f"
done

# Run Level 2 tests (requires input)
java -cp bin com.nsu.audit.NSUAuditTool level2 tests/level2/test_L2_1.csv <<EOF
NONE
EOF

# Run Level 3 tests (requires input)
java -cp bin com.nsu.audit.NSUAuditTool level3 tests/level3/test_L3_1.csv BSCSE data/program_knowledge_BSCSE.md <<EOF
NONE
EOF
```

### 6.2 Automated Testing Script

Create `run_tests.sh`:

```bash
#!/bin/bash
echo "Running NSU Audit Core Tests"
echo "============================="

# Level 1 Tests
echo -e "\n### Level 1 Tests ###"
for test in tests/level1/*.csv; do
    result=$(java -cp bin com.nsu.audit.NSUAuditTool level1 "$test" 2>&1)
    if echo "$result" | grep -q "PASSED"; then
        echo "✓ $(basename $test)"
    else
        echo "✗ $(basename $test)"
    fi
done

echo -e "\n### Test Complete ###"
```

---

## 7. Test Maintenance

### 7.1 Adding New Tests

1. Create new CSV file in appropriate test directory
2. Document test in this plan
3. Update coverage analysis
4. Add expected results
5. Run and validate

### 7.2 Updating Tests

When program requirements change:
1. Update affected test files
2. Update expected results
3. Re-run all tests
4. Update documentation

---

**End of Testing Plan**
