package org.moon.ii.rxtx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

public class SerialPortFacadeTest {

	public static void main(String[] args) throws NoSuchPortException, IOException, PortInUseException {
		SerialPortFacade serialPortFacade1 = new SerialPortFacade("COM1");
		SerialPortFacade serialPortFacade2 = new SerialPortFacade("COM2");
		serialPortFacade1.open(SerialPortFacadeTest.class.getName(), 50);
		serialPortFacade2.open(SerialPortFacadeTest.class.getName(), 50);
		OutputStream output = serialPortFacade2.getOutputStream();
		InputStream input = serialPortFacade1.getInputStream();
		output.write("abcdef".getBytes());
		output.write("hello!".getBytes());
		byte[] buffer = new byte[6];
		input.read(buffer);
		System.out.println("COM2 receive data:" + new String(buffer));
		input.read(buffer);
		System.out.println("COM2 receive data:" + new String(buffer));
	}

}
