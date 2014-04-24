package org.moon.test.jpa.query;

public abstract class AbstractBond implements Bond {

	private String key;
	private String logic;
	private String type;
	private String alias;
	private Object value;

	AbstractBond(String key, String logic, String type, Object value) {
		this.key = key;
		this.logic = logic;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public String getLogic() {
		return logic;
	}

	public String getType() {
		return type;
	}

	public String getAlias() {
		return alias;
	}

	public Object getValue() {
		return value;
	}

	protected void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return type + " " + key + " " + logic + " " + value;
	}

}