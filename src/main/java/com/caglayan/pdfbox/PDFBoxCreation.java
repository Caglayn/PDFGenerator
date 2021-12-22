package com.caglayan.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PDFBoxCreation {

    public static final String EXAMPLE_PDF = "C:\\OMDB\\example.pdf";

    public static void main(String[] args) {
//        createPDF();
        createPDFWithImage();
        readPDF();
    }

    private static void createPDFWithImage() {
        PDPageContentStream content = null;
        try (PDDocument pdf = new PDDocument()) {
            PDPage page = new PDPage();
            pdf.addPage(page);
            content = new PDPageContentStream(pdf, page);

            String imageFileName = "C:\\OMDB\\m.png";
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFileName, pdf);
            content.drawImage(pdImage, 20f, 20f);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.setLeading(14.5f);
            content.newLineAtOffset(25,700);
            String line = "Birgun okula giderken";
            content.showText(line);
            content.newLine();
            line = "Birgun okula giderken222222";
            content.showText(line);
            content.endText();
            content.close();
            pdf.save(PDFBoxCreation.EXAMPLE_PDF);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void readPDF() {
        File pdfFile = new File(EXAMPLE_PDF);
        try (PDDocument pdf = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdf);
            System.out.println(text);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    private static void createPDF() {
        PDPageContentStream content = null;
        try (PDDocument pdf = new PDDocument()) {
            PDPage page = new PDPage();
            pdf.addPage(page);
            content = new PDPageContentStream(pdf, page);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.setLeading(14.5f);
            content.newLineAtOffset(25,700);
            String line = "Birgun okula giderken";
            content.showText(line);
            content.endText();
            content.close();
            pdf.save(PDFBoxCreation.EXAMPLE_PDF);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
