
// File: StudentDAO.java
package com.example.studentapp;

import com.example.studentapp.model.Student;
import com.example.studentapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object for CRUD operations on Student table.
 */
public class StudentDAO {

    /**
     * Inserts a new student record into the database.
     */
    public void addStudent(Student s) {
        String sql = "INSERT INTO students (prn, name, dob, marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getPrn());
            ps.setString(2, s.getName());
            ps.setDate(3, new java.sql.Date(s.getDob().getTime()));
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    /**
     * Retrieves all students from the database.
     */
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT prn, name, dob, marks FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("prn"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getDouble("marks")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return list;
    }

    /**
     * Searches a student by PRN.
     */
    public Student findByPrn(String prn) {
        String sql = "SELECT prn, name, dob, marks FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("prn"),
                            rs.getString("name"),
                            rs.getDate("dob"),
                            rs.getDouble("marks")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching student: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates an existing student record.
     */
    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, dob=?, marks=? WHERE prn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setDate(2, new java.sql.Date(s.getDob().getTime()));
            ps.setDouble(3, s.getMarks());
            ps.setString(4, s.getPrn());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    /**
     * Deletes a student record by PRN.
     */
    public void deleteStudent(String prn) {
        String sql = "DELETE FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prn);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }
}
