package anshay.numberplus.Bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import anshay.numberplus.gson.Forecast;

/**
 * Created by Anshay on 2017/8/13.
 * 天气实体
 */

public class WeatherBean implements Parcelable {//继承接口使其可序列化
    private int id;
    private String city;
    private String icon;
    private String date;
    private String maxTemperature;
    private String minTemperature;
    private String weatherType1;
    private String weatherType2;
    private String sunRise;
    private String sunSet;
    private String dir;//风向
    private String sc;//风速
    private Bitmap mbitMap;
    private String nowTemperature;
    private String weatherTypeNow;

    public WeatherBean() {
    }//重写了Paraclabel接口，无参构造函数需自己写

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNowTemperature() {
        return nowTemperature;
    }

    public void setNowTemperature(String nowTemperature) {
        this.nowTemperature = nowTemperature;
    }

    public String getWeatherTypeNow() {
        return weatherTypeNow;
    }

    public void setWeatherTypeNow(String weatherTypeNow) {
        this.weatherTypeNow = weatherTypeNow;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Bitmap getMbitMap() {
        return mbitMap;
    }

    public void setMbitMap(Bitmap mbitMap) {
        this.mbitMap = mbitMap;
    }

    public String getWeatherType1() {
        return weatherType1;
    }

    public void setWeatherType1(String weatherType1) {
        this.weatherType1 = weatherType1;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getWeatherType2() {
        return weatherType2;
    }

    public void setWeatherType2(String weatherType2) {
        this.weatherType2 = weatherType2;
    }


    //以下为实现Parcelable接口固定写法
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(weatherTypeNow);
        out.writeString(nowTemperature);
        out.writeString(city);
        out.writeString(icon);
        out.writeString(date);
        out.writeString(maxTemperature);
        out.writeString(minTemperature);
        out.writeString(weatherType1);
        out.writeString(weatherType2);
        out.writeString(sunRise);
        out.writeString(sunSet);
        out.writeString(dir);
        out.writeString(sc);
        out.writeParcelable(mbitMap, flags);
    }

    public static final Parcelable.Creator<WeatherBean> CREATOR = new Creator<WeatherBean>() {
        @Override
        public WeatherBean[] newArray(int size) {
            return new WeatherBean[size];
        }

        @Override
        public WeatherBean createFromParcel(Parcel in) {
            return new WeatherBean(in);
        }
    };

    public WeatherBean(Parcel in) {
        nowTemperature = in.readString();
        weatherTypeNow = in.readString();
        city = in.readString();
        icon = in.readString();
        date = in.readString();
        maxTemperature = in.readString();
        minTemperature = in.readString();
        weatherType1 = in.readString();
        weatherType2 = in.readString();
        sunRise = in.readString();
        sunSet = in.readString();
        dir = in.readString();
        sc = in.readString();
        mbitMap = in.readParcelable(null);// 这个地方可以为null 
    }


}
