package com.diskodev;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OlaInvoiceExtractor {
    public static OlaInvoice extractOlaInvoiceDetails(String olaInvoiceText) {
        String totalBillPattern = "Total Bill\\s+\\*?\\s*\\(rounded off\\)\\s+(?:Includes\\s+\\S*\\s+Taxes\\s*)?₹(\\d+)";
        String invoiceIDPattern = "Invoice ID\\s+(\\w+)\\s+Invoice Date\\s+((\\d{2})/(\\d{2})/(\\d{4}))";
        String timePattern = "\\d{2}:\\d{2}\\s+(AM|PM)";
        String fileNamePattern = "Customer Ride Number - (\\w+)";

        double invoiceAmount = 0.00;
        String invoiceID = null;
        String invoiceDate = null;
        String invoiceTime = null;
        String invoiceFileName = null;

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

        matcher.reset();

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        LocalDateTime invoiceDateTime =
                LocalDateTime.parse(invoiceDate + " " + invoiceTime, dateTimeFormatter);

        matcher.usePattern(Pattern.compile(fileNamePattern));
        if (matcher.find()) {
            invoiceFileName = matcher.group(1);
        }

        matcher.reset();

        if (invoiceAmount == 0.00 ||
                Utils.isNull(invoiceID) ||
                Utils.isNull(invoiceDate) ||
                Utils.isNull(invoiceFileName)) {
            System.out.println(String.format("Rejecting : (%s, %s, %s, %s)",
                    invoiceAmount, invoiceID, invoiceDateTime, invoiceFileName));
            return null;
        }

        return new OlaInvoice(invoiceAmount, invoiceID, invoiceDateTime, invoiceFileName);
    }

    public static List<OlaInvoice> getInvoiceForEachFile(String invoicesDirectory)
            throws IOException {
        List<OlaInvoice> olaInvoices = new ArrayList<>();
        Path inputDirectory = Paths.get(invoicesDirectory);
        DirectoryStream<Path> directoryStream =
                Files.newDirectoryStream(inputDirectory, "*.{pdf}");
        for (Path file : directoryStream) {
            System.out.println(file.toString());
            String invoiceAsText =
                    PDFTextExtractor.getPDFTextFrom(file.toString());
            OlaInvoice olaInvoice =
                    OlaInvoiceExtractor.extractOlaInvoiceDetails(invoiceAsText);

            if (olaInvoice != null) {
                olaInvoices.add(olaInvoice);
            }
        }

        return olaInvoices;
    }
}
