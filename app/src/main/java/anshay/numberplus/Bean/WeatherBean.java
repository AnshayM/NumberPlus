package anshay.numberplus.Bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import anshay.numberplus.gson.Forecast;

/**
 * Created by Anshay on 2017/8/13.
 */

public class WeatherBean implements Parcelable {//继承接口使其可序列化
    private String icon;
    private String date;
    private String maxTempure;
    private String minTempure;
    private String weatherType1;
    private String weatherType;
    private String myDate;
    private String sunRise;
    private String sunSet;
    private String dir;//风向
    private String sc;//风速
    private Bitmap mbitMap;

    public WeatherBean() {}//重写了Paraclabel接口，无参构造函数需自己写

    public Bitmap getMbitMap() {
        return mbitMap;
    }

    public void setMbitMap(Bitmap mbitMap) {
        this.mbitMap = mbitMap;
    }

    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
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

    public String getMaxTempure() {
        return maxTempure;
    }

    public void setMaxTempure(String maxTempure) {
        this.maxTempure = maxTempure;
    }

    public String getMinTempure() {
        return minTempure;
    }

    public void setMinTempure(String minTempure) {
        this.minTempure = minTempure;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }


    //以下为实习Parcelable接口固定写法
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(icon);
        out.writeString(date);
        out.writeString(maxTempure);
        out.writeString(minTempure);
        out.writeString(weatherType1);
        out.writeString(weatherType);
        out.writeString(myDate);
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
        icon = in.readString();
        date = in.readString();
        maxTempure = in.readString();
        minTempure = in.readString();
        weatherType1 = in.readString();
        weatherType = in.readString();
        myDate = in.readString();
        sunRise = in.readString();
        sunSet = in.readString();
        dir = in.readString();
        sc = in.readString();
        mbitMap = in.readParcelable(null);// 这个地方可以为null 
    }


}
