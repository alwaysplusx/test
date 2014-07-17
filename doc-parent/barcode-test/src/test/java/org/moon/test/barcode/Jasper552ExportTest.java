package org.moon.test.barcode;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;

import org.junit.Before;
import org.junit.Test;
import org.moon.test.persistence.Material;

public class Jasper552ExportTest {

	protected JRBeanArrayDataSource dataSource;
	protected String imgTemplate = "src/main/resources/imageBarcode.jasper";
	protected String simpleTemplate = "src/main/resources/simpleBarcode.jasper";
	protected String imgOutputXLS = "target/552imageBarcodeXLS.xls";
	protected String imgOutputPDF = "target/552imageBarcodePDF.pdf";
	protected String simpleOutputXLS = "target/552SimpleBarcodeXLS.xls";
	protected String simpleOutputPDF = "target/552SimpleBarcodePDF.pdf";
	protected Map<String, Object> map = new HashMap<String, Object>();
	protected JasperPrint jasperPrint;
	protected SimpleXlsReportConfiguration reportConfiguration;

	@Before
	public void setUp() throws Exception {
		List<Material> list = new ArrayList<Material>();
		Material material1 = new Material("W1407170000", "测试零件0");
		material1.setFeedQuantity(new BigDecimal(41));
		material1.setStation("275");
		material1.setFeedDate(new Date());
		material1.sethBox("1箱+6");
		Material material2 = new Material("W1407170001", "测试零件1");
		material2.setFeedQuantity(new BigDecimal(11));
		material2.setStation("510");
		material2.setFeedDate(new Date());
		material2.sethBox("2箱+10");
		Material material3 = new Material("W1407170002", "测试零件2");
		material3.setFeedQuantity(new BigDecimal(23));
		material3.setStation("211");
		material3.setFeedDate(new Date());
		material3.sethBox("2箱+8");
		Material material4 = new Material("W1407170003", "测试零件3");
		material4.setFeedQuantity(new BigDecimal(85));
		material4.setStation("201");
		material4.setFeedDate(new Date());
		material4.sethBox("2箱+6");
		list.add(material1);
		list.add(material2);
		list.add(material3);
		list.add(material4);
		dataSource = new JRBeanArrayDataSource(list.toArray());
	}

	@Test
	public void imgBarcodeExportTest() throws Exception {
		jasperPrint = JasperFillManager.fillReport(imgTemplate, map, dataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(imgOutputXLS)));
		exporter.setConfiguration(reportConfiguration);
		exporter.exportReport();
		JasperExportManager.exportReportToPdfFile(jasperPrint, imgOutputPDF);
	}

	public XlsReportConfiguration getDefualtReportConfiguration() {
		if (reportConfiguration == null) {
			reportConfiguration = new SimpleXlsReportConfiguration();
			reportConfiguration.setCellHidden(true);
			reportConfiguration.setCellLocked(true);
			reportConfiguration.setOnePagePerSheet(false);
			reportConfiguration.setWhitePageBackground(false);
			reportConfiguration.setFontSizeFixEnabled(true);
		}
		return reportConfiguration;
	}

	@Test
	public void simpleBarcodeExportTest() throws Exception {
		jasperPrint = JasperFillManager.fillReport(simpleTemplate, map, dataSource);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(simpleOutputXLS)));
		exporter.exportReport();
		JasperExportManager.exportReportToPdfFile(jasperPrint, simpleOutputPDF);
	}
}
