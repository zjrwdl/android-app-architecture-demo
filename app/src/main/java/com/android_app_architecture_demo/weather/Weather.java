package com.android_app_architecture_demo.weather;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class Weather {
	private String city;
	private String lastBuildDate;
	private String temp;
	private List<DayWeather> mWeathers;
	
	public Weather() {
		super();
		mWeathers = new ArrayList<DayWeather>();
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public void putDayWeather(DayWeather dayWeather) {
		mWeathers.add(dayWeather);
	}
	
	public List<DayWeather> getWeathers() {
		return mWeathers;
	}
	
	public String buildTemp(Context c) {
		return temp + "℃";
//		return temp + "℉";
	}
}
