package org.moon.test.report;

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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.moon.test.persistence.Material;

public class ImgBarcodeExportTest {
	
	private JRBeanArrayDataSource dataSource;
	private String template = "src/main/resources/resources/reports/imgMaterial.jasper";
	private Map<String, Object> map = new HashMap<String, Object>();
	private JasperPrint jasperPrint;
	
	@Before
	public void setUp() throws Exception{
		List<Material> list = new ArrayList<Material>();
		Material material1 = new Material("|+_)(*&^%", "测试零件0");
		material1.setFeedQuantity(new BigDecimal(41));
		material1.setStation("275");
		material1.setFeedDate(new Date());
		material1.sethBox("1箱+6");
		Material material2 = new Material("10AbcIOta01", "测试零件1");
		material2.setFeedQuantity(new BigDecimal(51));
		material2.setStation("510");
		material2.setFeedDate(new Date());
		material2.sethBox("2箱+10");
		Material material3 = new Material("W 01-00928A", "测试零件2");
		material3.setFeedQuantity(new BigDecimal(51));
		material3.setStation("201");
		material3.setFeedDate(new Date());
		material3.sethBox("2箱+8");
		Material material4 = new Material("$#@!~001/ABC,", "测试零件2");
		material4.setFeedQuantity(new BigDecimal(51));
		material4.setStation("201");
		material4.setFeedDate(new Date());
		material4.sethBox("2箱+8");
		list.add(material1);
		list.add(material2);
		list.add(material3);
		list.add(material4);
		dataSource = new JRBeanArrayDataSource(list.toArray());
		jasperPrint = JasperFillManager.fillReport(template, map, dataSource);
	}

	@Test
	public void imgMaterialExportTest() throws Exception{
		JasperExportManager.exportReportToPdfFile(jasperPrint, "target/imgMaterial.pdf");
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("target/imgMaterial.xls"));
		exporter.exportReport();
	}
	
	@Test
	@Ignore
	public void exportImgBracode() throws Exception{
		map.put("code", "ABC123");
		JasperExportManager.exportReportToPdfFile(jasperPrint, "target/imgBarcode.pdf");
	}	
}
