package com.android_app_architecture_demo.util;

/**
 * Created by peter on 2017/7/18.
 */

public class Util {
    private static final String YAHOO_URI = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20" +
            "weather.forecast%20where%20woeid%20=%20";

    public static String getUrl(String woeid) {
        String url = YAHOO_URI +
                woeid;
        return url;
    }
}
