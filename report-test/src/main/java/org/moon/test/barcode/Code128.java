package org.moon.test.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Code128 {

	public static BufferedImage toBufferedImage(String message) {
		Code128Bean barcode = new Code128Bean();
		barcode.setFontSize(UnitConv.pt2mm(8));
		barcode.setBarHeight(10.0);
		barcode.setModuleWidth(0.27);
		barcode.doQuietZone(false);
		final int dpi = 441;
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(new ByteArrayOutputStream(), "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		barcode.generateBarcode(canvas, message);
		try {
			canvas.finish();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return canvas.getBufferedImage();
	}
	

}
