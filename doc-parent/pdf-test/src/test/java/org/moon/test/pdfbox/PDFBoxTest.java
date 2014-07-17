package org.moon.test.pdfbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.pdfbox.TextToPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

public class PDFBoxTest {

	public static void main(String[] args) throws Exception {
		TextToPDF ttp = new TextToPDF();
		File file = new File("src/test/resources/text.txt");
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
		PDDocument pdDocument = ttp.createPDFFromText(isr);
		pdDocument.save("target/text.pdf");
	}

	@Test
	public void testCreateWithChinese() throws Exception {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDFont font = PDType1Font.ZAPF_DINGBATS;
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		contentStream.setFont(font, 12);
		contentStream.moveTextPositionByAmount(100, 700);
		contentStream.drawString("Hello World, 我是中文");
		contentStream.endText();
		contentStream.close();
		document.save("target/Hello World.pdf");
		document.close();
	}
	
}
