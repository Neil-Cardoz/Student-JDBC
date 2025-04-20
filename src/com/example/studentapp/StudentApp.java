// File: StudentApp.java (Main)
package com.example.studentapp;

import com.example.studentapp.StudentDAO;
import com.example.studentapp.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Name: Your Name
 * PRN : YourPRN
 * Batch: YourBatch
 *
 * Menu-driven application for Student Data Entry using MySQL and JDBC.
 */
public class StudentApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayAll();
                case 3 -> searchByPrn();
                case 4 -> searchByName();
                case 5 -> searchByPosition();
                case 6 -> updateStudent();
                case 7 -> deleteStudent();
                case 0 -> System.out.println("Exiting application. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n=== Student Data Entry Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search by PRN");
        System.out.println("4. Search by Name");
        System.out.println("5. Search by Position");
        System.out.println("6. Update Student");
        System.out.println("7. Delete Student");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter PRN: ");
            String prn = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter DoB (yyyy-MM-dd): ");
            Date dob = sdf.parse(scanner.nextLine());
            System.out.print("Enter Marks (%): ");
            double marks = Double.parseDouble(scanner.nextLine());
            Student s = new Student(prn, name, dob, marks);
            dao.addStudent(s);
            System.out.println("Student added successfully.");
        } catch (ParseException e) {
            System.err.println("Invalid date format. Use yyyy-MM-dd.");
        }
    }

    private static void displayAll() {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void searchByPrn() {
        System.out.print("Enter PRN to search: ");
        Student s = dao.findByPrn(scanner.nextLine());
        System.out.println(s != null ? s : "Student not found.");
    }

    private static void searchByName() {
        System.out.print("Enter name keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        List<Student> list = dao.getAllStudents();
        list.stream()
                .filter(s -> s.getName().toLowerCase().contains(keyword))
                .forEach(System.out::println);
    }

    private static void searchByPosition() {
        System.out.print("Enter position (0-based index): ");
        int pos = Integer.parseInt(scanner.nextLine());
        List<Student> list = dao.getAllStudents();
        if (pos >= 0 && pos < list.size()) {
            System.out.println(list.get(pos));
        } else {
            System.out.println("Invalid position.");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter PRN of student to update: ");
        String prn = scanner.nextLine();
        Student existing = dao.findByPrn(prn);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }
        try {
            System.out.print("Enter new Name (leave blank to keep " + existing.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) existing.setName(name);
            System.out.print("Enter new DoB (yyyy-MM-dd, leave blank to keep " + sdf.format(existing.getDob()) + "): ");
            String dobStr = scanner.nextLine();
            if (!dobStr.isBlank()) existing.setDob(sdf.parse(dobStr));
            System.out.print("Enter new Marks (leave blank to keep " + existing.getMarks() + "): ");
            String marksStr = scanner.nextLine();
            if (!marksStr.isBlank()) existing.setMarks(Double.parseDouble(marksStr));
            dao.updateStudent(existing);
            System.out.println("Student updated successfully.");
        } catch (ParseException e) {
            System.err.println("Invalid date format. Update aborted.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter PRN of student to delete: ");
        dao.deleteStudent(scanner.nextLine());
        System.out.println("Student deleted if existed.");
    }
}
