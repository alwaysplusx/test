package org.moon.ii.rxtx;

import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("unused")
public class SerialPortReader {

	private InputStream input;
	private SerialPortParams serialPortParams;

	public SerialPortReader(InputStream input, SerialPortParams serialPortParams) {
		this.input = input;
		this.serialPortParams = serialPortParams;
	}

	public String readLine() throws IOException {
		return null;
	}

	public int read() throws IOException {
		return -1;
	}

	public static void main(String[] args) {

	}

}
