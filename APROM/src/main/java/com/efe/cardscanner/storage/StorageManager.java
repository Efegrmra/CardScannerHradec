package com.efe.cardscanner.storage;

import com.efe.cardscanner.model.Card;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class StorageManager {

    private static StorageManager instance;
    private static final String FILE_NAME = "scan_history.txt";

    private StorageManager() {
    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    public void saveCardScan(Card card) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = card.getIssueDate().format(formatter);

            String log = String.format("[%s] SCAN ATTEMPT -> ID: %s | Name: %s | Card Type: %s",
                    formattedDate, card.getCardId(), card.getOwnerName(), card.getClass().getSimpleName());

            pw.println(log);
            System.out.println("💾 Log saved to system: " + card.getOwnerName());

        } catch (IOException e) {
            System.err.println("File save error: " + e.getMessage());
        }
    }
}