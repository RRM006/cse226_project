# NSU Audit Core System v3.0
## Product Requirements Document - CLI Interface Edition

---

**Document Information:**
- **Project**: NSU Department Admin Audit Core - CLI Interface
- **Version**: 3.0
- **Previous Version**: 2.0 (Multi-program terminal output)
- **Date**: February 15, 2026
- **Timeline**: 3 Days
- **Author**: NSU Development Team
- **Status**: Ready for Implementation

---

## Executive Summary

NSU Audit Core v3.0 transforms the command-line audit tool into a **modern, interactive CLI application** with a custom window interface, colored output, and user-friendly interactions. This version builds on v2.0's multi-program support while dramatically improving the user experience.

**Core Objectives:**
1. Create custom CLI window interface (input/output)
2. Implement interactive menu-driven navigation
3. Add colored output for better readability
4. Include progress indicators for operations
5. Maintain all v2.0 functionality
6. Enhance user experience dramatically

**Key Features:**
- ✅ Custom CLI window (not just terminal)
- ✅ Interactive menus (no manual commands)
- ✅ Color-coded output (green/yellow/red)
- ✅ Progress bars for processing
- ✅ Professional UI elements
- ✅ All v2.0 features included

---

## Table of Contents

1. [System Overview](#1-system-overview)
2. [v3.0 Enhancements](#2-v30-enhancements)
3. [CLI Interface Design](#3-cli-interface-design)
4. [Menu System](#4-menu-system)
5. [Color Scheme](#5-color-scheme)
6. [Progress Indicators](#6-progress-indicators)
7. [User Workflows](#7-user-workflows)
8. [Technical Architecture](#8-technical-architecture)
9. [Implementation Libraries](#9-implementation-libraries)
10. [Screen Designs](#10-screen-designs)
11. [Error Handling](#11-error-handling)
12. [Testing Strategy](#12-testing-strategy)
13. [Implementation Guide](#13-implementation-guide)
14. [Success Criteria](#14-success-criteria)
15. [Deliverables](#15-deliverables)

---

## 1. System Overview

### 1.1 Purpose

Transform NSU Audit Core into a **professional, user-friendly CLI application** that:
- Provides custom window interface (not raw terminal)
- Guides users through interactive menus
- Shows colored, formatted output
- Displays progress for long operations
- Makes audit system accessible to non-technical users

### 1.2 What's New in v3.0

**User Experience:**
- Custom CLI window (opens in its own window)
- Menu-driven interface (no commands to remember)
- Colored output (visual status indicators)
- Progress bars (show operation progress)
- Formatted reports (professional appearance)

**Maintained from v2.0:**
- All 3 programs (BSCSE, BSEEE, LL.B)
- All academic policies
- All test cases (59 tests)
- Complete audit functionality

### 1.3 Target Users

**Primary Users:**
- Department administrators (non-technical)
- Academic advisors
- Faculty members
- Students (read-only access)

**Experience Level:**
- No command-line knowledge required
- Point-and-click interface
- Clear visual feedback
- Helpful prompts and guidance

---

## 2. v3.0 Enhancements

### 2.1 From v2.0 to v3.0

| Feature | v2.0 | v3.0 |
|---------|------|------|
| Interface | Terminal commands | Custom CLI window |
| Navigation | Manual commands | Interactive menus |
| Output | Plain text | Colored, formatted |
| Feedback | Text only | Progress bars + colors |
| User Input | Command arguments | Interactive prompts |
| Help | Manual/README | Built-in help system |
| Reports | Terminal output | Formatted + exportable |
| Look & Feel | Basic terminal | Professional UI |

### 2.2 Key Improvements

**Usability:**
- ✅ No command-line knowledge needed
- ✅ Visual status indicators (✓, ⚠, ✗)
- ✅ Clear navigation path
- ✅ Helpful error messages with colors
- ✅ Keyboard shortcuts (optional)

**Visual Design:**
- ✅ Color-coded categories (GED, Core, Electives)
- ✅ Box-drawing characters for structure
- ✅ Progress bars for operations
- ✅ Icons and symbols (✓, ⚠, ✗, ►)
- ✅ Professional formatting

**Functionality:**
- ✅ All v2.0 features preserved
- ✅ Interactive file selection
- ✅ Program auto-detection
- ✅ Batch mode available
- ✅ Export options

---

## 3. CLI Interface Design

### 3.1 Window Specifications

**Window Properties:**
```
Title: NSU Audit Core System v3.0
Size: 100 columns × 40 rows (minimum)
Resizable: Yes
Colors: Full color support (256 colors)
Encoding: UTF-8 (for symbols)
```

**Window Components:**
```
┌────────────────────────────────────────────────────────────┐
│              NSU AUDIT CORE SYSTEM v3.0                    │
│          North South University Graduation Auditor         │
├────────────────────────────────────────────────────────────┤
│                                                             │
│  [Main content area - 35 rows]                             │
│                                                             │
├────────────────────────────────────────────────────────────┤
│  Status: Ready | Program: LL.B Honors | Help: Press F1    │
└────────────────────────────────────────────────────────────┘
```

### 3.2 Screen Layout

**Header Section** (4 rows):
- Application title
- Current user/session info
- Navigation breadcrumb

**Content Section** (30-35 rows):
- Main menu
- Input forms
- Output displays
- Reports

**Footer Section** (2 rows):
- Status bar
- Quick help text
- Current operation

**Sidebar** (optional, 20 columns):
- Quick navigation
- Recent files
- Shortcuts

### 3.3 UI Elements

**Box Drawing:**
```
┌─┬─┐  Top border
│ │ │  Vertical lines
├─┼─┤  Middle border
│ │ │  Content area
└─┴─┘  Bottom border
```

**Symbols:**
```
✓  Success/Complete
✗  Error/Failed
⚠  Warning/In Progress
►  Selected item
□  Unchecked option
■  Checked option
↑↓ Navigation arrows
⏳ Processing
```

---

## 4. Menu System

### 4.1 Main Menu

```
╔══════════════════════════════════════════════════════════╗
║          NSU AUDIT CORE SYSTEM v3.0                      ║
║       North South University Graduation Auditor          ║
╚══════════════════════════════════════════════════════════╝

Main Menu:
  ► [1] Credit Tally (Level 1)
    [2] CGPA Calculator (Level 2)
    [3] Full Graduation Audit (Level 3)
    [4] Batch Processing
    [5] View Reports
    [6] Settings
    [7] Help & Documentation
    [0] Exit

Select option (0-7): _

Navigation: ↑↓ Arrow keys, Enter to select, 0 to exit
```

**Features:**
- Arrow key navigation
- Number key shortcuts
- Visual selection indicator (►)
- Help text at bottom
- Return to menu option in all screens

### 4.2 Level 1 Menu (Credit Tally)

```
╔══════════════════════════════════════════════════════════╗
║                   LEVEL 1: CREDIT TALLY                  ║
╚══════════════════════════════════════════════════════════╝

Current Mode: Credit Analysis

Options:
  ► [1] Select Transcript File
    [2] View Sample Transcript
    [3] Run Credit Analysis
    [4] View Last Result
    [0] Back to Main Menu

Transcript: [None selected]
Program: [Auto-detect]

Select option (0-4): _
```

### 4.3 Level 2 Menu (CGPA Calculator)

```
╔══════════════════════════════════════════════════════════╗
║              LEVEL 2: CGPA CALCULATOR                    ║
╚══════════════════════════════════════════════════════════╝

Current Mode: CGPA Calculation with Policies

Options:
  ► [1] Select Transcript File
    [2] Enter Waiver Information
    [3] Calculate CGPA
    [4] View Academic Policies
    [5] View Last Result
    [0] Back to Main Menu

Transcript: [None selected]
Waivers: [None]
Program: [Auto-detect]

Select option (0-5): _
```

### 4.4 Level 3 Menu (Full Audit)

```
╔══════════════════════════════════════════════════════════╗
║           LEVEL 3: FULL GRADUATION AUDIT                 ║
╚══════════════════════════════════════════════════════════╝

Current Mode: Comprehensive Audit

Options:
  ► [1] Select Transcript File
    [2] Select Knowledge Base
    [3] Run Full Audit
    [4] View Deficiency Report
    [5] Export Audit Report
    [6] View Last Result
    [0] Back to Main Menu

Transcript: [None selected]
Knowledge Base: [Auto-detect]
Program: [LL.B Honors]

Select option (0-6): _
```

### 4.5 File Selection Interface

```
╔══════════════════════════════════════════════════════════╗
║                   SELECT TRANSCRIPT FILE                 ║
╚══════════════════════════════════════════════════════════╝

Current Directory: tests/level1/law/

Files:
  ► test_L1_law_standard.csv
    test_L1_law_ged_violation.csv
    test_L1_law_incomplete.csv
    test_L1_law_edge_cases.csv
    [.. 4 more files ..]

Navigation:
  ↑↓  - Select file
  →   - Enter directory
  ←   - Parent directory
  [E] - Enter path manually
  [0] - Cancel

Selected: test_L1_law_standard.csv
```

---

## 5. Color Scheme

### 5.1 Color Palette

**Primary Colors:**
```
Green  (#00FF00) - Success, complete, passed
Yellow (#FFFF00) - Warning, in-progress, attention
Red    (#FF0000) - Error, failed, critical
Blue   (#0000FF) - Information, neutral
Cyan   (#00FFFF) - Highlight, selection
White  (#FFFFFF) - Primary text
Gray   (#808080) - Secondary text, disabled
```

**Usage:**
```python
from colorama import Fore, Back, Style

# Success
print(f"{Fore.GREEN}✓ PASSED{Style.RESET_ALL}")

# Warning
print(f"{Fore.YELLOW}⚠ IN PROGRESS{Style.RESET_ALL}")

# Error
print(f"{Fore.RED}✗ FAILED{Style.RESET_ALL}")

# Information
print(f"{Fore.BLUE}ℹ Information{Style.RESET_ALL}")
```

### 5.2 Color Coding by Status

**Academic Standing:**
```
Summa Cum Laude   → Bright Green
Magna Cum Laude   → Green
Cum Laude         → Cyan
First Class       → Blue
Second Class      → Blue
Third Class       → Yellow
PROBATION         → Red (Blinking)
```

**Requirement Status:**
```
✓ COMPLETE        → Green
⚠ IN PROGRESS     → Yellow
✗ INCOMPLETE      → Red
□ NOT STARTED     → Gray
```

**Category Colors:**
```
GED Group 1       → Cyan
GED Group 2       → Cyan
Core Program      → Blue
Electives         → Magenta
Capstone          → Green (when complete)
```

### 5.3 Text Styling

**Font Styles:**
```
Bold              → Important information
Italic            → Notes, hints
Underline         → Headers, titles
Dim               → Secondary text
Blink             → Critical errors (use sparingly)
```

**Examples:**
```python
print(f"{Style.BRIGHT}CRITICAL ERROR{Style.RESET_ALL}")
print(f"{Style.DIM}(Optional) Additional info{Style.RESET_ALL}")
```

---

## 6. Progress Indicators

### 6.1 Progress Bar Design

**Simple Progress Bar:**
```
Processing transcript...
[████████████████████              ] 65% (23/35 courses)
```

**Detailed Progress Bar:**
```
Analyzing transcript: test_L1_law_standard.csv

╔══════════════════════════════════════════════════════════╗
║ Phase 1: Parsing CSV                                     ║
║ [████████████████████████████████████] 100% Complete     ║
╠══════════════════════════════════════════════════════════╣
║ Phase 2: Validating Grades                               ║
║ [████████████████░░░░░░░░░░░░░░░░░░]  50% (18/35)       ║
╠══════════════════════════════════════════════════════════╣
║ Phase 3: Calculating Credits                             ║
║ [░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░]   0% Pending...    ║
╚══════════════════════════════════════════════════════════╝

Time Elapsed: 1.2s | Estimated Time Remaining: 1.5s
```

### 6.2 Spinner Animation

**For Quick Operations:**
```
Loading knowledge base...  ⠋
Loading knowledge base...  ⠙
Loading knowledge base...  ⠹
Loading knowledge base...  ⠸
Loading knowledge base...  ⠼
Loading knowledge base...  ⠴
Loading knowledge base...  ⠦
Loading knowledge base...  ⠧
Loading knowledge base...  ⠇
Loading knowledge base...  ⠏
Loading knowledge base...  ✓ Complete!
```

**Spinner Characters:**
```
⠋ ⠙ ⠹ ⠸ ⠼ ⠴ ⠦ ⠧ ⠇ ⠏
```

### 6.3 Multi-Step Progress

**For Complex Operations:**
```
╔══════════════════════════════════════════════════════════╗
║              GRADUATION AUDIT PROGRESS                   ║
╚══════════════════════════════════════════════════════════╝

[✓] Step 1: Load transcript                      (0.5s)
[✓] Step 2: Detect program                       (0.1s)
[✓] Step 3: Load knowledge base                  (0.8s)
[►] Step 4: Validate GED requirements            (1.2s)
    ├─ [✓] GED Group 1
    ├─ [►] GED Group 2 (checking alternatives...)
    └─ [ ] Science course validation
[ ] Step 5: Validate core courses
[ ] Step 6: Validate electives
[ ] Step 7: Check prerequisites
[ ] Step 8: Generate deficiency report
[ ] Step 9: Determine graduation eligibility

Overall Progress: [████████░░░░░░░░░░░░] 40%

Current: Validating GED Group 2...
```

### 6.4 Implementation

```python
from tqdm import tqdm
import time

# Simple progress bar
for i in tqdm(range(100), desc="Processing"):
    time.sleep(0.01)

# Custom progress bar
from rich.progress import Progress

with Progress() as progress:
    task1 = progress.add_task("[cyan]Parsing CSV...", total=100)
    task2 = progress.add_task("[yellow]Validating...", total=100)
    task3 = progress.add_task("[green]Calculating...", total=100)
    
    while not progress.finished:
        progress.update(task1, advance=0.5)
        progress.update(task2, advance=0.3)
        progress.update(task3, advance=0.1)
        time.sleep(0.02)
```

---

## 7. User Workflows

### 7.1 First-Time User Workflow

**Step 1: Launch Application**
```
$ python nsu_audit_cli.py

[Window opens with splash screen]

╔══════════════════════════════════════════════════════════╗
║                                                           ║
║         NSU AUDIT CORE SYSTEM v3.0                       ║
║                                                           ║
║     North South University Graduation Auditor            ║
║                                                           ║
║              Loading...  ⠋                               ║
║                                                           ║
╚══════════════════════════════════════════════════════════╝

[2 seconds later, main menu appears]
```

**Step 2: Select Operation**
```
Main Menu displayed (see section 4.1)
User selects: [3] Full Graduation Audit
```

**Step 3: Select Transcript**
```
File browser appears (see section 4.5)
User navigates to: tests/level3/law/
User selects: test_L3_law_graduation_ready.csv
```

**Step 4: Confirm Program**
```
╔══════════════════════════════════════════════════════════╗
║                CONFIRM PROGRAM SELECTION                 ║
╚══════════════════════════════════════════════════════════╝

Detected Program: LL.B Honors (Bachelor of Laws)
Department: School of Law

Is this correct?
  ► [Y] Yes, proceed
    [N] No, select manually
    [C] Cancel

Selection: _
```

**Step 5: Processing**
```
Progress indicators shown (see section 6.3)
Takes 3-5 seconds for full audit
```

**Step 6: View Results**
```
Formatted audit report displayed (see section 10.3)
With colors, symbols, and structure
```

**Step 7: Export or Return**
```
╔══════════════════════════════════════════════════════════╗
║                   AUDIT COMPLETE                         ║
╚══════════════════════════════════════════════════════════╝

Result: ✓ ELIGIBLE FOR GRADUATION
CGPA: 3.40 (First Class)

What would you like to do?
  ► [1] Export to PDF
    [2] Export to HTML
    [3] Save to Markdown
    [4] Print to Console
    [5] View Detailed Breakdown
    [0] Return to Main Menu

Selection: _
```

### 7.2 Quick Audit Workflow

**For Experienced Users:**

```
1. Launch app
2. Press [3] (Full Audit)
3. Press [1] (Select file)
4. Type file path directly
5. Press Enter
6. View results
7. Press [0] (Return to menu)

Total time: < 30 seconds
```

### 7.3 Batch Processing Workflow

```
╔══════════════════════════════════════════════════════════╗
║                   BATCH PROCESSING                       ║
╚══════════════════════════════════════════════════════════╝

Select input directory:
  ► tests/level3/law/

Files found: 10 transcripts

Program: LL.B Honors

Options:
  [1] Process all files
  [2] Select specific files
  [3] Preview file list
  [0] Cancel

Selection: 1

Processing...
[████████████████████████] 100% (10/10 files)

Summary:
  ✓ Eligible:       7 students (70%)
  ⚠ Deficiencies:   2 students (20%)
  ✗ On Probation:   1 student  (10%)

Export batch report?
  [Y] Yes  [N] No

Selection: _
```

---

## 8. Technical Architecture

### 8.1 Architecture Overview

```
┌─────────────────────────────────────────────────────────┐
│                   CLI Interface Layer                    │
│  (Custom window, menus, input/output handling)          │
├─────────────────────────────────────────────────────────┤
│                    UI Component Layer                    │
│  (Progress bars, tables, forms, formatters)             │
├─────────────────────────────────────────────────────────┤
│                  Business Logic Layer                    │
│  (Level 1, Level 2, Level 3 from v2.0)                  │
├─────────────────────────────────────────────────────────┤
│                     Data Layer                           │
│  (Transcript parser, knowledge base, constants)          │
└─────────────────────────────────────────────────────────┘
```

### 8.2 New v3.0 Modules

```
src/
├── cli/                           # NEW: CLI Interface
│   ├── __init__.py
│   ├── main.py                    # Application entry point
│   ├── window_manager.py          # Window creation & management
│   ├── menu_system.py             # Menu navigation
│   ├── input_handler.py           # User input processing
│   ├── output_formatter.py        # Formatted output
│   └── file_browser.py            # File selection UI
│
├── ui/                            # NEW: UI Components
│   ├── __init__.py
│   ├── progress_bars.py           # Progress indicators
│   ├── tables.py                  # Data tables
│   ├── forms.py                   # Input forms
│   ├── colors.py                  # Color management
│   └── symbols.py                 # Icon/symbol helpers
│
├── export/                        # NEW: Export functionality
│   ├── __init__.py
│   ├── pdf_exporter.py            # Export to PDF
│   ├── html_exporter.py           # Export to HTML
│   └── markdown_exporter.py       # Export to Markdown
│
├── level1/                        # FROM v2.0
│   └── ... (existing modules)
│
├── level2/                        # FROM v2.0
│   └── ... (existing modules)
│
├── level3/                        # FROM v2.0
│   └── ... (existing modules)
│
└── shared/                        # FROM v2.0
    └── ... (existing modules)
```

### 8.3 Data Flow

```
User Input (Menu Selection)
    ↓
Menu System (input_handler.py)
    ↓
Business Logic (level1/2/3)
    ↓
Progress Updates (progress_bars.py)
    ↓
Results (output_formatter.py)
    ↓
Display (window_manager.py)
    ↓
Export (optional, export/*)
```

---

## 9. Implementation Libraries

### 9.1 Required Libraries

**Core Libraries:**
```python
# CLI Framework
import click              # Command-line interface
import typer             # Modern CLI (alternative)

# UI Components
from rich.console import Console
from rich.table import Table
from rich.progress import Progress
from rich.panel import Panel
from rich import box

# Colors
from colorama import Fore, Back, Style, init

# Progress Bars
from tqdm import tqdm

# File Operations
import os
import sys
from pathlib import Path
```

**Installation:**
```bash
pip install click rich colorama tqdm
# OR
pip install typer rich colorama tqdm
```

### 9.2 Library Selection

**Option 1: Rich (Recommended)**
- Pros: Modern, feature-rich, easy to use
- Cons: Larger dependency
- Best for: Professional UI with tables, panels

**Option 2: Click + Colorama**
- Pros: Lightweight, widely used
- Cons: More manual work
- Best for: Simple, fast implementation

**Option 3: Typer + Rich**
- Pros: Modern, type-safe, great for menus
- Cons: Newer, less examples
- Best for: Clean, maintainable code

**Recommendation:** Use **Rich** for v3.0
- Best features for CLI apps
- Easy progress bars
- Beautiful tables
- Good documentation

### 9.3 Code Examples

**Rich Progress Bar:**
```python
from rich.progress import Progress

with Progress() as progress:
    task = progress.add_task("[cyan]Processing...", total=100)
    
    for i in range(100):
        progress.update(task, advance=1)
        # Do work here
```

**Rich Table:**
```python
from rich.console import Console
from rich.table import Table

console = Console()

table = Table(title="Credit Analysis")
table.add_column("Category", style="cyan")
table.add_column("Credits", justify="right", style="magenta")
table.add_column("Status", style="green")

table.add_row("GED Group 1", "16 / 16", "✓ Complete")
table.add_row("GED Group 2", "9 / 9", "✓ Complete")
table.add_row("Core Program", "54 / 81", "⚠ In Progress")

console.print(table)
```

**Rich Panel:**
```python
from rich.console import Console
from rich.panel import Panel

console = Console()

panel = Panel(
    "✓ ELIGIBLE FOR GRADUATION\nCGPA: 3.40 (First Class)",
    title="Audit Result",
    border_style="green"
)

console.print(panel)
```

---

## 10. Screen Designs

### 10.1 Main Menu Screen

```
╔══════════════════════════════════════════════════════════════════════╗
║                  NSU AUDIT CORE SYSTEM v3.0                          ║
║              North South University Graduation Auditor               ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  Welcome! Select an operation from the menu below:                   ║
║                                                                       ║
║  AUDIT OPERATIONS:                                                   ║
║    ► [1] Credit Tally (Level 1)                                     ║
║       Calculate earned credits and validate course completion        ║
║                                                                       ║
║      [2] CGPA Calculator (Level 2)                                   ║
║       Calculate CGPA with retakes, waivers, and academic standing    ║
║                                                                       ║
║      [3] Full Graduation Audit (Level 3)                            ║
║       Comprehensive audit with deficiency report                     ║
║                                                                       ║
║  ADDITIONAL FEATURES:                                                ║
║      [4] Batch Processing                                            ║
║       Process multiple student transcripts at once                   ║
║                                                                       ║
║      [5] View Reports                                                ║
║       View previously generated audit reports                        ║
║                                                                       ║
║      [6] Settings                                                    ║
║       Configure application preferences                              ║
║                                                                       ║
║      [7] Help & Documentation                                        ║
║       View user guide and documentation                              ║
║                                                                       ║
║      [0] Exit Application                                            ║
║                                                                       ║
╠══════════════════════════════════════════════════════════════════════╣
║  Navigation: Use ↑↓ arrows or number keys | Enter to select         ║
║  Quick Help: Press F1 at any time                                    ║
╚══════════════════════════════════════════════════════════════════════╝

Select option (0-7): _
```

### 10.2 Processing Screen

```
╔══════════════════════════════════════════════════════════════════════╗
║                      FULL GRADUATION AUDIT                           ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  File: test_L3_law_graduation_ready.csv                             ║
║  Program: LL.B Honors (Bachelor of Laws)                             ║
║  Department: School of Law                                            ║
║                                                                       ║
║  ═══════════════════════════════════════════════════════════════    ║
║                                                                       ║
║  [✓] Step 1: Load transcript                             0.5s       ║
║  [✓] Step 2: Detect program type                         0.1s       ║
║  [✓] Step 3: Load knowledge base                         0.8s       ║
║  [✓] Step 4: Parse program requirements                  0.3s       ║
║  [✓] Step 5: Calculate CGPA                              0.4s       ║
║  [►] Step 6: Validate GED requirements                   1.2s       ║
║      ├─ [✓] GED Group 1 (16 credits)                                ║
║      ├─ [►] GED Group 2 (9 credits)                                 ║
║      │   └─ Checking alternative course conflicts...                ║
║      └─ [ ] Science course validation                               ║
║  [ ] Step 7: Validate core courses by year                          ║
║  [ ] Step 8: Validate elective requirements                         ║
║  [ ] Step 9: Check dissertation (LLB407)                            ║
║  [ ] Step 10: Check prerequisites                                   ║
║  [ ] Step 11: Generate deficiency report                            ║
║  [ ] Step 12: Determine graduation eligibility                      ║
║                                                                       ║
║  Overall Progress:                                                   ║
║  [████████████░░░░░░░░░░░░░░░░░░░░░░]  45% (6/12 steps)           ║
║                                                                       ║
║  Current: Validating GED Group 2 alternative courses...             ║
║  Time Elapsed: 3.3s | Estimated Remaining: 4.2s                     ║
║                                                                       ║
╠══════════════════════════════════════════════════════════════════════╣
║  Status: Processing... | Press ESC to cancel (not recommended)      ║
╚══════════════════════════════════════════════════════════════════════╝
```

### 10.3 Results Screen (Graduation Ready)

```
╔══════════════════════════════════════════════════════════════════════╗
║                    GRADUATION AUDIT RESULTS                          ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  Student: [Auto-detected from filename]                              ║
║  Program: LL.B Honors (Bachelor of Laws)                             ║
║  File: test_L3_law_graduation_ready.csv                             ║
║  Date: February 15, 2026                                             ║
║                                                                       ║
║  ═══════════════════════════════════════════════════════════════    ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                    CREDIT SUMMARY                              │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │  Total Credits:         130 / 130 required    ✓               │ ║
║  │  CGPA:                  3.40                                    │ ║
║  │  Academic Standing:     First Class (Good Standing)            │ ║
║  │  Probation Status:      No                    ✓               │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                REQUIREMENT ANALYSIS                            │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │  GED Group 1 (16 cr):      ✓ COMPLETE                         │ ║
║  │    └─ ENG102, ENG103, BEN205, HIS103, BIO103                  │ ║
║  │                                                                 │ ║
║  │  GED Group 2 (9 cr):       ✓ COMPLETE                         │ ║
║  │    └─ ECO101, POL101, PHI101                                   │ ║
║  │                                                                 │ ║
║  │  Core Year 1 (6 cr):       ✓ COMPLETE                         │ ║
║  │  Core Year 2 (27 cr):      ✓ COMPLETE                         │ ║
║  │  Core Year 3 (21 cr):      ✓ COMPLETE                         │ ║
║  │  Core Year 4 (27 cr):      ✓ COMPLETE                         │ ║
║  │    └─ Including LLB407 (Dissertation) ✓                       │ ║
║  │                                                                 │ ║
║  │  Electives (24 cr):        ✓ COMPLETE (8 courses)             │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                  GRADUATION ELIGIBILITY                        │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │                                                                 │ ║
║  │               ✓ ELIGIBLE FOR GRADUATION                        │ ║
║  │                                                                 │ ║
║  │  All requirements met:                                          │ ║
║  │    ✓ Total credits (130/130)                                   │ ║
║  │    ✓ CGPA requirement (3.40 ≥ 2.00)                           │ ║
║  │    ✓ All GED courses complete                                  │ ║
║  │    ✓ All core courses complete                                 │ ║
║  │    ✓ All elective requirements met                             │ ║
║  │    ✓ Dissertation (LLB407) complete                            │ ║
║  │    ✓ No prerequisite violations                                │ ║
║  │    ✓ Good academic standing (not on probation)                 │ ║
║  │                                                                 │ ║
║  │  Graduation Status: APPROVED                                    │ ║
║  │  Honors: None (CGPA 3.40 < 3.50)                               │ ║
║  │                                                                 │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  What would you like to do?                                          ║
║    [1] Export to PDF                                                 ║
║    [2] Export to HTML                                                ║
║    [3] Save to Markdown                                              ║
║    [4] View Detailed Breakdown                                       ║
║    [5] Print Summary                                                 ║
║    [0] Return to Main Menu                                           ║
║                                                                       ║
║  Selection: _                                                        ║
║                                                                       ║
╚══════════════════════════════════════════════════════════════════════╝
```

### 10.4 Results Screen (Deficiencies Found)

```
╔══════════════════════════════════════════════════════════════════════╗
║                    GRADUATION AUDIT RESULTS                          ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  Student: [Auto-detected]                                            ║
║  Program: LL.B Honors                                                ║
║  File: test_L3_law_missing_core.csv                                 ║
║  Date: February 15, 2026                                             ║
║                                                                       ║
║  ═══════════════════════════════════════════════════════════════    ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                    CREDIT SUMMARY                              │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │  Total Credits:         125 / 130 required    ⚠               │ ║
║  │  CGPA:                  3.20                                    │ ║
║  │  Academic Standing:     First Class (Good Standing)            │ ║
║  │  Probation Status:      No                    ✓               │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                REQUIREMENT ANALYSIS                            │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │  GED Requirements:         ✓ COMPLETE (25 cr)                 │ ║
║  │  Core Year 1:              ✓ COMPLETE (6 cr)                  │ ║
║  │  Core Year 2:              ✓ COMPLETE (27 cr)                 │ ║
║  │  Core Year 3:              ✓ COMPLETE (21 cr)                 │ ║
║  │  Core Year 4:              ⚠ INCOMPLETE (24 / 27 cr)          │ ║
║  │  Electives:                ✓ COMPLETE (24 cr)                 │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                   DEFICIENCY REPORT                            │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │                                                                 │ ║
║  │  Missing Courses: 3 (9 credits)                                │ ║
║  │                                                                 │ ║
║  │  1. LLB401: Alternative Dispute Resolution Methods             │ ║
║  │     ├─ Credits: 3                                              │ ║
║  │     ├─ Category: Core Year 4                                   │ ║
║  │     ├─ Semester: Year 4, Semester 1                            │ ║
║  │     └─ Prerequisites: None                                     │ ║
║  │                                                                 │ ║
║  │  2. LLB403: Public International Laws                           │ ║
║  │     ├─ Credits: 3                                              │ ║
║  │     ├─ Category: Core Year 4                                   │ ║
║  │     ├─ Semester: Year 4, Semester 1                            │ ║
║  │     └─ Prerequisites: None                                     │ ║
║  │                                                                 │ ║
║  │  3. LLB407: Law Dissertation *** REQUIRED ***                  │ ║
║  │     ├─ Credits: 3                                              │ ║
║  │     ├─ Category: Core Year 4 (Capstone)                        │ ║
║  │     ├─ Semester: Year 4, Semester 2                            │ ║
║  │     ├─ Prerequisites: LLB404 (✓), 100 credits (✓)            │ ║
║  │     └─ Status: REQUIRED FOR GRADUATION                         │ ║
║  │                                                                 │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                     ACTION ITEMS                               │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │                                                                 │ ║
║  │  To graduate, you must:                                         │ ║
║  │    1. Complete LLB401 (3 credits)                              │ ║
║  │    2. Complete LLB403 (3 credits)                              │ ║
║  │    3. Complete LLB407 - Dissertation (3 credits) [REQUIRED]    │ ║
║  │                                                                 │ ║
║  │  Total credits needed: 9                                        │ ║
║  │  Estimated completion: 1 semester                               │ ║
║  │                                                                 │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
║  ┌────────────────────────────────────────────────────────────────┐ ║
║  │                  GRADUATION ELIGIBILITY                        │ ║
║  ├────────────────────────────────────────────────────────────────┤ ║
║  │                                                                 │ ║
║  │              ✗ NOT ELIGIBLE FOR GRADUATION                     │ ║
║  │                                                                 │ ║
║  │  Reasons:                                                       │ ║
║  │    ✗ Missing 3 core courses (9 credits)                       │ ║
║  │    ✗ Dissertation (LLB407) not completed                       │ ║
║  │                                                                 │ ║
║  │  Status: Continue enrollment to complete remaining courses     │ ║
║  │                                                                 │ ║
║  └────────────────────────────────────────────────────────────────┘ ║
║                                                                       ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  What would you like to do?                                          ║
║    [1] Export Deficiency Report                                      ║
║    [2] View Course Recommendations                                   ║
║    [3] View Detailed Breakdown                                       ║
║    [4] Save Report                                                   ║
║    [0] Return to Main Menu                                           ║
║                                                                       ║
║  Selection: _                                                        ║
║                                                                       ║
╚══════════════════════════════════════════════════════════════════════╝
```

---

## 11. Error Handling

### 11.1 Error Display Format

```
╔══════════════════════════════════════════════════════════╗
║                      ERROR                               ║
╠══════════════════════════════════════════════════════════╣
║                                                           ║
║  ✗ File Not Found                                        ║
║                                                           ║
║  The transcript file could not be found:                 ║
║  Path: tests/level3/law/missing_file.csv                ║
║                                                           ║
║  Possible causes:                                         ║
║    • File path is incorrect                              ║
║    • File has been moved or deleted                      ║
║    • Insufficient permissions                            ║
║                                                           ║
║  What would you like to do?                              ║
║    [1] Try a different file                              ║
║    [2] Browse for file                                   ║
║    [3] Return to menu                                    ║
║                                                           ║
║  Selection: _                                            ║
║                                                           ║
╚══════════════════════════════════════════════════════════╝
```

### 11.2 Error Types & Handling

**File Errors:**
```python
try:
    transcript = load_transcript(file_path)
except FileNotFoundError:
    show_error(
        title="File Not Found",
        message=f"Could not find: {file_path}",
        suggestions=[
            "Check file path",
            "Verify file exists",
            "Check permissions"
        ],
        actions=[
            ("Try different file", select_file),
            ("Browse", browse_files),
            ("Return to menu", main_menu)
        ]
    )
```

**Validation Errors:**
```python
try:
    validate_transcript_format(transcript)
except ValidationError as e:
    show_error(
        title="Invalid Transcript Format",
        message=str(e),
        suggestions=[
            "Check CSV format",
            "Verify all columns present",
            "Check for empty rows"
        ],
        actions=[
            ("View requirements", show_format_help),
            ("Try different file", select_file),
            ("Return to menu", main_menu)
        ]
    )
```

### 11.3 Warning Display

```
╔══════════════════════════════════════════════════════════╗
║                     WARNING                              ║
╠══════════════════════════════════════════════════════════╣
║                                                           ║
║  ⚠ Prerequisite Violation Detected                      ║
║                                                           ║
║  Course: LLB302 (Criminal Procedure Part 1)              ║
║  Prerequisite: LLB202 (Law of Crime)                     ║
║  Status: Prerequisite not completed                      ║
║                                                           ║
║  This may be an administrative issue. The audit will     ║
║  continue, but this violation will be flagged in the     ║
║  final report.                                            ║
║                                                           ║
║  [Press any key to continue]                             ║
║                                                           ║
╚══════════════════════════════════════════════════════════╝
```

---

## 12. Testing Strategy

### 12.1 v3.0-Specific Tests

**UI Tests:**
- Menu navigation (arrow keys, number keys)
- File selection browser
- Input validation
- Error handling display
- Progress bar functionality

**Visual Tests:**
- Color display (all terminals)
- Box drawing characters
- Symbol rendering
- Table formatting
- Panel borders

**Integration Tests:**
- All v2.0 functionality through CLI
- File operations through browser
- Export functionality
- Batch processing

### 12.2 Manual Testing Checklist

**Navigation:**
- [ ] Main menu displays correctly
- [ ] Arrow keys navigate menu
- [ ] Number keys select options
- [ ] Back navigation works
- [ ] Exit/cancel works

**File Operations:**
- [ ] File browser opens
- [ ] Directory navigation works
- [ ] File selection works
- [ ] Invalid paths handled
- [ ] Permissions checked

**Display:**
- [ ] Colors display correctly
- [ ] Symbols render properly
- [ ] Tables align correctly
- [ ] Progress bars animate
- [ ] Text wraps properly

**Functionality:**
- [ ] Level 1 works through CLI
- [ ] Level 2 works through CLI
- [ ] Level 3 works through CLI
- [ ] Batch processing works
- [ ] Export functions work

---

## 13. Implementation Guide

### 13.1 Development Phases

**Phase 1: Core CLI (Day 1, Hours 0-8)**
1. Setup window management
2. Implement main menu
3. Add file browser
4. Connect to v2.0 Level 1
5. Test basic flow

**Phase 2: UI Components (Day 1-2, Hours 8-16)**
1. Implement progress bars
2. Add color system
3. Create formatters
4. Build tables/panels
5. Test display

**Phase 3: Full Integration (Day 2, Hours 16-24)**
1. Connect Level 2
2. Connect Level 3
3. Add batch processing
4. Implement export
5. Full testing

**Phase 4: Polish (Day 3, Hours 24-32)**
1. Refine UI
2. Add help system
3. Error handling
4. Performance tuning
5. Documentation

**Phase 5: Testing & Docs (Day 3, Hours 32-40)**
1. Comprehensive testing
2. Bug fixes
3. User documentation
4. Code cleanup
5. Release preparation

### 13.2 Key Implementation Files

**main.py** (Entry Point):
```python
#!/usr/bin/env python3
"""
NSU Audit Core CLI v3.0
Main application entry point
"""

from cli.window_manager import WindowManager
from cli.menu_system import MainMenu

def main():
    # Initialize window
    window = WindowManager(title="NSU Audit Core v3.0")
    
    # Show splash screen
    window.show_splash()
    
    # Run main menu
    menu = MainMenu(window)
    menu.run()
    
    # Cleanup
    window.close()

if __name__ == "__main__":
    main()
```

**menu_system.py** (Menu Logic):
```python
from rich.console import Console
from rich.panel import Panel

class MainMenu:
    def __init__(self, window):
        self.window = window
        self.console = Console()
    
    def run(self):
        while True:
            choice = self.show_menu()
            
            if choice == '1':
                self.level1_flow()
            elif choice == '2':
                self.level2_flow()
            elif choice == '3':
                self.level3_flow()
            elif choice == '0':
                break
    
    def show_menu(self):
        self.console.clear()
        
        menu_text = """
Main Menu:
  [1] Credit Tally (Level 1)
  [2] CGPA Calculator (Level 2)
  [3] Full Graduation Audit (Level 3)
  [4] Batch Processing
  [5] View Reports
  [0] Exit
        """
        
        panel = Panel(menu_text, title="NSU Audit Core v3.0")
        self.console.print(panel)
        
        return input("Selection: ")
```

---

## 14. Success Criteria

### 14.1 Functional Requirements

✅ **CLI Interface**
- Custom window launches
- Menus work correctly
- File browser functional
- All operations accessible

✅ **Visual Design**
- Colors display properly
- Symbols render correctly
- Progress bars animate
- Tables format properly

✅ **User Experience**
- Easy to navigate
- Clear instructions
- Helpful error messages
- Quick operations

✅ **v2.0 Compatibility**
- All Level 1 functions work
- All Level 2 functions work
- All Level 3 functions work
- All 59 tests passing

### 14.2 Non-Functional Requirements

✅ **Performance**
- UI renders in < 0.5s
- Operations complete in < 5s
- No lag or freezing

✅ **Usability**
- First-time users can navigate
- Clear help available
- Intuitive menus

✅ **Compatibility**
- Works on Windows
- Works on Mac
- Works on Linux
- Terminal compatibility

### 14.3 Acceptance Criteria

**Interface:**
- [x] Window opens with title
- [x] Main menu displays
- [x] Colors show correctly
- [x] Symbols render
- [x] Progress bars work

**Navigation:**
- [x] Menu selection works
- [x] File browser works
- [x] Back navigation works
- [x] Exit works

**Functionality:**
- [x] Level 1 audit works
- [x] Level 2 CGPA works
- [x] Level 3 full audit works
- [x] Batch processing works
- [x] Export works

---

## 15. Deliverables

### 15.1 Source Code

**New Files:**
```
src/
├── cli/
│   ├── main.py
│   ├── window_manager.py
│   ├── menu_system.py
│   ├── input_handler.py
│   ├── output_formatter.py
│   └── file_browser.py
├── ui/
│   ├── progress_bars.py
│   ├── tables.py
│   ├── forms.py
│   ├── colors.py
│   └── symbols.py
└── export/
    ├── pdf_exporter.py
    ├── html_exporter.py
    └── markdown_exporter.py
```

### 15.2 Executable

**Create Executable:**
```bash
# Using PyInstaller
pyinstaller --onefile --windowed --name="NSU_Audit_v3.0" src/cli/main.py

# Creates:
dist/NSU_Audit_v3.0.exe (Windows)
dist/NSU_Audit_v3.0 (Mac/Linux)
```

### 15.3 Documentation

**Required:**
```
README_v3.0.md       - v3.0 user guide
CHANGELOG.md         - v2.0 → v3.0 changes
USER_GUIDE.md        - Complete user manual
DEVELOPER_GUIDE.md   - Developer documentation
```

---

## 16. Timeline

**Total Time**: 3 Days (40 working hours)

| Day | Hours | Phase | Tasks |
|-----|-------|-------|-------|
| 1 | 0-8 | Core CLI | Window, menus, file browser |
| 1-2 | 8-16 | UI Components | Progress, colors, tables |
| 2 | 16-24 | Integration | Connect all levels |
| 3 | 24-32 | Polish | UI refinement, help |
| 3 | 32-40 | Testing | Full testing, docs |

---

## 17. Next Steps

### 17.1 Prerequisites

Before starting v3.0:
- ✅ Complete v2.0 implementation
- ✅ All 59 tests passing
- ✅ Code reviewed and cleaned

### 17.2 Installation

```bash
# Install required libraries
pip install rich colorama tqdm click

# Test installation
python -c "from rich.console import Console; Console().print('[green]OK[/green]')"
```

### 17.3 Begin Development

```bash
# Create v3.0 branch
git checkout -b v3.0-cli-interface

# Start with main.py
python src/cli/main.py
```

### 17.4 Questions?

Everything you need is in this PRD:
- ✅ Complete specifications
- ✅ Screen designs
- ✅ Code examples
- ✅ Implementation guide
- ✅ Success criteria

**Let's build NSU Audit Core v3.0!** 🚀

---

**END OF VERSION 3.0 PRD**

**Complete Package**:
- v2.0 PRD: Multi-program audit system
- v3.0 PRD: CLI interface with UI enhancements
