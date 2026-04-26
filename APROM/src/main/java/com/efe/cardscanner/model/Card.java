package com.efe.cardscanner.model;

import java.time.LocalDateTime;

public abstract class Card {
    private String cardId;
    private String ownerName;
    private LocalDateTime issueDate;

    public Card(String cardId, String ownerName) {
        this.cardId = cardId;
        this.ownerName = ownerName;
        this.issueDate = LocalDateTime.now();
    }

    public String getCardId() { return cardId; }
    public String getOwnerName() { return ownerName; }
    public LocalDateTime getIssueDate() { return issueDate; }

    public abstract void displayCardInfo();
}