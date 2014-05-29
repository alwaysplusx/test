package org.moon.test.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.junit.Ignore;
import org.junit.Test;
import org.moon.test.persistence.Material;
import org.moon.test.persistence.User;

public class JasperReportExportTest {

	@Test
	@Ignore
	public void demoExportTest() throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Detail", "ABC");
		JasperPrint print = JasperFillManager.fillReport("src/main/resources/resources/reports/report.jasper", map, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(print, "target/report.pdf");
	}
	
	@Test
	@Ignore
	public void barcodeExportTest() throws Exception{
		User user = new User(100000l,"USER1","F");
		user.setBirthday(new Date());
		user.setCountry("China");
		user.setRemark("Good Guy");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(list.toArray());
		JasperPrint print = JasperFillManager.fillReport("src/main/resources/resources/reports/barcode.jasper", new HashMap<String, Object>(), dataSource);
		JasperExportManager.exportReportToPdfFile(print, "target/barcode.pdf");
	}
	
	@Test
	@Ignore
	public void materialExportAsXLSTest() throws Exception{
		List<Material> list = new ArrayList<Material>();
		Material material1 = new Material("1002350TARC1", "正时皮带张紧轮合件");
		material1.setFeedQuantity(new BigDecimal(41));
		material1.setStation("275");
		material1.setFeedDate(new Date());
		material1.sethBox("1箱+6");
		Material material2 = new Material("100239839TABC1", "测试零件1");
		material2.setFeedQuantity(new BigDecimal(51));
		material2.setStation("510");
		material2.setFeedDate(new Date());
		material2.sethBox("2箱+10");
		list.add(material1);
		list.add(material2);
		JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(list.toArray());
		JasperPrint print = JasperFillManager.fillReport("src/main/resources/resources/reports/material.jasper", new HashMap<String, Object>(), dataSource);
		JRXlsExporter export = new JRXlsExporter();
		export.setExporterInput(new SimpleExporterInput(print));
		export.setExporterOutput(new SimpleOutputStreamExporterOutput("target/material.xls"));
	}
	
	@Test
	@Ignore
	public void materialExportAsPDFTest() throws Exception{
		List<Material> list = new ArrayList<Material>();
		Material material1 = new Material("1002350TARC1", "正时皮带张紧轮合件");
		material1.setFeedQuantity(new BigDecimal(41));
		material1.setStation("275");
		material1.setFeedDate(new Date());
		material1.sethBox("1箱+6");
		Material material2 = new Material("100239839TABC1", "测试零件1");
		material2.setFeedQuantity(new BigDecimal(51));
		material2.setStation("510");
		material2.setFeedDate(new Date());
		material2.sethBox("2箱+10");
		list.add(material1);
		list.add(material2);
		JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(list.toArray());
		JasperPrint print = JasperFillManager.fillReport("src/main/resources/resources/reports/material.jasper", new HashMap<String, Object>(), dataSource);
		JasperExportManager.exportReportToPdfFile(print, "target/material.pdf");
	}
	
}
