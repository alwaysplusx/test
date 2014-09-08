package de.synyx.rxtx.utils;

/**
 *
 * @author Johannes Graf - graf@synyx.de
 */
public class UnsupportedPortException extends Exception {

    private static final long serialVersionUID = 7632412424874077390L;

    public UnsupportedPortException(PortType portType) {
        super("PortType " + portType + " is not supported!");
    }
}
