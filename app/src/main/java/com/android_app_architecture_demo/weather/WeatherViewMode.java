package com.android_app_architecture_demo.weather;

/**
 * Created by peter on 10/20/17.
 */

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android_app_architecture_demo.data.WeatherDataSource;
import com.android_app_architecture_demo.data.WeatherRepository;

public class WeatherViewMode extends BaseObservable {
    public final ObservableField<String> mWeatherinfo = new ObservableField<>();
    private Context mContext;
    public WeatherViewMode(Context context){
        mContext = context;
    }

    public void start() {
        loadWeather();
    }


    public void loadWeather() {
        // Simplification for sample: a network reload will be forced on first load.
        WeatherRepository.getInstance().getWeather(new WeatherDataSource.LoadWeatherCallback() {
            @Override
            public void onWeatherLoaded(Weather weather) {
                showWeather(weather);
            }
        });
    }

    public void showWeather(Weather weather) {
        StringBuilder weatherInfo = new StringBuilder();
        weatherInfo.append(weather.getCity());
        weatherInfo.append(": today weather is:");
        weatherInfo.append(weather.getWeathers().get(0).toString());
        Message message = Message.obtain(mHandler);
        message.what = 0;
        message.obj = weatherInfo.toString();
        //通过handler将消息发送到主线程来更新界面
        message.sendToTarget();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String weatherInfo = (String)msg.obj;
                    mWeatherinfo.set(weatherInfo.toString());
                    Log.d("TEST","weatherInfo="+weatherInfo.toString());
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
