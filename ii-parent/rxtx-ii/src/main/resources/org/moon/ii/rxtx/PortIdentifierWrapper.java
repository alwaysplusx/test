package org.moon.ii.rxtx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;

class PortIdentifierWrapper {

    private Logger log = LoggerFactory.getLogger(PortIdentifierWrapper.class);
    private CommPortIdentifier portIdentifier;

    public PortIdentifierWrapper(CommPortIdentifier portIdentifier) {
        this.portIdentifier = portIdentifier;
    }

    public boolean available() {
        try {
            CommPortIdentifier.getPortIdentifier(portIdentifier.getName());
        } catch (NoSuchPortException e) {
            log.info("no such port exception {}", e);
            return false;
        }
        return true;
    }

    public CommPortIdentifier getIdentifier() {
        return this.portIdentifier;
    }

}
