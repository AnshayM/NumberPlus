package anshay.numberplus.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.MyAdapter;
import anshay.numberplus.R;
import anshay.numberplus.gson.Forecast;
import anshay.numberplus.gson.Weather;
import anshay.numberplus.util.HttpUtil;
import anshay.numberplus.util.Utility;
import okhttp3.Callback;
import okhttp3.Response;

import static anshay.numberplus.R.id.imageView;


/**
 * Created by Anshay on 2017/8/11.
 */
public class FragmentHome extends Fragment {

    private ImageView swiper;
    private TextView cityName, date, tempureNow, weatherTypeNow;
    private GridView gridView;
    private MyAdapter adapter;
    private WeatherBean bean;
    ArrayList<WeatherBean> list;
    private LocationManager locationManager;
    private String provider;
    private double latitude, longitude;
    private String city, degreeNow, weatherType;//中间栏关于天气的变量名。

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        cityName = (TextView) view.findViewById(R.id.city);
        date = (TextView) view.findViewById(R.id.date);
        tempureNow = (TextView) view.findViewById(R.id.tempureNow);
        weatherTypeNow = (TextView) view.findViewById(R.id.weatherTypeNow);
        swiper = (ImageView) view.findViewById(R.id.swiper);
        gridView = (GridView) view.findViewById(R.id.gridView);

        /*
        * 获取位置信息
        * */
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);//获取所有可用的位置提供器，传入True表示只有启用的位置提供器才会被返回
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = locationManager.GPS_PROVIDER;
            Log.i("log", "gps");
        } else if (providerList.contains(locationManager.NETWORK_PROVIDER)) {
            provider = locationManager.NETWORK_PROVIDER;
            Log.i("log", "network");
        } else {
            Toast.makeText(getActivity(), "no location provider to use", Toast.LENGTH_SHORT).show();
        }

        /*调用getLocation()方法调用获取到经纬度*/
        getLocation();
         /*中间栏信息更新 */
        setNowWeather(latitude, longitude);//传入拿到的经纬度，调用方法获取实时城市，温度，天气种类信息,并设置到控件中
        setDate();//设置中间栏日期信息

        list = new ArrayList<WeatherBean>();//初始化集合
        setGirdView(latitude, longitude);//设置girdView栏数据

        adapter = new MyAdapter(getActivity(), list);    // 初始化适配器
        gridView.setAdapter(adapter);// gridView与适配器绑定

        //子项的点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean = new WeatherBean();
                bean = list.get(position);
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                intent.putExtra("mydate", bean.getMyDate());
//                intent.putExtra("myCity", city);


//                bean = new WeatherBean();
//                bean = list.get(position);
//                Intent intent = new Intent(getActivity(), WeatherActivity.class);
//                Bundle mybundle = new Bundle();
//                mybundle.putParcelable("mybean", (Parcelable) bean);
//                intent.putExtra("myDate",bean.getMyDate());
//                startActivity(intent);//先获取到当前的Activity，再做跳转
//                getActivity().finish();
//                Toast.makeText(getActivity(), bean.getDate(), Toast.LENGTH_SHORT).show();
            }
        });
//        Log.d("碎片：", "onCreateView1: ");
        return view;
    }

    // 设置日期信息
    private void setDate() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(week)) {
            week = "天";
        }
        if ("2".equals(week)) {
            week = "一";
        }
        if ("3".equals(week)) {
            week = "二";
        }
        if ("4".equals(week)) {
            week = "三";
        }
        if ("5".equals(week)) {
            week = "四";
        }
        if ("6".equals(week)) {
            week = "五";
        }
        if ("7".equals(week)) {
            week = "六";
        }
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append("今天是" + year + "年" + month + "月" + day + "日 " + "星期" + week);
        date.setText(sbBuffer);//获取日期信息
    }

    //获取经纬度信息
    private void getLocation() {
        int permissionCheck = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("权限log", "没有权限");
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.i("权限log", "拒绝声明");
                Toast.makeText(getActivity(), "u had rejected the request", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {//如果有权限，就直接进行事件处理
            Log.i("权限log", "有权限");
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                Log.i("权限log", "显示位置");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
//        return sbBuffer.toString();
    }

    /*获取实时天气，并显示在中间信息栏*/
    public void setNowWeather(Double latitude, Double longitude) {
        String weatherUrl = "https://free-api.heweather.com/v5/weather?city=" + longitude + "," + latitude
                + "&key=7d600ab4df3d4cad89141901a36dd7e4";
//        Log.d("sss", weatherUrl);
        HttpUtil.sendOKHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                if (weather != null && "ok".equals(weather.status)) {
                    //显示中间栏显示实时天气信息
                    city = "武汉" + weather.basic.cityName + "区";//所在城市，此处所给接口返回数据只有洪山，为初步完成功能所以自己静态加一个武汉。
                    degreeNow = weather.now.temperature + "℃";//实时气温
                    weatherType = weather.now.more.info;//天气种类
                    //转到主线程更新实时天气信息
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cityName.setText(city);
                            tempureNow.setText(degreeNow);
                            weatherTypeNow.setText(weatherType);


                        }
                    });
                }
            }
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {//请求失败调用
                Toast.makeText(getActivity(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /* 根据经纬度请求天气信息,并存到集合中，为GirdView提供数据*/
    public void setGirdView(Double latitude, Double longitude) {
//        Log.d("请求天气数据，拿到的weatherId：", weatherId);
        String myKey = "&key=7d600ab4df3d4cad89141901a36dd7e4";//我的私钥
        String guoKey = "&key=bc0418b57b2d4918819d3974ac1285d9";//郭大神的私钥
        String weatherUrl = "https://free-api.heweather.com/v5/weather?city=" + latitude + ","
                + longitude + myKey;
        HttpUtil.sendOKHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {//请求失败调用
                Toast.makeText(getActivity(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //或用另一种方法，GirdLayout使用addView方法添加子项。
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationInfo appInfo = getActivity().getApplicationInfo();
                        Bitmap bitmap;
                        for (Forecast forecast : weather.forecastList) {
                            WeatherBean mbean = new WeatherBean();
                            mbean.setMyDate(forecast.date);//详细天气信息，用来做传递参数
                            //2017-08-14取字段8月14日
                            String d1 = forecast.date.split("-")[1];//截取月
                            String d2 = forecast.date.split("-")[2];//截取日
                            mbean.setDate(d1 + "月" + d2 + "日");
                            mbean.setIcon(forecast.more.iconId);
                            Log.d("getIcon", mbean.getIcon());
                            mbean.setMaxTempure(forecast.temperature.max);//最高温
                            mbean.setMinTempure(forecast.temperature.min);//最低温
                            mbean.setWeatherType(forecast.more.info1);//白天天气种类
                            mbean.setWeatherType1(forecast.more.info2);//晚上天气种类
                            mbean.setDir(forecast.wind.dir);//风向
                            mbean.setSc(forecast.wind.sc);//风向
                            mbean.setSunRise(forecast.sun.sr);//日出
                            mbean.setSunSet(forecast.sun.ss);//日落

                            String iconName = "a" + mbean.getIcon();//设置图片名
                            //第一个参数是图片名，第二个是位置目录，第三个是获取项目中的包
                            int resID = getResources().getIdentifier(iconName, "mipmap", appInfo.packageName);
                            bitmap = BitmapFactory.decodeResource(getResources(), resID);
                            mbean.setMbitMap(bitmap);
                            list.add(mbean);
                        }
                    }
                });
            }
        });
    }
//    public  void getBitmap(String string) {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
////                    String ss = "https://cdn.heweather.com/cond_icon/101.png";
//                    URL url = new URL("https://cdn.heweather.com/cond_icon/" +
//                            "101" + ".png");
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    InputStream is = conn.getInputStream();
//                    bitmap = BitmapFactory.decodeStream(is);
////                    is.close();
////                    return bitmap;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setImageBitmap(bitmap);
//            }
//        });
//    }
}