package com.android_app_architecture_demo.weather;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android_app_architecture_demo.R;
import com.android_app_architecture_demo.util.ActivityUtils;

public class WeatherActivity extends AppCompatActivity  implements WeatherFragment.OnFragmentInteractionListener {

    private WeatherPresenter mWeatherPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherFragment weatherFragment =
                (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherFragment == null) {
            // Create the fragment
            weatherFragment = WeatherFragment.newInstance("","");
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), weatherFragment, R.id.contentFrame);
        }

        // Create the presenter
        mWeatherPresenter = new WeatherPresenter(weatherFragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
