package org.moon.test.pdfbox;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

public class Printer {

    private static boolean print(String PDFFile, PrintService printerService) throws Exception {
        if (PDFFile.toLowerCase().endsWith(".pdf")) {
            File file = new File(PDFFile);
            if (file.exists() && file.isFile()) {
                PDDocument document = null;
                try {
                    // 创建并返回一个打印任务
                    PrinterJob printJob = PrinterJob.getPrinterJob();
                    // 为打印任务设置打印机
                    printJob.setPrintService(printerService);
                    // 设置打印任务的名称
                    printJob.setJobName(file.getName());
                    // 使用PDFBox加载PDF文件
                    document = PDDocument.load(file);
                    // 创建一个pdf文件打印适配page
                    PDPageable page = new PDPageable(document, printJob);
                    // 设置需打印页，对打印文件的前期解析
                    printJob.setPageable(page);
                    // 打印
                    printJob.print();
                    return true;
                } finally {
                    if (document != null) {
                        document.close();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 指定打印机名称打印PDF文件
     * @param PDFFile PDF文件所在路径
     * @param printServiceName 打印机名称
     * @return true 成功插入打印队列, false 打印失败
     * @throws Exception 如果打印机未找到
     */
    public static boolean print(String PDFFile, String printServiceName) throws Exception {
        return print(PDFFile, getPrintService(printServiceName));
    }

    /**
     * 使用默认打印机打印PDF文件
     * @param PDFFile PDF文件路径
     * @return true 成功插入打印队列, false 打印失败
     * @throws Exception 未设置默认打印机
     */
    public static boolean print(String PDFFile) throws Exception {
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService == null) {
            throw new Exception("默认打印机器未设置");
        }
        return print(PDFFile, defaultPrintService);
    }

    private static PrintService getPrintService(String printServiceName) throws Exception {
        PrintService[] services = PrinterJob.lookupPrintServices();
        for (PrintService service : services) {
            if (service.getName().indexOf(printServiceName) != -1) {
                return service;
            }
        }
        throw new Exception("指定打印机" + printServiceName + "未找到");
    }
    
}
