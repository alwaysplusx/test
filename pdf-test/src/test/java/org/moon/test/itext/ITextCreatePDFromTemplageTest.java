package org.moon.test.itext;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class ITextCreatePDFromTemplageTest {

	public static void main(String[] args) throws Exception {
		String tempFile = "src/test/resources/userTemplage.pdf";
		String destFile = "target/user_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
		FileOutputStream fos = new FileOutputStream(destFile);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfReader reader = new PdfReader(tempFile);
		PdfStamper stamper = new PdfStamper(reader, baos);
		AcroFields form = stamper.getAcroFields();
		form.setField("name", "Test");
		form.setField("age", "26");
		stamper.setFormFlattening(true);
		stamper.close();
		System.out.println(tempFile);
		System.out.println(destFile);
		Document doc = new Document();
		doc.open();
		PdfCopy pdfCopy = new PdfCopy(doc, fos);
		PdfImportedPage impPage = null;
		impPage = pdfCopy.getImportedPage(new PdfReader(baos.toByteArray()), 1);
		pdfCopy.addPage(impPage);
		doc.close();
	}

}
