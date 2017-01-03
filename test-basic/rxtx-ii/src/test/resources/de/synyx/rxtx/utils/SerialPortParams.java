package de.synyx.rxtx.utils;

/**
 *
 * @author Johannes Graf - graf@synyx.de
 */
public class SerialPortParams {

    private BaudRate baudRate;
    private DataBits dataBits;
    private StopBits stopBits;
    private Parity parity;

    /**
     * Constructs default configuration object with BaudRate.BAUD9600, DataBits.DATA8, StopBits.ONE, Parity.NONE;
     */
    public SerialPortParams() {
        this.baudRate = BaudRate.BAUD9600;
        this.dataBits = DataBits.DATA8;
        this.stopBits = StopBits.ONE;
        this.parity = Parity.NONE;
    }

    /**
     * Constructs a custom configuration object.
     *
     * @param baudRate
     * @param dataBits
     * @param stopBits
     * @param parity
     */
    public SerialPortParams(BaudRate baudRate, DataBits dataBits, StopBits stopBits, Parity parity) {
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }

    public int getParity() {
        return this.parity.getValue();
    }

    public int getBaudRate() {
        return this.baudRate.getValue();
    }

    public int getDataBits() {
        return this.dataBits.getValue();
    }

    public int getStopBits() {
        return this.stopBits.getValue();
    }

    /**
     * Enum representing possible values for the baud rate of a {@link gnu.io.SerialPort} connection.
     */
    public enum BaudRate {

        BAUD4800(4800), BAUD9600(9600), BAUD19200(19200), BAUD38400(38400), BAUD57600(57600), BAUD115200(115200), BAUD230400(230400);

        private int value;

        private BaudRate(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Enum representing possible values for the parity of a {@link gnu.io.SerialPort} connection.
     */
    public enum Parity {

        NONE(0), ODD(1), EVEN(2), MARK(3), SPACE(4);

        private int value;

        private Parity(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * Enum representing possible values for the stop bits of a {@link gnu.io.SerialPort} connection.
     */
    public enum StopBits {

        ONE(1), ONE_POINT_FIVE(3), TWO(2);

        private int value;

        private StopBits(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * Enum representing possible values for the data bits of a {@link gnu.io.SerialPort} connection.
     */
    public enum DataBits {

        DATA5(5), DATA6(6), DATA7(7), DATA8(8);

        private int value;

        private DataBits(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
