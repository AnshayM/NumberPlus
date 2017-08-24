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
    private int myId;
    private String city;
    private String icon;
    private String date;
    private String maxTemperature;
    private String minTemperature;
    private String weatherTypeDay;
    private String weatherTypeNight;
    private String sunRise;
    private String sunSet;
    private String dir;//风向
    private String sc;//风速
    private Bitmap myBitMap;
    private String nowTemperature;
    private String weatherTypeNow;
    private String updateTime;//更新时间

    public WeatherBean() {
    }//重写了Parcelable接口，无参构造函数需自己写

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getWeatherTypeDay() {
        return weatherTypeDay;
    }

    public void setWeatherTypeDay(String weatherTypeDay) {
        this.weatherTypeDay = weatherTypeDay;
    }

    public String getWeatherTypeNight() {
        return weatherTypeNight;
    }

    public void setWeatherTypeNight(String weatherTypeNight) {
        this.weatherTypeNight = weatherTypeNight;
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

    public Bitmap getMyBitMap() {
        return myBitMap;
    }

    public void setMyBitMap(Bitmap myBitMap) {
        this.myBitMap = myBitMap;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.myId);
        dest.writeString(this.city);
        dest.writeString(this.icon);
        dest.writeString(this.date);
        dest.writeString(this.maxTemperature);
        dest.writeString(this.minTemperature);
        dest.writeString(this.weatherTypeDay);
        dest.writeString(this.weatherTypeNight);
        dest.writeString(this.sunRise);
        dest.writeString(this.sunSet);
        dest.writeString(this.dir);
        dest.writeString(this.sc);
        dest.writeParcelable(this.myBitMap, flags);
        dest.writeString(this.nowTemperature);
        dest.writeString(this.weatherTypeNow);
        dest.writeString(this.updateTime);
    }

    protected WeatherBean(Parcel in) {
        this.myId = in.readInt();
        this.city = in.readString();
        this.icon = in.readString();
        this.date = in.readString();
        this.maxTemperature = in.readString();
        this.minTemperature = in.readString();
        this.weatherTypeDay = in.readString();
        this.weatherTypeNight = in.readString();
        this.sunRise = in.readString();
        this.sunSet = in.readString();
        this.dir = in.readString();
        this.sc = in.readString();
        this.myBitMap = in.readParcelable(Bitmap.class.getClassLoader());
        this.nowTemperature = in.readString();
        this.weatherTypeNow = in.readString();
        this.updateTime = in.readString();
    }

    public static final Creator<WeatherBean> CREATOR = new Creator<WeatherBean>() {
        @Override
        public WeatherBean createFromParcel(Parcel source) {
            return new WeatherBean(source);
        }

        @Override
        public WeatherBean[] newArray(int size) {
            return new WeatherBean[size];
        }
    };
}
