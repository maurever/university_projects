package fit.maurever.utils;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fit.maurever.layout.MainFrame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * PDF Generator for generate output file in PDF.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class PdfGenerator {

    public static void createPdf(String fileName, String[][] regressionInformation, Double[][] predictedValues, String graphName) {
        int chapterCounter = 1;
        try {
            OutputStream file = new FileOutputStream(new File(fileName));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.addAuthor("PredCrime");
            document.addCreationDate();
            document.addCreator("iText library");
            document.addTitle("PredCrime predictive analysis - regression model");

            BaseFont bf = BaseFont.createFont();
            
            Font h1 = new Font(bf, 20);
            h1.setStyle("bold");

            Font chapterFont = new Font(bf, 14);
            chapterFont.setStyle("bold");

            Font paragrafFontBold = new Font(bf, 11);
            paragrafFontBold.setStyle("bold");

            Font paragrafFont = new Font(bf, 11);

            Paragraph p = new Paragraph("Predictive analysis - regression model", h1);
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);
            if (regressionInformation != null) {
                Paragraph title = new Paragraph("Regression Information", chapterFont);
                title.setSpacingBefore(20);
                title.setSpacingAfter(10);
                Chapter chapter = new Chapter(title, chapterCounter++);
                chapter.setTriggerNewPage(false);

                for (String[] information : regressionInformation) {
                    Paragraph paragraph = new Paragraph();
                    paragraph.add(new Chunk(information[0], paragrafFontBold));
                    paragraph.add(new Chunk(": " + information[1], paragrafFont));
                    chapter.add(paragraph);
                }
                document.add(chapter);
            }
            if (predictedValues != null) {
                Paragraph title = new Paragraph("Predicted values", chapterFont);
                title.setSpacingBefore(20);
                title.setSpacingAfter(20);
                Chapter chapter = new Chapter(title, chapterCounter++);
                chapter.setTriggerNewPage(false);
                Paragraph paragraphTable = new Paragraph();
                PdfPTable table = new PdfPTable(2);
                for (Double[] value : predictedValues) {
                    table.addCell(value[0] + "");
                    table.addCell(value[1] + "");
                }
                paragraphTable.add(table);
                chapter.add(paragraphTable);
                document.add(chapter);

            }
            if (graphName != null) {
                Paragraph title = new Paragraph("Graph", chapterFont);
                title.setSpacingBefore(20);
                title.setSpacingAfter(10);
                Chapter chapter = new Chapter(title, chapterCounter++);
                chapter.setTriggerNewPage(false);
                document.add(chapter);
                Image img = Image.getInstance(String.format(graphName));
                img.scaleToFit(400, 200);
                img.setAlignment(Element.ALIGN_CENTER);
                document.add(img);
            }
            document.close();
            file.close();
            MainFrame.statusbar.setText("PDF was created with name '" + fileName + "'.");
        } catch (Exception e) {
            e.printStackTrace();
            MainFrame.statusbar.setText("PDF create error.");
        }
    }

}
