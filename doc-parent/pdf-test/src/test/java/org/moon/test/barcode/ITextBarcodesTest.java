package org.moon.test.barcode;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextBarcodesTest {

	private static final String DEST_FILE = "target/barcodes.pdf";

	public static void main(String[] args) throws Exception {
		new ITextBarcodesTest().createPdf(DEST_FILE);
	}

	public void createPdf(String filename) throws Exception {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		Barcode128 barcode = new Barcode128();
		barcode.setCode("123456");
		document.add(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLUE, BaseColor.RED));
		document.close();
	}
}