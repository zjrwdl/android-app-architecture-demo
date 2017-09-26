/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android_app_architecture_demo.weather;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;


import com.android_app_architecture_demo.data.WeatherDataSource;
import com.android_app_architecture_demo.data.WeatherRepository;
import com.android_app_architecture_demo.util.Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xmlpull.v1.XmlPullParser;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link WeatherFragment}), retrieves the data and updates the
 * UI as required.
 */
public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View mTasksView;


    public WeatherPresenter(@NonNull WeatherContract.View tasksView) {
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadWeather();
    }


    @Override
    public void loadWeather() {
        // Simplification for sample: a network reload will be forced on first load.
        WeatherRepository.getInstance().getWeather(new WeatherDataSource.LoadWeatherCallback() {
            @Override
            public void onWeatherLoaded(Weather weather) {
                mTasksView.showWeather(weather);
            }
        });
    }

}
