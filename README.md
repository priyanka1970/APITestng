# 🧪 API Automation Framework

This is a Java-based API Automation Framework built using:

- Maven
- Cucumber (BDD)
- TestNG
- Rest Assured
- ExtentReports
- Allure Reports

---

## 🔧 Tech Stack

- **Java 8** – Programming Language
- **Maven** – Build and dependency management
- **TestNG** – Test execution & parallel execution
- **Cucumber** – BDD (Behavior Driven Development)
- **Rest Assured** – API testing
- **ExtentReports** – Rich HTML test reporting
- **Allure Reports** – Interactive and detailed test reporting

---

## 📁 Project Structure

```
assignment/
├── src/
│   ├── main/java/                   → Application code (if any)
│   └── test/java/
│       ├── stepdefinitions/         → Cucumber Step Definitions
│       ├── runners/                 → TestRunner class
│       └── utils/                   → Utilities / Helpers
│
│   └── test/resources/
│       └── features/                → Cucumber Feature files
│
├── testng.xml                       → TestNG Suite Configuration
├── pom.xml                          → Maven Configuration
└── README.md                        → Project Documentation
```

---

## ▶️ How to Run Tests

### 📦 Prerequisites

- Java 8 installed
- Maven installed (`mvn -version`)
- IDE like IntelliJ IDEA or Eclipse

### 🏃‍♂️ Run via Command Line

To run all tests:

```bash
mvn clean test
```

To run a specific TestNG runner:

```bash
mvn clean test -Dtest=runners.TestRunner
```

---
### 🟢 To start FastAPI manually
```bash
uvicorn fastapi_app.main:app --reload
git add .
gti commit -m"new commit"
git push -u origin main
```


## 📊 Reports

### 🟢 Allure Report

To generate and open the Allure report:

```bash
mvn clean test
allure serve
```

> 📌 **Note:** Allure CLI must be installed.  
> [Install Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline)

### 🟣 Extent Report

Extent report is auto-generated after execution.  
Check under:

```
test-output/ExtentReport/
```

Ensure your `extent.properties` is properly configured (if used).

---

## 🧪 Sample Feature File

```gherkin
Feature: User API Validation

  Scenario: Get user by ID
    Given I hit the GET endpoint "/api/users/2"
    Then the response status code should be 200
    And the response body should contain "id"
```

---

## ℹ️ Additional Notes

- `TestRunner.java` must be under `src/test/java/runners/` so Maven Surefire can detect it.
- Framework supports BDD using Gherkin syntax.
- Extensible for environment configs, test data management, parallel execution, and CI/CD pipelines.

---