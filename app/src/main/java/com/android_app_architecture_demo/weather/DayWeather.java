package com.android_app_architecture_demo.weather;

import android.content.Context;


// 每一天的天气
public class DayWeather{
	// <yweather:forecast day="Sun" date="6 Sep 2015" low="66" high="89" text="Mostly Sunny" code="34"/>
	private String day;
	private String date;
	private String low;
	private String high;
	private String weather;
	
	public DayWeather() {
		super();
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String buildTemp(Context c) {
		return low + "~" + high + "℃";
//		return low + "~" + high + "℉";
	}
	
	@Override
	public String toString() {
		return "DayWeather [day=" + day + ", date=" + date + ", low=" + low
				+ ", high=" + high + ", weather=" + weather + "]";
	}
}
