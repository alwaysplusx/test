package org.moon.test.barcode;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.junit.Before;
import org.junit.Test;
import org.moon.test.persistence.Material;

@SuppressWarnings("deprecation")
public class SimpleBarcodeExportTest {

	protected JRBeanArrayDataSource dataSource;
	protected String template = "src/main/resources/simpleBarcode.jasper";
	protected String outputXLS = "target/simpleBarcodeXls.xls";
	protected String outputPDF = "target/simpleBarcodePDF.pdf";
	protected Map<String, Object> map = new HashMap<String, Object>();
	protected JasperPrint jasperPrint;

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
		jasperPrint = JasperFillManager.fillReport(template, map, dataSource);
	}

	@Test
	public void simpleBarcodeXLSTest() throws Exception {
		JExcelApiExporter exporter = new JExcelApiExporter();
		exporter.setParameter(JRExporterParameter.INPUT_FILE, new File(template));
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(outputXLS));
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);		
		exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
		exporter.exportReport();
		JasperExportManager.exportReportToPdfFile(jasperPrint, outputPDF);
	}

}
