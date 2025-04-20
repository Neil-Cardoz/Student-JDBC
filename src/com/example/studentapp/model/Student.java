// File: Student.java
package com.example.studentapp.model;

import java.util.Date;

/**
 * Represents a student with PRN, name, date of birth, and marks.
 */
public class Student {
    private String prn;
    private String name;
    private Date dob;
    private double marks;

    /**
     * Constructor to initialize a new Student object.
     * @param prn  the student's PRN
     * @param name the student's name
     * @param dob  the student's date of birth
     * @param marks the student's marks percentage
     */
    public Student(String prn, String name, Date dob, double marks) {
        this.prn = prn;
        this.name = name;
        this.dob = dob;
        this.marks = marks;
    }

    // Getters and setters
    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("PRN: %s | Name: %s | DoB: %tF | Marks: %.2f%%",
                prn, name, dob, marks);
    }
}
