package com.efe.cardscanner.model;

public class StaffCard extends Card {
    private int accessLevel;
    private String title;

    public StaffCard(String cardId, String ownerName, int accessLevel, String title) {
        super(cardId, ownerName);
        this.accessLevel = accessLevel;
        this.title = title;
    }

    public int getAccessLevel() { return accessLevel; }
    public String getTitle() { return title; }

    @Override
    public void displayCardInfo() {
        System.out.println("--- STAFF CARD ---");
        System.out.println("ID: " + getCardId());
        System.out.println("Name: " + getOwnerName());
        System.out.println("Title: " + title);
        System.out.println("Access Level: " + accessLevel);
    }
}