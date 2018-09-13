package com.diskodev;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFTextExtractor {
    public static String getPDFTextFrom(String pdfFile) {
        PDDocument document = null;
        String pdfText = null;
        try {
            document = PDDocument.load(new File(pdfFile));
            pdfText = new PDFTextStripper().getText(document);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pdfText;
    }
}
