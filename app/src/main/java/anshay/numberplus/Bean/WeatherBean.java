package anshay.numberplus.Bean;

import android.graphics.Bitmap;
import android.os.Parcel;

import java.io.Serializable;
import java.util.Date;

import anshay.numberplus.gson.Forecast;

/**
 * Created by Anshay on 2017/8/13.
 */

public class WeatherBean implements Serializable {//继承接口使其可序列化
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

    /**
     * 32      * 內容接口描述
     * 33
     */
//    public int describeContents() {
//        return 0;
//    }

//    // 將数据写入Parcel容器，由Parcel容器保存，以便从parcel容器获取数据
//    public void writeTparcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeParcelable(bmp, flags);
//    }
//        43     @Override
//44     public void writeToParcel(Parcel  , int flags) {
//        45         // TODO Auto-generated method stub
//        46         dest.writeString(name);
//        47         dest.writeParcelable(bmp, flags);
//        48
//        49     }
//50
//        51     /**
// 52      * 静态的Parcelable.Creator接口
// 53      * 其中public static final这几个修饰符一个都不能少，内部对象CREATOR的名称也不能改变，必须全部大写。
// 54      */
//        55     public static final Parcelable.Creator<Finger> CREATOR = new Parcelable.Creator<Finger>() {
//56
//        57         /**
// 58          * 实现从Parcel容器中读取传递数据值，封装成Parcelable对象返回逻辑层
// 59          */
//        60         @Override
//61         public Finger createFromParcel(Parcel source) {
//            62             // TODO Auto-generated method stub
//            63             return new Finger(source);
//            64         }
//65
//        66         /**
// 67          * 仅一句话即可（return new T[size]），供外部类反序列化本类数组使用。
// 68          */
//        69         @Override
//70         public Finger[] newArray(int size) {
//            71             // TODO Auto-generated method stub
//            72             return new Finger[size];
//            73         }
//74
//        75     };
//76
//        77     public Finger() {
//        78
//        79     }
//80
//        81     private Finger(Parcel in) {
//        82         name = in.readString();
//        83         bmp = in.readParcelable(Bitmap.class.getClassLoader());
//        84         ;
//        85
//        86     }

}
