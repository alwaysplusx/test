package org.moon.test.itext;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFTemplateInfoTest {

    public static void main(String[] args) throws Exception {
        String templateFile = "src/test/resources/PdfTemplate.pdf";
        PdfReader reader = new PdfReader(templateFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamp = new PdfStamper(reader, baos);
        AcroFields form = stamp.getAcroFields();
        Map<String, Item> fields = form.getFields();
        Iterator<String> iterator = fields.keySet().iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    
}
