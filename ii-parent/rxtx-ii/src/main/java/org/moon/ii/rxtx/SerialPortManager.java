package org.moon.ii.rxtx;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class SerialPortManager {

	private Map<String, PortIdentifierWrapper> wrapperMap = new HashMap<String, PortIdentifierWrapper>();

	private SerialPortManager() {
		reflush();
	}

	@SuppressWarnings("unchecked")
	public synchronized void reflush() {
		removeUnavailableSerialPort();
		Enumeration<CommPortIdentifier> identifiers = CommPortIdentifier.getPortIdentifiers();
		while (identifiers.hasMoreElements()) {
			CommPortIdentifier cpi = identifiers.nextElement();
			if (PortType.SERIAL.getValue() == cpi.getPortType() && !wrapperMap.containsKey(cpi.getName())) {
				wrapperMap.put(cpi.getName(), new PortIdentifierWrapper(cpi));
			}
		}
	}

	private void removeUnavailableSerialPort() {
		List<String> markRemove = new ArrayList<String>();
		for (Iterator<String> it = wrapperMap.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			if (wrapperMap.get(key).available()) {
				markRemove.add(key);
			}
		}
		for (String key : markRemove) {
			wrapperMap.remove(key);
		}
	}

	public CommPortIdentifier getSerialPortIdentifier(String portName) {
		if (wrapperMap.containsKey(portName)) {
			PortIdentifierWrapper wrapper = wrapperMap.get(portName);
			if (wrapper.available()) {
				return wrapper.getIdentifier();
			}
		}
		reflush();
		if (wrapperMap.containsKey(portName)) {
			return wrapperMap.get(portName).getIdentifier();
		}
		return null;
	}

	public static SerialPortManager getInstance() {
		return InstanceHolder.instance;
	}

	private static class InstanceHolder {
		private static final SerialPortManager instance = new SerialPortManager();
	}

}
