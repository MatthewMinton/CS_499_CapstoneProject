# CS 499 Capstone Project – Rescue Animal Management System  

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk) ![Maven](https://img.shields.io/badge/Build-Maven-green?logo=apache-maven) ![SQLite](https://img.shields.io/badge/Database-SQLite-lightgrey?logo=sqlite) ![SQL](https://img.shields.io/badge/Language-SQL-darkblue?logo=postgresql) ![IDE](https://img.shields.io/badge/IDE-IntelliJ_IDEA-purple?logo=intellij-idea)

## Overview
This project is a **Rescue Animal Management System** developed as part of the Computer Science Capstone course. It is an **enhancement of the IT 145: Foundations in Application Development Project 2 assignment**, redesigned and expanded to meet professional standards. The system simulates the operations of a rescue animal organization, where users can intake new animals, update their training status, reserve/unreserve animals, and display current records.

The project demonstrates skills in **software design, database integration, object-oriented programming, input validation, and testing**.

<div align="center">
  <h2>Milestone One Code Review</h2>
  <iframe width="560" height="315"
          src="https://www.youtube.com/embed/y5Y3bRJQNIU&t=5s"
          title="Milestone One Code Review"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen>
  </iframe>
</div>

---

## Features
- **Animal Intake**: Add new rescue dogs or monkeys into the system.  
- **Training Management**: Track training progress and update training stages.  
- **Reservation System**: Reserve or unreserve animals for service.  
- **Data Validation**: Validate user input to prevent invalid or corrupted entries.  
- **Database Integration**: Store data using **SQLite** with helpers for production and testing.  
- **Console Menu Interface**: Interactive menu for navigating features.  
- **JUnit Testing**: Includes tests for core functionality such as animals, validation, and the manager.  

---

## Project Structure
```text
CS_499_CapstoneProject/
│
├── src/
│   ├── main/java/com/matthew/animalapp/
│   │   ├── AnimalManager.java        # Core class for managing animals
│   │   ├── DatabaseConnector.java    # Connects to SQLite database
│   │   ├── DatabaseHelper.java       # Database operations
│   │   ├── FakeDatabaseHelper.java   # Mock database for testing
│   │   ├── Dog.java                  # Dog subclass
│   │   ├── Monkey.java               # Monkey subclass
│   │   ├── RescueAnimal.java         # Base class for all rescue animals
│   │   ├── MenuController.java       # Console-based user menu
│   │   ├── Validation.java           # Input validation logic
│   │   └── Driver.java               # Program entry point
│   │
│   └── resources/
│       └── META-INF/                 # Maven metadata
│
├── test/java/com/matthew/animalapp/
│   ├── AnimalManagerTest.java
│   ├── DogTest.java
│   ├── MonkeyTest.java
│   ├── RescueAnimalTest.java
│   └── ValidationTest.java
│
├── README.md
├── pom.xml
├── .gitignore
└── .env
```
---

## How to Run

### Requirements
- **Java 17 or higher**  
- **Maven** installed (`mvn -v` to check)  
- **SQLite** (bundled via JDBC, no external setup required)

### Steps
1. Clone the repository:
```bash
git clone https://github.com/your-username/CS_499_CapstoneProject.git
cd CS_499_CapstoneProject
```

2. Compile the project
```bash
mvn clean install
```

4. Run the application
```bash
 mvn exec:java -Dexec.mainClass="com.matthew.animalapp.Driver"
```

## Example of Main Menu
  ```text
  ****************************************
  *    Grazioso Salvare Rescue Manager   *
  ****************************************
   Please choose an option below:
   [1] Intake a new animal
   [2] Reserve/Unreserve an animal
   [3] Print list of all dogs
   [4] Print list of all monkeys
   [5] Print available animals
   [6] Update training status
   [7] Remove an animal
   [q] Quit application
```


## Comparison to IT 145 Project 2
This project began as **IT 145 Project 2: Animal Shelter Application**, which provided a simple, console-based program with limited functionality.  
In this capstone enhancement, major improvements were made:  

- Added **SQLite database integration** instead of storing animals in memory.  
- Introduced a **Validation class** to handle input checking and error prevention.  
- Refactored the code to use **HashMaps** for efficient lookups instead of basic arrays/lists.  
- Implemented **JUnit tests** for unit-level verification and regression prevention.  
- Converted the project into a **Maven build** with proper dependency management.  
- Expanded the **MenuController** for a more user-friendly console interface.  
- Designed a **FakeDatabaseHelper** to support test-driven development.  

These enhancements turn the original beginner-level project into a **scalable, testable, and more professional application**.

## Learning Outcomes
This project demonstrates:
- Applying **object-oriented design principles**  
- Building with **Maven** for dependency management  
- **Database interaction** with SQLite  
- **Testing with JUnit** for software reliability  
- **Error handling and validation** for real-world scenarios  
- Expanding an earlier course project into a professional-quality system

## Author
**Matthew Minton**  
Computer Science Capstone – CS 499  

