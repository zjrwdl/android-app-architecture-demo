package com.android_app_architecture_demo.weather;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android_app_architecture_demo.R;
import com.android_app_architecture_demo.ViewModelHolder;
import com.android_app_architecture_demo.util.ActivityUtils;

public class WeatherActivity extends AppCompatActivity  implements WeatherFragment.OnFragmentInteractionListener {

    private WeatherViewMode mWeatherViewMode;
    public static final String WEATHER_VIEWMODEL_TAG = "WEATHER_VIEWMODEL_TAG";
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

        mWeatherViewMode = findOrCreateViewModel();
        weatherFragment.setViewModel(mWeatherViewMode);
        // Create the presenter
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private WeatherViewMode findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present. It's retained using the
        // Fragment Manager.
        @SuppressWarnings("unchecked")
        ViewModelHolder<WeatherViewMode> retainedViewModel =
                (ViewModelHolder<WeatherViewMode>) getSupportFragmentManager()
                        .findFragmentByTag(WEATHER_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            WeatherViewMode viewModel = new WeatherViewMode(getApplicationContext());
            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    WEATHER_VIEWMODEL_TAG);
            return viewModel;
        }
    }
}
