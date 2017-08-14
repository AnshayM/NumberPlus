package anshay.numberplus.Bean;

import java.io.Serializable;
import java.util.Date;

import anshay.numberplus.gson.Forecast;

/**
 * Created by Anshay on 2017/8/13.
 */

public class WeatherBean implements Serializable {//继承接口使其可序列化
    private int icon;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
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
}
