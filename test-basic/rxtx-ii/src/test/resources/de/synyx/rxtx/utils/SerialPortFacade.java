package de.synyx.rxtx.utils;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * A small facade to improve usage of a {@link gnu.io.SerialPort}
 *
 * @author Johannes Graf - graf@synyx.de
 */
public class SerialPortFacade {

    private static final Logger LOG = LoggerFactory.getLogger(SerialPortFacade.class);

    private String portName;
    private SerialPortParams serialPortParams;
    private SerialPort serialPort;

    /**
     * Constructs a new instance of SerialPortFacade via portName and with custom configuration.
     *
     * @param portName The name of the {@link gnu.io.SerialPort} (e.g. /dev/ttySerialPort01)
     * @param serialPortParams The configuration applied when calling SerialPortFacade.open().
     */
    public SerialPortFacade(String portName, SerialPortParams serialPortParams) {
        this.portName = portName;
        this.serialPortParams = serialPortParams;
    }

    /**
     * Constructs a new instance of SerialPortFacade via portName with default configuration.
     *
     * @param portName The name of the {@link gnu.io.SerialPort} (e.g. /dev/ttySerialPort01)
     */
    public SerialPortFacade(String portName) {
        this(portName, new SerialPortParams());
    }

    /**
     * Opens a connection to the given portName and configures the connection with {@link SerialPortParams}.
     *
     * @throws UnsupportedCommOperationException
     * @throws PortInUseException
     * @throws UnsupportedPortException
     * @throws NoSuchPortException
     */
    public void open() throws UnsupportedCommOperationException, PortInUseException, UnsupportedPortException, NoSuchPortException {
        LOG.info("opening connection to serialPort " + this.serialPort);

        CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);

        PortType currentPortType = PortType.fromValue(commPortIdentifier.getPortType());
        if (!PortType.SERIAL.equals(currentPortType)) {
            throw new UnsupportedPortException(currentPortType);
        }

        if (commPortIdentifier.isCurrentlyOwned()) {
            throw new PortInUseException();
        }

        this.serialPort = (SerialPort) commPortIdentifier.open(portName, 2000);
        applySerialPortParams();

        LOG.info("connection is established to serialPort " + this.serialPort.getName());
    }

    private void applySerialPortParams() throws UnsupportedCommOperationException {
        LOG.info("applying serialPort configuration:"
                + " baud rate: " + this.serialPortParams.getBaudRate()
                + " data bits: " + this.serialPortParams.getDataBits()
                + " stop bits: " + this.serialPortParams.getStopBits()
                + " parity: " + this.serialPortParams.getParity());

        this.serialPort.setSerialPortParams(
                this.serialPortParams.getBaudRate(),
                this.serialPortParams.getDataBits(),
                this.serialPortParams.getStopBits(),
                this.serialPortParams.getParity()
        );
    }

    /**
     * Closes the connection of the connected {@link gnu.io.SerialPort}.
     */
    public void close() {
        LOG.info("closing connection to serialPort " + this.serialPort.getName());
        this.serialPort.close();
        LOG.info("connection is closed to serialPort" + this.portName);
    }

    /**
     * Registers a {@link gnu.io.SerialPortEventListener} to the connected {@link gnu.io.SerialPort}
     * and enables listening for {@link gnu.io.SerialPortEvent.DATA_AVAILABLE}.
     *
     * @param serialPortEventListener A custom {@link gnu.io.SerialPortEventListener} implementation
     * @throws TooManyListenersException
     */
    public void registerEventlistener(SerialPortEventListener serialPortEventListener) throws TooManyListenersException {
        LOG.info("registering serialPortEventListener");
        this.serialPort.addEventListener(serialPortEventListener);
        LOG.info("enabling notify on data available");
        this.serialPort.notifyOnDataAvailable(true);
    }

    /**
     * Returns the connected {@link gnu.io.SerialPort} object behind the facade.
     *
     * @return  {@link SerialPort}
     */
    public SerialPort getSerialPort() {
        return this.serialPort;
    }

    /**
     * Returns the {@link InputStream} of the connected {@link gnu.io.SerialPort}.
     *
     * @return InputStream
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException {
        return this.serialPort.getInputStream();
    }

    /**
     * Returns the {@link OutputStream} of the connected {@link gnu.io.SerialPort}.
     *
     * @return OutputStream
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException {
        return this.serialPort.getOutputStream();
    }

    public String getPortName() {
        return this.portName;
    }
}
