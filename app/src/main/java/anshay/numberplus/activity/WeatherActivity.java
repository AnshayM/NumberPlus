package anshay.numberplus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.R;
/**
 * Created by Anshay on 2017/8/13.
 * 天气详情页
 */

public class WeatherActivity extends AppCompatActivity {
    private TextView date;//日期
    private TextView min;//最低温
    private TextView max;//最高温
    private TextView type1;//白天天气
    private TextView type2;//晚上天气
    private TextView dir;//风向
    private TextView sc;//风速
    private TextView sunrise;//日出时间
    private TextView sunset;//日落时间
    private WeatherBean bean;//天气实体类
    private TextView city;
    private String myCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        city = (TextView) findViewById(R.id.city);
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
//        myCity = getIntent().getStringExtra("mycity");
        Log.d("intent", "收到的intent  " + myCity);//为空，待完善
//        city.setText(getIntent().getExtras("mycity"));

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
