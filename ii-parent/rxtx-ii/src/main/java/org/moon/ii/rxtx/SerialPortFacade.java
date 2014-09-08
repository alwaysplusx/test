package org.moon.ii.rxtx;

import gnu.io.CommPortIdentifier;
import gnu.io.CommPortOwnershipListener;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortFacade {

	private static final Logger LOG = LoggerFactory.getLogger(SerialPortFacade.class);

	private String portName;
	private CommPortIdentifier serialPortIdentifier;
	private SerialPort serialPort;
	private boolean opened = false;
	private SerialPortManager serialPortManager = SerialPortManager.getInstance();

	public SerialPortFacade(String portName) throws NoSuchPortException {
		this.portName = portName;
		this.serialPortIdentifier = serialPortManager.getSerialPortIdentifier(portName);
		if (serialPortIdentifier == null) {
			throw new NoSuchPortException();
		}
		addPortOwnershipListener(this.new SerialPortOwershipListener());
	}

	protected void addPortOwnershipListener(CommPortOwnershipListener listener) {
		serialPortIdentifier.addPortOwnershipListener(listener);
	}

	protected void removePortOwnershipListener(CommPortOwnershipListener listener) {
		serialPortIdentifier.removePortOwnershipListener(listener);
	}

	public void open(String owenName, int timeLimit) throws PortInUseException, NoSuchPortException {
		if (opened) {
			LOG.info("serial port {} already open", portName);
			return;
		}
		LOG.info("opening connection to serialPort {}", portName);
		try {
			this.serialPort = (SerialPort) serialPortIdentifier.open(owenName, timeLimit);
			opened = true;
			LOG.info("connection is established to serialPort " + this.serialPort.getName());
		} catch (PortInUseException e) {
			LOG.error("can't open serial port {}, the serial already open by other application.\n {}", portName, e);
			throw e;
		}
	}

	public void applySerialPortParams(SerialPortParams serialPortParams) throws UnsupportedCommOperationException {
		checkSerialPortOpen();
		LOG.info("applying serialPort configuration:" + " baud rate: " + serialPortParams.getBaudRate() + " data bits: " + serialPortParams.getDataBits()
				+ " stop bits: " + serialPortParams.getStopBits() + " parity: " + serialPortParams.getParity());
		this.serialPort.setSerialPortParams(serialPortParams.getBaudRate(), serialPortParams.getDataBits(), serialPortParams.getStopBits(),
				serialPortParams.getParity());
	}

	public void close() {
		if (opened) {
			this.serialPort.close();
			this.serialPort = null;
			opened = false;
			LOG.info("connection is closed to serialPort" + this.portName);
		}
	}

	public void registerEventlistener(SerialPortEventListener listener, SerialPortEventType... enableListenerType) {
		checkSerialPortOpen();
		LOG.info("registering serialPortEventListener {}", listener);
		enableNotifyListener(enableListenerType);
	}

	private void enableNotifyListener(SerialPortEventType... types) {
		for (SerialPortEventType type : types) {
			enableOrDisableNotifyListener(type.getValue(), true);
		}
	}

	private void enableOrDisableNotifyListener(int type, boolean able) {
		switch (type) {
		case SerialPortEvent.BI:
			this.serialPort.notifyOnBreakInterrupt(able);
			break;
		case SerialPortEvent.CD:
			this.serialPort.notifyOnCarrierDetect(able);
			break;
		case SerialPortEvent.CTS:
			this.serialPort.notifyOnCTS(able);
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			this.serialPort.notifyOnDataAvailable(able);
			break;
		case SerialPortEvent.DSR:
			this.serialPort.notifyOnDSR(able);
			break;
		case SerialPortEvent.FE:
			this.serialPort.notifyOnFramingError(able);
			break;
		case SerialPortEvent.OE:
			this.serialPort.notifyOnOverrunError(able);
			break;
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			this.serialPort.notifyOnOutputEmpty(able);
			break;
		case SerialPortEvent.PE:
			this.serialPort.notifyOnParityError(able);
			break;
		case SerialPortEvent.RI:
			this.serialPort.notifyOnRingIndicator(able);
			break;
		}
	}

	public CommPortIdentifier getPortIdentifier() {
		return this.serialPortIdentifier;
	}

	public SerialPort getSerialPort() {
		checkSerialPortOpen();
		return this.serialPort;
	}

	public InputStream getInputStream() throws IOException {
		checkSerialPortOpen();
		return this.serialPort.getInputStream();
	}

	public OutputStream getOutputStream() throws IOException {
		checkSerialPortOpen();
		return this.serialPort.getOutputStream();
	}

	public String getPortName() {
		return this.portName;
	}

	private void checkSerialPortOpen() {
		if (serialPort == null || !opened) {
			throw new IllegalStateException("serial port " + this.portName + " not open or closed");
		}
	}

	class SerialPortOwershipListener implements CommPortOwnershipListener {
		@Override
		public void ownershipChange(int type) {
			switch (type) {
			case CommPortOwnershipListener.PORT_OWNED:
				LOG.debug("serial owen by one application");
				break;
			case CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED:
				LOG.debug("other application requested this serial port");
				break;
			case CommPortOwnershipListener.PORT_UNOWNED:
				LOG.info("serial port is not owned by an application and is ready to be opened");
				break;
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.close();
	}

}
