package org.moon.test.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Code128 {

    public final static int DEFAULT_DPI = 441;
    public final static String DEFAULT_MIME_TYPE = "image/jpeg";

    public static BufferedImage toBufferedImage(String code) throws IOException {
        Code128Bean barcode = new Code128Bean();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(bos, DEFAULT_MIME_TYPE, DEFAULT_DPI, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        barcode.setHeight(10);
        barcode.setFontSize(UnitConv.pt2mm(5));
        barcode.generateBarcode(canvas, code);
        canvas.finish();
        return canvas.getBufferedImage();
    }

    public static byte[] toByteArray(String code) throws IOException {
        return ((ByteArrayOutputStream)toOutputStream(code)).toByteArray();
    }

    public static OutputStream toOutputStream(String code) throws IOException {
        Code128Bean barcode = new Code128Bean();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(bos, DEFAULT_MIME_TYPE, DEFAULT_DPI, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        barcode.generateBarcode(canvas, code);
        canvas.finish();
        return bos;
    }

}
