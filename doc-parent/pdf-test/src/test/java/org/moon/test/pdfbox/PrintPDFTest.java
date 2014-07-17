package org.moon.test.pdfbox;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

public class PrintPDFTest {

	public static void main(String[] args) throws Exception {
		PDDocument document = null;
		try {
			String filePath = "src/test/resources/print.pdf";
			//获取默认的打印机
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			//返回所有连接的打印机，通过打印机名称可以实现指定打印机打印
			//PrintService[] services = PrinterJob.lookupPrintServices();
			//创建并返回一个打印任务
			PrinterJob printJob = PrinterJob.getPrinterJob();
			//为打印任务设置打印机
			printJob.setPrintService(service);
			//设置打印任务的名称
			printJob.setJobName(new File(filePath).getName());
			//使用PDFBox加载PDF文件
			document = PDDocument.load(filePath);
			//创建一个pdf文件打印适配page
			PDPageable page = new PDPageable(document, printJob);
			//设置需打印页，对打印文件的前期解析
			printJob.setPageable(page);
			//打印
			printJob.print();
			System.out.println("print success");
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
}
