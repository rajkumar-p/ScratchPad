package com.diskodev;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFDetailsExtractor {
    private String pdfText = "";

    PDFDetailsExtractor() {
        try {
            PDDocument document = PDDocument.load(new File("/Users/rajkumar.p/Projects/ScratchPad/OlaBillParse/res/CRN1777598014.pdf"));
            pdfText = new PDFTextStripper().getText(document);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPdfText() {
        return pdfText;
    }
}
