package org.moon.test.report;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Code128Test {

	public static void main(String[] args) throws Exception {
		Code128Bean barcode = new Code128Bean();
		final int dpi = 150;
		//barcode.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
		//barcode.doQuietZone(false);
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(new FileOutputStream("target/barcode.png"), "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		barcode.generateBarcode(canvas, "abc123");
		canvas.finish();
	}

}
