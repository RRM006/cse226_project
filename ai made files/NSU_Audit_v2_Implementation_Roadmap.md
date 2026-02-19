# NSU Audit Core v2.0 - Implementation Roadmap
## 1-Day Sprint Plan

---

## Timeline: 1 Day (24 hours)

### ✅ Clarifications Confirmed:

1. **CSV Format**: Same structure for all programs (Law, Engineering)
2. **Policies**: Implement ALL academic policies from NSU documents
3. **Testing**: L1, L2, L3 structure for Law + edge cases
4. **LLB407**: Required (flag if issues arise)
5. **Electives**: Follow curriculum (Year 3+ for Law)
6. **Exclusion**: Automated for all programs
7. **Output**: Follow L1, L2, L3 instruction format
8. **v3.0 Focus**: CLI window interface (input → output)

---

## Phase 1: Foundation (Hours 0-4)

### ✅ Task 1.1: Law Program Knowledge Base (1.5 hours)
**Files to Create:**
- `data/programs/program_knowledge_LLB.md`
- `data/policies/ged_requirements.md`
- `data/policies/prerequisite_graph_LLB.md`

**Content:**
- All 130 credits mapped
- GED Group 1 (16 cr) + Group 2 (9 cr)
- 27 Core courses by year
- 19 Elective options
- Prerequisites (LLB101→103/104, etc.)

### ✅ Task 1.2: Academic Policies Implementation (1.5 hours)
**Check v1.0 for:**
- Retake policy (best grade counts)
- Course exclusion (80% rule)
- Probation tracking

**Add to v2.0:**
- Full retake implementation (max 3, 4th = VC approval)
- Automated exclusion validation
- Probation semester counter

### ✅ Task 1.3: Modular Architecture Setup (1 hour)
**Create Structure:**
```
src/
├── level1/
│   ├── credit_tally_engine.py
│   └── utils_l1.py
├── level2/
│   ├── cgpa_calculator.py
│   ├── retake_processor.py
│   └── utils_l2.py
├── level3/
│   ├── audit_engine.py
│   ├── requirement_checker.py
│   └── utils_l3.py
└── shared/
    ├── transcript_parser.py
    ├── constants.py
    └── utils.py
```

---

## Phase 2: Core Implementation (Hours 4-12)

### ✅ Task 2.1: Enhanced Level 1 - Credit Tally (2 hours)
**New Features:**
- Multi-program support (detect BSCSE/BSEEE/LLB)
- GED validation for Law (Group 1 + 2)
- Year-wise credit tracking
- Category breakdown (GED, Core, Electives)

**Test Cases to Pass:**
- `test_L1_law_standard.csv`
- `test_L1_law_ged_violation.csv`
- `test_L1_law_incomplete.csv`
- All existing Engineering tests

### ✅ Task 2.2: Enhanced Level 2 - CGPA Calculator (3 hours)
**New Features:**
- Full retake policy (track attempts, flag 4th)
- Course exclusion validator (80% rule)
- Academic standing with probation tracking
- Policy enforcement flags

**Test Cases to Pass:**
- `test_L2_law_cgpa_standard.csv`
- `test_L2_law_retakes.csv`
- `test_L2_law_probation.csv`
- `test_L2_law_exclusion.csv`

### ✅ Task 2.3: Enhanced Level 3 - Audit Engine (3 hours)
**New Features:**
- Law program requirement checker
- GED Group validation
- Dissertation requirement (LLB407)
- Year progression validation
- Prerequisite checking

**Test Cases to Pass:**
- `test_L3_law_graduation_ready.csv`
- `test_L3_law_missing_core.csv`
- `test_L3_law_no_dissertation.csv`
- `test_L3_law_prerequisite_violation.csv`

---

## Phase 3: Testing & Documentation (Hours 12-18)

### ✅ Task 3.1: Create Law Test Cases (2 hours)
**Level 1 (8 tests):**
- Standard, GED violations, incomplete, edge cases

**Level 2 (8 tests):**
- CGPA standard, retakes, waivers, probation, exclusion

**Level 3 (10 tests):**
- Graduation ready, missing core, GED incomplete, no dissertation, prerequisites

### ✅ Task 3.2: Integration Testing (2 hours)
- Test all programs (BSCSE, BSEEE, LLB)
- Verify policy enforcement
- Cross-validate manual calculations

### ✅ Task 3.3: Documentation (2 hours)
- Update README.md for v2.0
- Create TESTING.md
- Document all new features
- Add usage examples

---

## Phase 4: Refinement (Hours 18-24)

### ✅ Task 4.1: Output Formatting (2 hours)
- Structured output per L1, L2, L3 specs
- Clear section headers
- Consistent formatting
- Easy-to-read reports

### ✅ Task 4.2: Edge Case Handling (2 hours)
- Law-specific edge cases
- Policy edge cases
- Error handling
- Input validation

### ✅ Task 4.3: Final Testing & Bug Fixes (2 hours)
- Run all 59 test cases
- Fix any issues
- Performance optimization
- Code cleanup

---

## Critical Implementation Notes

### 🔴 Must Have (v2.0):
1. ✅ Law program support (all 130 credits)
2. ✅ GED Group 1 & 2 validation
3. ✅ Retake policy (automated)
4. ✅ Course exclusion (automated, 80% rule)
5. ✅ Probation tracking (3 semester limit)
6. ✅ Dissertation requirement (LLB407)
7. ✅ Year progression validation
8. ✅ 59 test cases passing
9. ✅ Clear L1, L2, L3 output

### 🟡 Nice to Have (if time):
1. ⚠️ Prerequisite graph visualization
2. ⚠️ Semester-wise progression report
3. ⚠️ Multiple student comparison
4. ⚠️ Export to Markdown/HTML

### 🔵 Defer to v3.0:
1. 📅 Custom CLI window interface
2. 📅 Colored output
3. 📅 Progress bars
4. 📅 Interactive menus
5. 📅 Database integration
6. 📅 Batch processing

---

## v2.0 Deliverables Checklist

### Code:
- [ ] `src/level1/` - Credit tally with Law support
- [ ] `src/level2/` - CGPA with full policies
- [ ] `src/level3/` - Audit with Law validation
- [ ] `src/shared/` - Common utilities

### Knowledge Base:
- [ ] `data/programs/program_knowledge_LLB.md`
- [ ] `data/policies/ged_requirements.md`
- [ ] `data/policies/prerequisite_graph_LLB.md`
- [ ] `data/policies/academic_policies.md`

### Test Cases:
- [ ] `tests/level1/law/` - 8 test files
- [ ] `tests/level2/law/` - 8 test files
- [ ] `tests/level3/law/` - 10 test files

### Documentation:
- [ ] Updated `README.md`
- [ ] `TESTING.md`
- [ ] `CHANGELOG.md` (v1.0 → v2.0)
- [ ] Usage examples

### Validation:
- [ ] All 59 tests passing
- [ ] Manual calculation verification
- [ ] Performance < 5 sec per audit
- [ ] Clear output format

---

## Known Issues to Flag

### LLB407 (Dissertation):
- **Issue**: Required in Year 4, Semester 2
- **Prerequisites**: LLB404 + 100 credits
- **Flag**: If student attempts before prerequisites met
- **Action**: Block registration, show clear error

### Course Exclusion:
- **Issue**: Can only exclude after 80% credits (104/130)
- **Rule**: Cannot exclude core courses
- **Rule**: Cannot exclude to avoid probation
- **Action**: Automated validation, reject invalid requests

### Probation Tracking:
- **Issue**: Must raise CGPA to 2.00 within 3 semesters
- **Action**: Track probation semesters, dismiss after 3

---

## Risk Mitigation

### Risk 1: Timeline (1 day)
**Mitigation**: 
- Focus on must-have features
- Defer nice-to-have to v3.0
- Use existing v1.0 code where possible
- Parallel testing during implementation

### Risk 2: Law Complexity
**Mitigation**:
- Clear knowledge base structure
- Modular validation functions
- Test incrementally (GED, Core, Electives separately)

### Risk 3: Policy Implementation
**Mitigation**:
- Check v1.0 for existing policies
- Implement incrementally (retake → exclusion → probation)
- Automated validation with clear error messages

---

## Next Steps - START NOW! 🚀

1. **First 30 minutes**: Create Law knowledge base files
2. **Next 2 hours**: Implement Level 1 with Law support
3. **Next 3 hours**: Implement Level 2 with full policies
4. **Next 3 hours**: Implement Level 3 with Law validation
5. **Next 4 hours**: Create all test cases
6. **Next 4 hours**: Testing, debugging, documentation
7. **Final 7.5 hours**: Refinement, edge cases, final validation

**Let's build v2.0! 💪**
