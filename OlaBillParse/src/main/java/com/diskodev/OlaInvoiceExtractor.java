package com.diskodev;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OlaInvoiceExtractor {
    public static OlaInvoice extractOlaInvoiceDetails(String olaInvoiceText) {
        String totalBillPattern = "Total Bill\\s+\\*?\\s*\\(rounded off\\)\\s+(?:Includes\\s+\\S*\\s+Taxes\\s*)?₹(\\d+)";
        String invoiceIDPattern = "Invoice ID\\s+(\\w+)\\s+Invoice Date\\s+((\\d{2})/(\\d{2})/(\\d{4}))";
        String timePattern = "\\d{2}:\\d{2}\\s+(AM|PM)";

        double invoiceAmount = 0.00;
        String invoiceID = null;
        String invoiceDate = null;
        String invoiceTime = null;

        Matcher matcher;
        matcher = Pattern.compile(totalBillPattern).matcher(olaInvoiceText);
        if (matcher.find()) {
            invoiceAmount = Double.parseDouble(matcher.group(1));
        }

        matcher.reset();

        matcher.usePattern(Pattern.compile(invoiceIDPattern));
        if (matcher.find()) {
            invoiceID = matcher.group(1);
            invoiceDate = matcher.group(2);
        }

        matcher.reset();

        matcher.usePattern(Pattern.compile(timePattern));
        if (matcher.find()) {
            invoiceTime = matcher.group();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        LocalDateTime invoiceDateTime = LocalDateTime.parse(invoiceDate + " " + invoiceTime, dateTimeFormatter);

        if (invoiceAmount == 0.00 || invoiceID == null || invoiceDateTime == null) {
            System.out.println(String.format("Rejecting : (%s, %s, %s)", invoiceAmount, invoiceID, invoiceDateTime));
            return null;
        }

        return new OlaInvoice(invoiceAmount, invoiceID, invoiceDateTime);
    }
}
