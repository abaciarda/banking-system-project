package abaciarda.bankingsystem.utils;

import java.time.LocalDate;

public class GenerateIban {
    public static String generateIban(String ssn) {
        String countryCode = "TR";

        String year = String.valueOf(LocalDate.now().getYear()).substring(2);

        String bankCode = "1905";

        String ssnStr = ssn.substring(ssn.length() - 5);

        return countryCode + year + bankCode + ssnStr;
    }
}
