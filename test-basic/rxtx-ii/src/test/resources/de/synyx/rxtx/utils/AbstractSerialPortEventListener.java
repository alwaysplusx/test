package de.synyx.rxtx.utils;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class to handle all event types of a {@link gnu.io.SerialPortEvent}.
 *
 * @author Johannes Graf - graf@synyx.de
 */
public abstract class AbstractSerialPortEventListener implements SerialPortEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSerialPortEventListener.class);

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.BI} should be
     * handled.
     * 
     * @param serialPort
     */
    public void handleBreakInterrupt(SerialPort serialPort) {
        LOG.debug("Received event: break interrupt");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.CD} should be
     * handled.
     */
    public void handleCarrierDetected() {
        LOG.debug("Received event: Carrier detect.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.CTS} should be
     * handled.
     */
    public void handleClearToSend() {
        LOG.debug("Received event: Clear to send.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.DATA_AVAILABLE}
     * should be handled.
     */
    public void handleDataAvailable() {
        LOG.debug("Received event: Data available at the serial port");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.DSR} should be
     * handled.
     */
    public void handleDataSetReady() {
        LOG.debug("Received event: Data set ready.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.FE} should be
     * handled.
     */
    public void handleFramingError() {
        LOG.debug("Received event: Framing error.");
    }

    /**
     * Override this method when
     * {@link gnu.io.SerialPortEvent.OUTPUT_BUFFER_EMPTY} should be handled.
     */
    public void handleOutputBufferEmpty() {
        LOG.debug("Received event: Output buffer is empty.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.OE} should be
     * handled.
     */
    public void handleOverrunError() {
        LOG.debug("Received event: Overrun error.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.PE} should be
     * handled.
     */
    public void handleParityError() {
        LOG.debug("Received event: Parity error.");
    }

    /**
     * Override this method when {@link gnu.io.SerialPortEvent.RI} should be
     * handled.
     */
    public void handleRingIndicator() {
        LOG.debug("Received event: Ring indicator.");
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:
            handleBreakInterrupt((SerialPort) event.getSource());
            break;
        case SerialPortEvent.CD:
            handleCarrierDetected();
            break;
        case SerialPortEvent.CTS:
            handleClearToSend();
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            handleDataAvailable();
            break;
        case SerialPortEvent.DSR:
            handleDataSetReady();
            break;
        case SerialPortEvent.FE:
            handleFramingError();
            break;
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            handleOutputBufferEmpty();
            break;
        case SerialPortEvent.OE:
            handleOverrunError();
            break;
        case SerialPortEvent.PE:
            handleParityError();
            break;
        case SerialPortEvent.RI:
            handleRingIndicator();
            break;
        }
    }
}
