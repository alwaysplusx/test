package org.harmony.test.rxtx;

import gnu.io.SerialPortEvent;

public abstract class SerialPortEventListener implements gnu.io.SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:
            handleBreakInterrupt(event);
            break;
        case SerialPortEvent.CD:
            handleCarrierDetected(event);
            break;
        case SerialPortEvent.CTS:
            handleClearToSend(event);
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            handleDataAvailable(event);
            break;
        case SerialPortEvent.DSR:
            handleDataSetReady(event);
            break;
        case SerialPortEvent.FE:
            handleFramingError(event);
            break;
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            handleOutputBufferEmpty(event);
            break;
        case SerialPortEvent.OE:
            handleOverrunError(event);
            break;
        case SerialPortEvent.PE:
            handleParityError(event);
            break;
        case SerialPortEvent.RI:
            handleRingIndicator(event);
            break;
        }
    }

    /**
     * {@link gnu.io.SerialPortEvent#notifyOnBreakInterrupt(boolean)}
     * 
     * @param source
     */
    public void handleRingIndicator(SerialPortEvent source) {

    }

    /**
     * set {@link gnu.io.SerialPortEvent#notifyOnParityError(boolean)} true
     * 
     * @param source
     */
    public void handleParityError(SerialPortEvent source) {

    }

    public void handleOverrunError(SerialPortEvent source) {

    }

    public void handleOutputBufferEmpty(SerialPortEvent source) {

    }

    public void handleFramingError(SerialPortEvent source) {

    }

    public void handleDataSetReady(SerialPortEvent source) {

    }

    public void handleDataAvailable(SerialPortEvent source) {

    }

    public void handleClearToSend(SerialPortEvent source) {

    }

    public void handleCarrierDetected(SerialPortEvent source) {

    }

    public void handleBreakInterrupt(SerialPortEvent source) {

    }
}
