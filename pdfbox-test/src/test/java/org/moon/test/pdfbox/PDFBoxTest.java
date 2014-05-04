package org.moon.test.pdfbox;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.pdfbox.TextToPDF;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFBoxTest {

	public static void main(String[] args) throws Exception {
		TextToPDF ttp = new TextToPDF();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/test.txt")));
		PDDocument pdDocument = ttp.createPDFFromText(br);
		pdDocument.save("target/test.pdf");
	}

}
