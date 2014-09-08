package org.moon.ii.rxtx;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortTest {

	static Logger log = LoggerFactory.getLogger(SerialPortTest.class);

	@Test
	public void test() throws Exception {
		CommPortIdentifier portId1 = CommPortIdentifier.getPortIdentifier("COM1");
		CommPortIdentifier portId2 = CommPortIdentifier.getPortIdentifier("COM2");
		CommPort com1 = portId1.open("wuxii", 50);
		CommPort com2 = portId2.open("wuxii", 50);
		System.out.println("portId1 == portId2 ? " + (portId1 == portId2));
		System.out.println("com1 == com2 ? " + (com1 == com2));
		System.out.println("portId1 equals portId2 ? " + (portId1.equals(portId2)));
		System.out.println("com1 equals com2 ? " + (com1.equals(com2)));
	}

}
