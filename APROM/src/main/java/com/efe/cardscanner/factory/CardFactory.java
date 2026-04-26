package com.efe.cardscanner.factory;

import com.efe.cardscanner.model.Card;
import com.efe.cardscanner.model.StaffCard;
import com.efe.cardscanner.model.StudentCard;
import com.efe.cardscanner.model.ExchangeStudentCard;
import com.efe.cardscanner.exception.InvalidCardException;

public class CardFactory {

    public static Card createCard(String scannedData) throws InvalidCardException {
        if (scannedData == null || scannedData.trim().isEmpty()) {
            throw new InvalidCardException("Scan error: Read data is empty!");
        }

        String[] parts = scannedData.split(",");

        if (parts.length < 4) {
            throw new InvalidCardException("Incomplete card data read! Data format is invalid.");
        }

        String cardType = parts[0].toUpperCase();

        try {
            if (cardType.equals("STD")) {
                return new StudentCard(parts[1], parts[2], parts[3], parts[4]);
            } else if (cardType.equals("STF")) {
                int accessLevel = Integer.parseInt(parts[3]);
                return new StaffCard(parts[1], parts[2], accessLevel, parts[4]);
            } else if (cardType.equals("EXC")) {
                if (parts.length < 7) {
                    throw new InvalidCardException("Missing data fields for exchange student card!");
                }
                return new ExchangeStudentCard(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
            } else {
                throw new InvalidCardException("Unknown card format: " + cardType);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCardException("Data conversion error (Invalid number format): " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCardException("Data structure error: Missing fields for card type " + cardType);
        } catch (Exception e) {
            throw new InvalidCardException("An unexpected error occurred while creating the card: " + e.getMessage());
        }
    }
}