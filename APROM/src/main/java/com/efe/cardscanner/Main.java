package com.efe.cardscanner;

import com.efe.cardscanner.device.Scanner;
import com.efe.cardscanner.device.FileScanner;
import com.efe.cardscanner.factory.CardFactory;
import com.efe.cardscanner.model.Card;
import com.efe.cardscanner.exception.InvalidCardException;
import com.efe.cardscanner.security.AccessValidator;
import com.efe.cardscanner.storage.StorageManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CAMPUS CARD SCANNER SYSTEM (LIVE) ===\n");

        StorageManager storage = StorageManager.getInstance();
        AccessValidator security = new AccessValidator();

        Scanner realScanner = new FileScanner();

        System.out.println("--- Waiting for Live Scan ---");

        String scannedData = realScanner.scan();

        if (scannedData != null && !scannedData.isEmpty()) {
            try {
                Card card = CardFactory.createCard(scannedData);
                card.displayCardInfo();
                security.checkEntry(card, "Management");
                storage.saveCardScan(card);
            } catch (InvalidCardException e) {
                System.err.println("SECURITY WARNING: " + e.getMessage());
            }
        } else {
            System.out.println("No data scanned. System shutting down.");
        }
    }
}