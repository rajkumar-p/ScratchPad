package com.diskodev;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
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
        try {
            PDDocument doc = PDDocument.load(new File("/Users/rajkumar.p/Projects/ScratchPad/OlaBillParse/res/CRN1777598014.pdf"));
            String text = new PDFTextStripper().getText(doc);

            // Print the text in PDF
            System.out.println("Text in PDF");
            System.out.println("-----------");
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
