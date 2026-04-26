package com.efe.cardscanner.model;

public class ExchangeStudentCard extends StudentCard {
    private String homeUniversity;
    private String programName;

    public ExchangeStudentCard(String cardId, String ownerName, String studentNumber, String department, String homeUniversity, String programName) {
        super(cardId, ownerName, studentNumber, department);
        this.homeUniversity = homeUniversity;
        this.programName = programName;
    }

    public String getHomeUniversity() { return homeUniversity; }
    public String getProgramName() { return programName; }

    @Override
    public void displayCardInfo() {
        System.out.println("--- EXCHANGE STUDENT CARD ---");
        System.out.println("ID: " + getCardId());
        System.out.println("Name: " + getOwnerName());
        System.out.println("Student No: " + getStudentNumber());
        System.out.println("Host Department: " + getDepartment());
        System.out.println("Home University: " + homeUniversity);
        System.out.println("Exchange Program: " + programName);
    }
}