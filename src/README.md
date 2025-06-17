# API Automation Framework for FastAPI

This project is a **Test Automation Framework** designed to validate all major functionalities of a **FastAPI implementation** using Java, TestNG, Cucumber, and RestAssured.

## Technologies Used

Java 8+, Maven (Dependency management & build tool), TestNG (Test execution framework), Cucumber (Behavior-Driven Development framework), RestAssured (API automation library), Extent Reports & Allure Reports (Test reporting tools), and FastAPI (Python-based API framework).

## Setup Instructions

Ensure you have **Java 8+**, Maven, and **Python & FastAPI** installed (`pip install fastapi uvicorn`). Clone the repository using `git clone <repository-url>` and navigate to the project folder (`cd assignment`). Install dependencies by running `mvn clean install`.

Before running tests, **start the FastAPI backend** using `python -m uvicorn app:app --host 127.0.0.1 --port 8000 --reload`. Verify the health endpoint using `curl -X GET http://127.0.0.1:8000/health`. Then execute the API tests using `mvn clean test`.

## Generating Reports

Generate **Allure Reports** using `allure generate target/allure-results --clean -o test-output/allure-report` and view them using `allure serve test-output/allure-report`. **Extent Reports** are stored inside `target/cucumber-html-report`.

## Configuration Management

Base URL and environment variables are managed in `ConfigUtil.java`. Modify API settings by updating `String baseUrl = ConfigUtil.getProperty("baseUrl");`.

## CI/CD Integration

Automate testing in **GitHub Actions** using a `.github/workflows/api-tests.yml` workflow. This includes setting up Java, installing dependencies, running API tests, and generating an Allure report.

## Future Enhancements

Enhancing FastAPI logs within Allure reports, improving API request validation using schema validation, and automating environment setup for different stages (Dev, QA, Prod).

## License

This project is licensed under **MIT License**.