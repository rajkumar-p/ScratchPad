package com.diskodev;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelInvoiceCreator {
    public static void createExcelWithInvoiceDetails(String outputDirectory,
                                                     String excelTemplateFileName,
                                                     String invoiceMonth,
                                                     List<OlaInvoice> olaInvoices)
    {
        try {
            String excelOutputFileName = String.format("%s/%s.xlsx", outputDirectory, invoiceMonth);
            Files.copy(Paths.get(excelTemplateFileName), Paths.get(excelOutputFileName), StandardCopyOption.REPLACE_EXISTING);
            Workbook workbook = WorkbookFactory.create(new File(excelOutputFileName));
            Sheet firstSheet = workbook.getSheetAt(0);

            Row monthRow = firstSheet.getRow(6);
            Cell monthCell = monthRow.getCell(1);

            monthCell.setCellType(CellType.STRING);
            monthCell.setCellValue(invoiceMonth);

            int invoiceEntryRowStartIndex = 8;
            int invoiceEntryRowIndex = invoiceEntryRowStartIndex;
            int serialNumber = 1;
            for (OlaInvoice olaInvoice : olaInvoices) {
                String invoiceID = olaInvoice.getInvoiceID();
                String invoiceDate = olaInvoice.getInvoiceDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                double invoiceAmount = olaInvoice.getInvoiceAmount();

                Row currentRow = firstSheet.getRow(invoiceEntryRowIndex);

                Cell serialCell = currentRow.getCell(0);
                serialCell.setCellValue(serialNumber);

                Cell tripDateCell = currentRow.getCell(1);
                tripDateCell.setCellValue(invoiceDate);

                Cell cabAutoCell = currentRow.getCell(2);
                cabAutoCell.setCellValue("Cab/Auto");

                Cell invoiceNumberCell = currentRow.getCell(3);
                invoiceNumberCell.setCellValue(invoiceID);

                Cell invoiceDateCell = currentRow.getCell(4);
                invoiceDateCell.setCellValue(invoiceDate);

                Cell invoiceAmountCell = currentRow.getCell(5);
                invoiceAmountCell.setCellType(CellType.NUMERIC);
                invoiceAmountCell.setCellValue(invoiceAmount);

                ++invoiceEntryRowIndex;
                ++serialNumber;
            }

            Cell totalAmountCell = firstSheet.getRow(47).getCell(5);
            totalAmountCell.setCellType(CellType.FORMULA);

            String totalAmountFormula = String.format("SUM(F9:F%d)", invoiceEntryRowIndex);
            totalAmountCell.setCellFormula(totalAmountFormula);

            FileOutputStream excelOutFile = new FileOutputStream(invoiceMonth);
            workbook.write(excelOutFile);

            excelOutFile.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
