package com.android_app_architecture_demo.data;

import com.android_app_architecture_demo.weather.Weather;

/**
 * Created by peter on 9/26/17.
 */

public interface WeatherDataSource {
    interface LoadWeatherCallback{
        void onWeatherLoaded(Weather weather);
    }

    public void getWeather(LoadWeatherCallback callback);
}
