package org.moon.test.report;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Code128Test {

	public static void main(String[] args) throws Exception {
		Code128Bean barcode = new Code128Bean();
		barcode.setBarHeight(5);
		barcode.setFontSize(1.0);
		final int dpi = 441;
		//barcode.doQuietZone(false);
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(new FileOutputStream("target/barcode.png"), "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		barcode.generateBarcode(canvas, "+_)(*&^%$#@!~");
		canvas.finish();
	}
	
}