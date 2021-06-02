package com.lab4.demo.diploma;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DiplomaService {

    public void export(String teamName,String description) throws IOException {
        createFile(teamName,description);
    }
    public void createFile(String teamName,String description) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream pageContentStream = new PDPageContentStream(document, page);
        pageContentStream.beginText();
        pageContentStream.setFont( PDType1Font.TIMES_ROMAN , 35 );
        pageContentStream.setLeading(25.5f);
        pageContentStream.newLineAtOffset(100,  700);
        pageContentStream.showText("SLEDGE DOG CHAMPIONS");


        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();


        pageContentStream.setFont( PDType1Font.TIMES_ROMAN , 20 );
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("       The Certificate is Proudly Presented To");
        pageContentStream.showText(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        pageContentStream.newLine();
        pageContentStream.newLine();


        pageContentStream.setFont( PDType1Font.COURIER_OBLIQUE , 30);
        stringBuilder.append(teamName);
        pageContentStream.showText(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.newLine();
        pageContentStream.setFont( PDType1Font.TIMES_ROMAN , 20 );
        stringBuilder.append("                                  Team Roster");
        pageContentStream.showText(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        pageContentStream.newLine();
        pageContentStream.newLine();

        pageContentStream.setFont( PDType1Font.COURIER_OBLIQUE , 10);
        stringBuilder.append(description);
        pageContentStream.showText(stringBuilder.toString());
        pageContentStream.endText();
        pageContentStream.close();
        document.save("pdfFile.pdf");

    }
}
