package org.moon.test.itext;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class ITextCreatePDFromTemplateTest {

    public static void main(String[] args) throws Exception {
        String tempFile = "src/test/resources/userTemplate-1.pdf";
        PdfReader reader = new PdfReader(tempFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamp = new PdfStamper(reader, baos);
        AcroFields form = stamp.getAcroFields();
        form.setField("name", "Test");
        form.setField("age", "26");
        stamp.setFormFlattening(true);
        stamp.close();
        
        //new FileOutputStream("target/user.pdf").write(baos.toByteArray());
        Document doc = new Document();
        PdfCopy pdfCopy = new PdfCopy(doc, new FileOutputStream("target/user.pdf"));
        doc.open();
        PdfImportedPage impPage = pdfCopy.getImportedPage(new PdfReader(baos.toByteArray()), 1);
        pdfCopy.addPage(impPage); 
        doc.close();
    }

}
