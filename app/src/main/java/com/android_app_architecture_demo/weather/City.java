package com.android_app_architecture_demo.weather;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = -8730643835168072924L;
	
	private String city;
	private String woeid;
	
	public City(String city, String woeid) {
		super();
		this.city = city;
		this.woeid = woeid;
	}

	public String getCity() {
		return city;
	}

	public String getWoeid() {
		return woeid;
	}
}
