package com.efe.cardscanner.model;

public class StudentCard extends Card {
    private String studentNumber;
    private String department;

    public StudentCard(String cardId, String ownerName, String studentNumber, String department) {
        super(cardId, ownerName);
        this.studentNumber = studentNumber;
        this.department = department;
    }

    public String getStudentNumber() { return studentNumber; }
    public String getDepartment() { return department; }

    @Override
    public void displayCardInfo() {
        System.out.println("--- STUDENT CARD ---");
        System.out.println("ID: " + getCardId());
        System.out.println("Name: " + getOwnerName());
        System.out.println("Student No: " + studentNumber);
        System.out.println("Department: " + department);
    }
}