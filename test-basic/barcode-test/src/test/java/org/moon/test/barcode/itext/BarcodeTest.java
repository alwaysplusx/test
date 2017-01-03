package org.moon.test.barcode.itext;

import java.awt.Color;
import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BarcodeCodabar;
import com.lowagie.text.pdf.BarcodeDatamatrix;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class BarcodeTest {

    static BaseFont bf;
    static Document doc;
    static PdfWriter writer;
    static String outputFile = "target/barcodes.pdf";
    static PdfContentByte content;

    @BeforeClass
    public static void setUp() throws Exception {
        bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        doc = new Document();
        writer = PdfWriter.getInstance(doc, new FileOutputStream(outputFile));
        doc.open();
        content = writer.getDirectContent();
        Paragraph title = new Paragraph("iText Generate Barcode - 使用iText生成条码", new Font(bf, 28));
        title.setAlignment(ElementTags.ALIGN_CENTER);
        doc.add(title);
    }

    @Test
    public void genBarcode128() throws Exception {
        doc.add(new Paragraph("Default Barcode128"));
        Barcode128 b = new Barcode128();
        b.setCode("W201407171703");
        doc.add(b.createImageWithBarcode(content, null, null));
        b.setBaseline(-1f);
        doc.add(new Paragraph("Barcode128 文字在上", new Font(bf)));
        doc.add(b.createImageWithBarcode(content, Color.BLUE, Color.RED));
        b.setBaseline(b.getSize());
        doc.add(new Paragraph("Barcode code 0123456789\uffff(0 - 9)"));
        b.setCode("0123456789\uffff(0 - 9)");
        b.setCodeType(Barcode.CODE128_RAW);
        doc.add(b.createImageWithBarcode(content, null, null));
    }

    @Test
    public void genBarcodeEAN() throws Exception {
        BarcodeEAN b = new BarcodeEAN();
        doc.add(new Paragraph("BarcodeEAN"));
        b.setCode("6584545678906");
        doc.add(b.createImageWithBarcode(content, null, null));
    }

    @Test
    public void codaBar() throws Exception {
        doc.add(new Paragraph("Codabar"));
        BarcodeCodabar codabar = new BarcodeCodabar();
        codabar.setCode("D5945D");
        codabar.setStartStopText(true);
        doc.add(codabar.createImageWithBarcode(content, null, null));
    }

    @Test
    public void datamatrixBarcode() throws Exception {
        doc.add(new Paragraph("Barcode Datamatrix"));
        BarcodeDatamatrix datamatrix = new BarcodeDatamatrix();
        datamatrix.generate("This is simple text message!还加入了中文。");
        doc.add(datamatrix.createImage());
    }

    @Ignore
    @Test
    public void genQRCode() {
        // BarcodeQRCode b;
    }

    @AfterClass
    public static void tearDown() {
        doc.close();
    }

}
