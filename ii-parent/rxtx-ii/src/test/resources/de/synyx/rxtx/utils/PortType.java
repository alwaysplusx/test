package de.synyx.rxtx.utils;

/**
 *
 * @author Johannes Graf - graf@synyx.de
 */
public enum PortType {

    SERIAL(1), PARALLEL(2), I2C(3), RS485(4), RAW(5), UNSUPPORTED(6);

    private int type;

    private PortType(int type) {
        this.type = type;
    }

    public static PortType fromValue(int type) {
        switch (type) {
            case 1:
                return SERIAL;
            case 2:
                return PARALLEL;
            case 3:
                return I2C;
            case 4:
                return RS485;
            case 5:
                return RAW;
            default:
                return UNSUPPORTED;
        }
    }

    public int getValue() {
        return type;
    }
}
