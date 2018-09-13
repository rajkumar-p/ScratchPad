package com.diskodev;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OlaBillParse
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        String invoicesDirectory = "/Users/rajkumar.p/Documents/Bills/Ola/August2018";
        Path inputDirectory = Paths.get(invoicesDirectory);

        List<OlaInvoice> olaInvoices = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(inputDirectory, "*.{pdf}")) {
            for (Path file : directoryStream) {
                String invoiceAsText = PDFTextExtractor.getPDFTextFrom(file.toString());
                OlaInvoice olaInvoice = OlaInvoiceExtractor.extractOlaInvoiceDetails(invoiceAsText);

                if (olaInvoice != null) {
                    olaInvoices.add(olaInvoice);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        olaInvoices.sort((invoice1, invoice2) -> {
            return invoice1.getInvoiceDateTime().compareTo(invoice2.getInvoiceDateTime());
        });

        for (OlaInvoice olaInvoice : olaInvoices) {
            System.out.println(olaInvoice);
        }

        double sum = 0.00;
        sum = olaInvoices.stream()
                .mapToDouble(OlaInvoice::getInvoiceAmount)
                .sum();

        System.out.println("Total Sum : " + sum);

        String excelTemplateFileName = "/Users/rajkumar.p/Documents/Bills/Ola/Sheets/OlaBillsTemplate.xlsx";
        String invoiceMonth = "August 2018";
        ExcelInvoiceCreator.createExcelWithInvoiceDetails(invoicesDirectory,
                                                            excelTemplateFileName,
                                                            invoiceMonth,
                                                            olaInvoices);

        System.out.println("Created invoice file.");
    }
}
