package org.moon.test.pdfbox;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

public class PrintPDFTest {
    
	private static final String PASSWORD     = "-password";
    private static final String SILENT       = "-silentPrint";
    private static final String PRINTER_NAME = "-printerName";
    
	public static void main(String[] args) throws Exception {
		String password = "";
		String pdfFile = null;
		boolean silentPrint = false;
		String printerName = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(PASSWORD)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				password = args[i];
			} else if (args[i].equals(PRINTER_NAME)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				printerName = args[i];
			} else if (args[i].equals(SILENT)) {
				silentPrint = true;
			} else {
				pdfFile = args[i];
			}
		}

		if (pdfFile == null) {
			usage();
		}

		PDDocument document = null;
		try {
			document = PDDocument.load(pdfFile);

			if (document.isEncrypted()) {
				document.decrypt(password);
			}

			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setJobName(new File(pdfFile).getName());

			if (printerName != null) {
				PrintService[] printService = PrinterJob.lookupPrintServices();
				boolean printerFound = false;
				for (int i = 0; !printerFound && i < printService.length; i++) {
					if (printService[i].getName().indexOf(printerName) != -1) {
						printJob.setPrintService(printService[i]);
						printerFound = true;
					}
				}
			}

			printJob.setPageable(new PDPageable(document, printJob));
			if (silentPrint || printJob.printDialog()) {
				printJob.print();
			}
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
	
    private static void usage()
    {
        System.err.println( "Usage: java -jar pdfbox-app-x.y.z.jar PrintPDF [OPTIONS] <PDF file>\n" +
            "  -password  <password>        Password to decrypt document\n" +
            "  -silentPrint                 Print without prompting for printer info\n"
            );
        System.exit( 1 );
    }
}
