package com.efe.cardscanner.security;

import com.efe.cardscanner.model.Card;
import com.efe.cardscanner.model.StaffCard;
import com.efe.cardscanner.model.StudentCard;

public class AccessValidator {

    public boolean checkEntry(Card card, String zone) {
        System.out.println("🔍 Security check in progress: Target Zone -> " + zone);

        if (card instanceof StaffCard) {
            StaffCard staff = (StaffCard) card;
            if (zone.equals("Management") && staff.getAccessLevel() < 3) {
                System.out.println("❌ ACCESS DENIED: Insufficient access level for this zone!");
                return false;
            }
        } else if (card instanceof StudentCard) { // This covers both StudentCard and ExchangeStudentCard
            if (zone.equals("Management") || zone.equals("System Room")) {
                System.out.println("❌ ACCESS DENIED: Students are not allowed in this zone!");
                return false;
            }
        }

        System.out.println("✅ ACCESS GRANTED: Opening door.");
        return true;
    }
}