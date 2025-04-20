# Student Data Entry Application

**Name:** Your Name  
**PRN:** YourPRN  
**Batch:** YourBatch

## Description

A menu-driven Java console application for managing student records with persistent MySQL storage via JDBC.

## Features

- Add new student records (PRN, Name, Date of Birth, Marks)  
- Display all students  
- Search by PRN, Name, or Position  
- Update existing student details  
- Delete students  
- Persistent storage in MySQL

## Prerequisites

- Java Development Kit (JDK 8+)  
- MySQL Server  
- MySQL JDBC Driver (Connector/J)

## Project Structure

```
student-data-entry/
├── src/
│   └── com/example/studentapp/
│       ├── dao/
│       │   └── StudentDAO.java
│       ├── model/
│       │   └── Student.java
│       ├── util/
│       │   └── DBConnection.java
│       └── StudentApp.java
├── studentdb.sql
└── README.md
```

## Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/student-data-entry.git
   cd student-data-entry
   ```

2. **Initialize the database**
   ```bash
   mysql -u root -p < studentdb.sql
   ```
   This creates the `studentdb` database and `students` table.

3. **Configure credentials**
   In `src/com/example/studentapp/util/DBConnection.java`, update:
   ```java
   private static final String URL      = "jdbc:mysql://localhost:3306/studentdb";
   private static final String USER     = "appuser";         // your DB user
   private static final String PASSWORD = "StrongP@ssw0rd"; // your DB password
   ```

## Running the Application

Compile and run using your IDE or command line:
```bash
javac -d bin src/com/example/studentapp/**/*.java
java -cp bin;path/to/mysql-connector-java.jar com.example.studentapp.StudentApp
```

## Usage

Upon launch, follow the console menu:
1. Add Student  
2. Display All Students  
3. Search by PRN  
4. Search by Name  
5. Search by Position  
6. Update Student  
7. Delete Student  
0. Exit

Enter the corresponding number and follow prompts to perform operations.

## Contributing

1. Fork the repository  
2. Create a branch: `git checkout -b feature/XYZ`  
3. Commit: `git commit -m "Add XYZ feature"`  
4. Push: `git push origin feature/XYZ`  
5. Open a Pull Request

## License

This project is licensed under the MIT License.

