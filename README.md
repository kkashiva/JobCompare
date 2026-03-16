# Job Offer Comparison App

Android application that allows users to **compare job offers across multiple dimensions** such as salary, bonus, benefits, cost of living, and work flexibility. The app ranks job opportunities using configurable weights so users can objectively evaluate competing offers.

This project was developed as part of **CS6300: Software Development Process** in the **Georgia Tech OMSCS program**.

---

# Overview

Choosing between multiple job offers can be difficult when compensation packages vary across salary, bonuses, benefits, and location. This application helps users make better decisions by:

- Storing their **current job and multiple job offers**
- Adjusting for **cost of living differences**
- Assigning **custom weights to compensation components**
- Calculating a **weighted job score**
- Ranking and comparing job opportunities side-by-side

The application runs locally on Android and stores data in a local SQLite database.

---

# Features

## Job Management

- Enter and store **current job details**
- Add **multiple job offers**
- Edit existing job information
- Persist job data locally using SQLite

## Job Comparison

- Automatically calculate a **weighted job score**
- Rank jobs based on compensation and benefits
- Select **two jobs to compare side-by-side**

## Adjustable Comparison Settings

Users can customize importance weights for:

- Yearly salary
- Yearly bonus
- Training & development fund
- Leave time
- Telework days per week

## Cost of Living Adjustment

Compensation values are normalized using a cost-of-living index.

Adjusted Yearly Salary:
```
AYS = (YearlySalary * 100) / CostOfLivingIndex
```
Adjusted yearly bonus uses the same formula.

---

# Tech Stack

| Component | Technology |
|---|---|
| Platform | Android |
| Language | Java 17 |
| IDE | Android Studio |
| Database | SQLite |
| Build System | Gradle (Groovy DSL) |
| Testing | JUnit |

Minimum supported Android version: **API Level 33 (Android 13)**.

---

# Application Architecture

The system follows a **layered architecture** separating UI, domain models, and persistence.

### Main Components

**View Layer**
- Android activities and UI components

**Model Layer**
- `Job`
- `JobOffer`
- `ComparisonSetting`

**Persistence Layer**
- SQLite database
- Database helper classes

The application stores all job data locally and does not require internet connectivity.

---

# System Diagrams

## Use Case Diagram

![Use Case Diagram](GroupProject/Docs/images/use_case_diagram.png)

Shows the primary user interactions including entering jobs, adjusting comparison settings, and comparing offers.

---

## Component Architecture

![Component Diagram](GroupProject/Docs/images/component_diagram.png)

The system separates UI components, business logic models, and database persistence.

---

## Class Diagram

![Class Diagram](GroupProject/Docs/images/class_diagram.png)

Represents the static structure of the core classes and their relationships.

---

## Deployment Diagram

![Deployment Diagram](images/deployment_diagram.png)

Since this is a single-user application, the Android app connects to a local SQLite database.

---

# User Interface

The application contains several screens.

## Main Menu

Entry point of the application where users can:

- Enter current job
- Edit current job
- Add job offers
- Adjust comparison weights
- Compare job offers

## Job Entry Screens

Users input:

- Title
- Company
- Location (state, city)
- Cost of living index
- Salary
- Bonus
- Training & development fund
- Leave time
- Telework days

## Comparison Settings

Allows users to assign weights to compensation components used in ranking jobs.

## Job Comparison

Displays a ranked table of jobs and allows users to select two offers for side-by-side comparison.

---

# Testing

Testing was performed using both automated and manual approaches.

## Automated Testing

JUnit tests validate core logic such as the job score calculation.

Example input parameters:

- Adjusted salary
- Adjusted bonus
- Training fund
- Leave time
- Telework days
- Weight parameters

Expected output:

Correct weighted job score.

## Manual Testing

Manual tests verified:

- UI workflows
- Database persistence
- Input validation
- Job ranking behavior
- Comparison screen functionality

Target metrics:

- ≥70% code coverage
- ≥95% test case pass rate

---

# Development Process

The project followed a structured development lifecycle.

1. Planning
2. Architecture and design
3. Implementation
4. Testing

Key deliverables included UML diagrams, architecture documentation, working Android application, and test plans.

---

# Project Team

**CS6300 – Software Development Process**  
Georgia Institute of Technology (OMSCS)

Team 047

- Thu Nguyen
- Yunhe Cui
- Kartikeya Kashiva
- Brenda Njeri

---

# Running the Project

## Requirements

- Android Studio
- Android SDK API Level 33+
- Java 17

## Setup

Clone the repository:

```bash
git clone https://github.com/<your-username>/<repo-name>.git
```

Open the project in Android Studio and run it using either:
- Android emulator
- Android device running API 33+
