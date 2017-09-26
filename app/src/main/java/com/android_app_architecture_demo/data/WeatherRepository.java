package com.android_app_architecture_demo.data;

import android.text.TextUtils;
import android.util.Xml;

import com.android_app_architecture_demo.util.Util;
import com.android_app_architecture_demo.weather.City;
import com.android_app_architecture_demo.weather.DayWeather;
import com.android_app_architecture_demo.weather.Weather;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by peter on 9/26/17.
 */

public class WeatherRepository implements WeatherDataSource {
    private static WeatherRepository INSTANCE = null;

    public static WeatherRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WeatherRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getWeather(LoadWeatherCallback callback) {
        gpsQueryCurrentCity(callback);
    }

    //使用jsoup解析https://www.yahoo.com/news/weather/，分析出当前Ip地址对应的城市
    private void gpsQueryCurrentCity(final LoadWeatherCallback callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://www.yahoo.com/news/weather/")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String html = response.body().string();
                    Document doc = Jsoup.parse(html);
                    Element ListDiv = doc.getElementsByAttributeValue("class","weather").first();
                    String woeid = ListDiv.attr("data-woeid");
                    City city = null;
                    if(woeid !=null && !TextUtils.isEmpty(woeid)){
                        city = new City("",woeid);
                    }
                    String urlString = Util.getUrl(city.getWoeid());
                    queryWeather(urlString,callback);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }


    private void queryWeather(String url,final LoadWeatherCallback callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String weatherResponse = response.body().string();
                    parser(weatherResponse,callback);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }

    synchronized private boolean parser(String buffer,LoadWeatherCallback callback) {
        Weather weather = new Weather();
        ByteArrayInputStream tInputStringStream = null;
        if (buffer != null && !buffer.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(buffer.getBytes());
        } else {
            return false;
        }
        XmlPullParser xmlParser = Xml.newPullParser();//获得XmlPullParser解析器
        try {
            //得到文件流，并设置编码方式
            xmlParser.setInput(tInputStringStream, "UTF-8");

            //获得解析到的事件类别，这里有开始文档，结束文档，开始标签，结束标签，文本等等事件。
            int evtType = xmlParser.getEventType();

            while(evtType != XmlPullParser.END_DOCUMENT)//一直循环，直到文档结束
            {
                switch(evtType)
                {
                    case XmlPullParser.START_TAG:
                        String tag = xmlParser.getName();
                        if (tag.equalsIgnoreCase("pubDate")) {
                            String pubDate = xmlParser.nextText();
                            weather.setLastBuildDate(pubDate);
                        } else if (tag.equalsIgnoreCase("condition")) {
                            String temp = xmlParser.getAttributeValue(null, "temp");
                            weather.setTemp(temp);
                        } else if (tag.equalsIgnoreCase("forecast")) {
                            String day = xmlParser.getAttributeValue(null, "day");
                            String date = xmlParser.getAttributeValue(null, "date");
                            String low = xmlParser.getAttributeValue(null, "low");
                            String high = xmlParser.getAttributeValue(null, "high");
                            String text = xmlParser.getAttributeValue(null, "text");
                            DayWeather dayWeather = new DayWeather();
                            dayWeather.setDay(day);
                            dayWeather.setDate(date);
                            dayWeather.setLow(low);
                            dayWeather.setHigh(high);
                            dayWeather.setWeather(text);
                            weather.putDayWeather(dayWeather);
                        }else if (tag.equalsIgnoreCase("location")){
                            String city = xmlParser.getAttributeValue(null, "city");
                            weather.setCity(city);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        //标签结束
                    default:break;
                }
                //如果xml没有结束，则导航到下一个节点
                evtType = xmlParser.next();
            }
            callback.onWeatherLoaded(weather);
        } catch (Exception e) {
            return false;
        }finally {
        }
        return true;
    }
}
