package org.moon.test.mybatis.persistence;

public class Country extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String countryName;

	public Country() {
	}

	public Country(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
