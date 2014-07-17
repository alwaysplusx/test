package org.moon.test.jpa.query;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractBond implements Bond {

	protected String key;
	protected String logic;
	protected String link;
	protected String alias;
	protected Object value;
	protected String sqlValue;
	protected boolean isDate;

	AbstractBond(String key, String logic, String link, Object value) {
		this.key = key;
		this.logic = logic;
		this.link = link;
		this.value = value;
		this.sqlValue = value.toString();
		this.isDate = isDate(value);
	}

	private boolean isDate(Object value) {
		if (value instanceof Calendar || value instanceof Date) {
			return true;
		}
		return false;
	}

	public String getKey() {
		return key;
	}

	public String getLogic() {
		return logic;
	}

	public String getLink() {
		return link;
	}

	public String getAlias() {
		return alias;
	}

	public Object getValue() {
		return value;
	}
	
	@Override
	public String toSQL(String tableAlias) {
		if(isDate){
			return " " + this.link + " " + tableAlias + "." + this.key + " " + this.logic + " " + this.sqlValue;	
		}
		return " " + this.link + " " + tableAlias + "." + this.key + " " + this.logic + " '" + this.sqlValue + "'";
	}

	@Override
	public String toXQL(String tableAlias) {
		return " " + this.link + " " + tableAlias + "." + this.key +" "+ this.logic + ":" + this.alias;
	}
	
}