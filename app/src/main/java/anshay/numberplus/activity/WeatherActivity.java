package anshay.numberplus.activity;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.R;
import anshay.numberplus.gson.Forecast;
import anshay.numberplus.gson.Weather;
import anshay.numberplus.util.HttpUtil;
import anshay.numberplus.util.Utility;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Anshay on 2017/8/13.
 */

public class WeatherActivity extends AppCompatActivity {
    private TextView city;
    private TextView date;
    private TextView min;
    private TextView max;
    private TextView type1;
    private TextView type2;
    private TextView dir;//风向
    private TextView sc;//风速
    private TextView sunrise;
    private TextView sunset;
    private WeatherBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        date = (TextView) findViewById(R.id.mDate);
        min = (TextView) findViewById(R.id.min);
        max = (TextView) findViewById(R.id.max);
        type1 = (TextView) findViewById(R.id.type1);
        type2 = (TextView) findViewById(R.id.type2);
        dir = (TextView) findViewById(R.id.dir);
        sc = (TextView) findViewById(R.id.sc);
        sunrise = (TextView) findViewById(R.id.sr);
        sunset = (TextView) findViewById(R.id.ss);


//        bean = (WeatherBean) getIntent().getSerializableExtra("mybean");
        String myCity = getIntent().getStringExtra("myCity");
        String myDate = getIntent().getStringExtra("myDate");

//        city.setText(myCity);
//        date.setText(myDate);

        bean = new WeatherBean();
//        getInfo(myCity, myDate);
        date.setText(bean.getDate());
        min.setText(bean.getMinTempure());
        max.setText(bean.getMaxTempure());
        type1.setText(bean.getWeatherType());//白天
        type2.setText(bean.getWeatherType1());//夜间
        sunrise.setText(bean.getSunRise());
        sunset.setText(bean.getSunSet());
        dir.setText(bean.getDir());//风向
        sc.setText(bean.getSc());//风力

//        type2.setText(bean.getWeatherType());
//        String myCity = getIntent().getStringExtra("string_data");
//        Log.d("dd", myDate);
//        Log.d("dd", myCity);

    }

//    public void getInfo(String myCity, final String myDate) {
////        Log.d("请求天气数据，拿到的weatherId：", weatherId);
//        String myKey = "&key=7d600ab4df3d4cad89141901a36dd7e4";//我的私钥
//        String guoKey = "&key=bc0418b57b2d4918819d3974ac1285d9";//郭大神的私钥
//        String weatherUrl = "https://free-api.heweather.com/v5/weather?city=" + myCity + myKey;
//        HttpUtil.sendOKHttpRequest(weatherUrl, new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {//请求失败调用
//            }
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                //或用另一种方法，GirdLayout使用addView方法添加子项。
//                final String responseText = response.body().string();
//                final Weather weather = Utility.handleWeatherResponse(responseText);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ApplicationInfo appInfo = getApplicationInfo();
//                        Bitmap bitmap;
//
//                        for (Forecast forecast : weather.forecastList) {
//
//                            WeatherBean mbean = new WeatherBean();
//                            if (myDate == forecast.date) {
//                                mbean.setMyDate(forecast.date);//详细天气信息，用来做传递参数
//                                //2017-08-14取字段8月14日
//                                String d1 = forecast.date.split("-")[1];//截取月
//                                String d2 = forecast.date.split("-")[2];//截取日
//                                mbean.setDate(d1 + "月" + d2 + "日");
//                                mbean.setIcon(forecast.more.iconId);
//                                Log.d("getIcon", mbean.getIcon());
//                                mbean.setMaxTempure(forecast.temperature.max);//最高温
//                                mbean.setMinTempure(forecast.temperature.min);//最低温
//                                mbean.setWeatherType(forecast.more.info1);//白天天气种类
//                                mbean.setWeatherType1(forecast.more.info2);//晚上天气种类
//                                mbean.setDir(forecast.wind.dir);//风向
//                                mbean.setSc(forecast.wind.sc);//风向
//                                mbean.setSunRise(forecast.sun.sr);//日出
//                                mbean.setSunSet(forecast.sun.ss);//日落
//                                String iconName = "a" + mbean.getIcon();//设置图片名
//                                //第一个参数是图片名，第二个是位置目录，第三个是获取项目中的包
//                                int resID = getResources().getIdentifier(iconName, "mipmap", appInfo.packageName);
//                                bitmap = BitmapFactory.decodeResource(getResources(), resID);
//                                mbean.setMbitMap(bitmap);
//                            }
//                        }
//                    }
//                });
//            }
//        });
//    }
}
