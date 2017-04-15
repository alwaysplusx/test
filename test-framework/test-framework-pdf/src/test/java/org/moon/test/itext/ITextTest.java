package org.moon.test.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextTest {

    public static void main(String[] args) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("target/itext.pdf"));
        BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );  
        Font fontChinese = new Font(bfChinese); 
        doc.open();
        Paragraph paragraph = new Paragraph("Hello 我是中文",fontChinese);
        doc.add(paragraph);
        doc.close();
    }
    
    
    
    
}
