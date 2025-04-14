# Selenium Automation Framework

This repository contains a Selenium-based automation framework written in Java. The framework is designed to simplify the process of writing, running, and managing automated tests for web applications, offering a modular and scalable solution for test automation.

---

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Modular Design**: Each component of the framework is designed to be reusable and extendable.
- **Cross-Browser Support**: Easily test applications across multiple browsers like Chrome, Firefox, and Edge.
- **Configuration-Driven Development**: Centralized configuration files for managing settings such as browser preferences, timeouts, and test data.
- **Reporting**: Automatically generates detailed test execution reports.
- **Error Logging**: Provides in-depth logs for debugging test failures.
- **Page Object Model (POM)**: Implements POM to enhance code readability and maintainability.
- **Scalability**: Can be scaled to include additional test cases, test suites, and integrations.
- **Integration Ready**: Supports integration with CI/CD pipelines like Jenkins.

---

## Prerequisites

Before using this framework, ensure that you have the following installed:

- **Java Development Kit (JDK)** (Version 8 or higher)
- **Apache Maven** (For build and dependency management)
- **Selenium WebDriver** (Ensure your desired WebDriver binaries are in the system path)
- **Web Browsers** (e.g., Google Chrome, Mozilla Firefox)

---

## Installation

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/ranjansingh04/selenium-automation-framework.git
2. Navigate to the project directory:
   ```bash
   cd selenium-automation-framework
3. Build the project using Maven:
   ```bash
   mvn clean install
   
---

## Usage

1. Configure the Framework:
   Update the configuration files (e.g., config.properties) to define browser preferences, base URLs, and other test settings.
   Place your test data files in the appropriate directory (e.g., /src/test/resources).
2. Write Test Cases:
   Create new test cases by extending the base test class and implementing the required test methods.
3. Run Tests:
   Use Maven to execute test cases:
   ```bash
   mvn test 
   ```  
   Alternatively, run specific test suites using:
   ```bash
   mvn test -DsuiteXmlFile=<suite-name>.xml
   ```
4. View Test Reports:
   After execution, detailed test reports can be found in the target/surefire-reports directory.

---

## Project Structure
```text
selenium-automation-framework/
├── src
    ├── main
    │   ├── java
    │   │   └── com                                 # Contains your main source code.
    │   │       ├── config                          # Configuration-related classes.
    │   │       ├── data.changeless                 # Constant data or static datasets.
    │   │       ├── driver                          # WebDriver-related setup and handling.
    │   │       ├── enums                           # Java Enums for predefined sets of constants.
    │   │       ├── exceptions                      # Custom exception classes.
    │   │       ├── page                            # Page Object Model classes.
    │   │       ├── report                          # Reporting utilities.
    │   │       └── utils                           # Helper/utility classes.
    │   └── resources                               # Resource files like log4j2.properties for logging configurations.
    │       └── log4j2.properties
    └── test    
        ├── java
        │   └── com
        │       ├── data                            # Test data classes.
        │       │   └── LoginTestData
        │       └── test                            # Test classes, grouped by feature or functionality.
        │           ├── JSAlertsTest
        │           ├── LoginTest
        │           └── BaseWeb
        └── resources                               # Resource files used during testing.
            ├── META-INF.services                   # Service provider configuration.
            └── suites                              # Test suite XML configurations for TestNG or Selenium Grid.
                ├── local.xml
                ├── selenium-grid.xml
                ├── allure.properties
                ├── general.properties
                ├── local.properties
                └── selenium-grid.properties
```

---

## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute this project as per the license terms.