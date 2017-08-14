package anshay.numberplus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.R;

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

        WeatherBean bean = (WeatherBean) getIntent().getSerializableExtra("mybean");
//        String myCity = getIntent().getStringExtra("myCity");
//        String myDate = getIntent().getStringExtra("myDate");

//        city.setText(myCity);
//        date.setText(myDate);
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

}
