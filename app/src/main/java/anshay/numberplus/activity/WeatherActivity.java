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

        bean = getIntent().getParcelableExtra("mybean");//获取上一个界面传递过来的weatherbean对象

        date.setText(bean.getDate());
        min.setText(bean.getMinTempure());
        max.setText(bean.getMaxTempure());
        type1.setText(bean.getWeatherType());//白天
        type2.setText(bean.getWeatherType1());//夜间
        sunrise.setText(bean.getSunRise());
        sunset.setText(bean.getSunSet());
        dir.setText(bean.getDir());//风向
        sc.setText(bean.getSc());//风力

    }

}
