package org.moon.ii.rxtx;

/**
 * @see gnu.io.SerialPortEvent
 * @author wuxii
 *
 */
public enum SerialPortEventType {

    DATA_AVAILABLE(1), 
    OUTPUT_BUFFER_EMPTY(2), 
    CLEAR_TO_SEND(3), 
    DATA_SET_READY(4), 
    RING_INDICATOR(5), 
    CARRIER_DETECT(6), 
    OVERRUN_ERROR(7), 
    PARITY_ERROR(8), 
    FRAMING_ERROR(    9), 
    BREAK_INTERRUPT(10);

    private int type;

    private SerialPortEventType(int type) {
        this.type = type;
    }

    public static SerialPortEventType fromValue(int type) {
        switch (type) {
        case 1:
            return DATA_AVAILABLE;
        case 2:
            return OUTPUT_BUFFER_EMPTY;
        case 3:
            return CLEAR_TO_SEND;
        case 4:
            return DATA_SET_READY;
        case 5:
            return RING_INDICATOR;
        case 6:
            return CARRIER_DETECT;
        case 7:
            return OVERRUN_ERROR;
        case 8:
            return PARITY_ERROR;
        case 9:
            return FRAMING_ERROR;
        case 10:
            return BREAK_INTERRUPT;
        default:
            throw new IllegalArgumentException();
        }
    }

    public int getValue(){
        return this.type;
    }
    
}