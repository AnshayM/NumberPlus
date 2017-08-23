package anshay.numberplus.Fragment;

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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.Adapter.MyGridViewAdapter;
import anshay.numberplus.Operator.MOperator;
import anshay.numberplus.R;
import anshay.numberplus.activity.WeatherActivity;
import anshay.numberplus.gson.Forecast;
import anshay.numberplus.gson.Weather;
import anshay.numberplus.util.HttpUtil;
import anshay.numberplus.util.Utility;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Anshay on 2017/8/11.
 * Email: anshaym@163.com

 * 首页界面
 */
public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private String TAG = "mine:  ";//log标签
    private TextView cityName, date, temperatureNow, weatherTypeNow;
    private GridView gridView;
    private MyGridViewAdapter adapter;//gridView适配器
    private WeatherBean bean;//天气类实体
    private ArrayList<WeatherBean> list = new ArrayList<WeatherBean>();//天气类实体的集合

    private LocationManager locationManager;
    private Banner banner;//轮播图控件
    public SwipeRefreshLayout swipeRefresh;//下拉刷 新控件

    Integer[] images = {R.mipmap.befor1, R.mipmap.befor2, R.mipmap.befor3, R.mipmap.befor4, R.mipmap.befor5};//轮播图资源文件
    private String myUrl = "https://free-api.heweather.com/v5/";//接口网址
    public String myKey = "7d600ab4df3d4cad89141901a36dd7e4";//我的私钥
    public String guoKey = "bc0418b57b2d4918819d3974ac1285d9";//郭大神的私钥
    private int UPDATE_INFO = 1;
    private double latitude, longitude;//经纬度
    private String provider;
    private MOperator mOperator;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    final Weather weather = Utility.handleWeatherResponse(msg.getData().getString("responseText"));//解析数据
                    cityName.setText(weather.basic.cityName);//城市
                    weatherTypeNow.setText(weather.now.more.info);//实时天气种类;
                    temperatureNow.setText(weather.now.temperature + "℃");//实时气温
                    setDate();//设置中间栏日期信息
                    saveWeatherInfo(weather);//gridView信息存入数据库

                    adapter = new MyGridViewAdapter(getActivity(), list); // 初始化适配器
                    Log.d(TAG, "初始化适配器");

                    gridView.setAdapter(adapter);// gridView与适配器绑定
                    Log.d(TAG, "gridView与适配器绑定");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);//初始化控件

        initBanner();//初始化顶部轮播图

        //先从数据库查询数据,如果没有数据，就从服务器上去查
        list = mOperator.queryAll();
        if (list.size() == 0) {
            Log.d(TAG, "查询list时无数据");
            initLocation();//先获取经纬度
            getForecastWeatherInfo(latitude, longitude);//从服务器上去查询数据并存到数据库中,此段代码后时list集合中已有数据！
            Log.d(TAG + "网络请求获取后的list长度：", String.valueOf(list.size()));
        } else {
            //如果是从数据库中取得的数据，为每一个实体添加背景图信息
            for (int i = 0; i < list.size(); i++) {
                WeatherBean bean = list.get(i);
                setBitmap(bean);
            }
            Log.d(TAG, "为实体类配置图片完成，数目为" + String.valueOf(list.size()));
        }

        //异步原因handler处理完数据前这几行代码就先运行了，导致页面要很长时间才能出来画面，为了实现出现页面即出现画面，我把这两行代码放在了handler里面
        adapter = new MyGridViewAdapter(getActivity(), list); // 初始化适配器
        gridView.setAdapter(adapter);// gridView与适配器绑定

        setMyInfo();//中间栏信息
        Log.d(TAG, "中间栏信息");

        gridView.setOnItemClickListener(this); //子项的点击事件监听
        swipeRefresh.setOnRefreshListener(this);//下拉刷新监听
        return view;
    }

    /*初始化控件*/
    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);//设置刷新进度条颜色
        cityName = (TextView) view.findViewById(R.id.city);
        date = (TextView) view.findViewById(R.id.date);
        temperatureNow = (TextView) view.findViewById(R.id.temperatureNow);
        weatherTypeNow = (TextView) view.findViewById(R.id.weatherTypeNow);
        gridView = (GridView) view.findViewById(R.id.gridView);

        mOperator = new MOperator(getContext());//初始化数据库操作类
    }

    /*中间栏信息*/
    private void setMyInfo() {
        bean = new WeatherBean();
        if (list.size() > 0) {
            Log.d("mine", "list加载有数据");
            bean = list.get(0);//获取第一项
            cityName.setText(bean.getCity());
            temperatureNow.setText(bean.getNowTemperature());
            weatherTypeNow.setText(bean.getWeatherTypeNow());
        } else {
            Log.d("mine", "list加载无数据");
        }
        setDate();//设置中间栏日期信息
    }

    /*轮播图*/
    public void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < images.length; index++) {
            intList.add(images[index]);
        }
        banner.setImages(intList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /*获取经纬度*/
    public void initLocation() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);//获取所有可用的位置提供器，传入True表示只有启用的位置提供器才会被返回
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = locationManager.GPS_PROVIDER;
            Log.i(TAG + "权限log", "gps");
        } else if (providerList.contains(locationManager.NETWORK_PROVIDER)) {
            provider = locationManager.NETWORK_PROVIDER;
            Log.i(TAG + "权限log", "internet");
        } else {
            Toast.makeText(getActivity(), "no location provider to use", Toast.LENGTH_SHORT).show();
        }
        //获取运时权限
        int permissionCheck = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(getActivity(), "你拒绝了权限请求", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                Log.i(TAG + "权限log", "正在请求权限");
            }
        } else {//如果有权限，就直接进行事件处理
            Log.i(TAG + "权限log", "有权限");
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                Log.i(TAG + "权限log", "显示位置");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.i(TAG + "纬度", String.valueOf(latitude));
                Log.i(TAG + "经度", String.valueOf(longitude));


            }
        }
    }

    // 设置日期信息
    public void setDate() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);//待去0处理，08月改为8月
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        switch (week) {
            case "1":
                week = "天";
                break;
            case "2":
                week = "一";
                break;
            case "3":
                week = "二";
                break;
            case "4":
                week = "三";
                break;
            case "5":
                week = "四";
                break;
            case "6":
                week = "五";
                break;
            case "7":
                week = "六";
                break;
            default:
                break;
        }
//        String ss= "今天是" + year + "年" + month + "月" + day + "日 " + "星期" + week;
//        Message message = new Message();
//        message.what=UPDATE_CENTRAL_TEXT;
//        handler.setEncoding(message);
        date.setText("今天是" + year + "年" + month + "月" + day + "日 " + "星期" + week);//显示日期信息
    }

    /* 根据经纬度请求未来天气信息,并发送给handler*/
    public void getForecastWeatherInfo(Double latitude, Double longitude) {
        String weatherUrl = myUrl + "weather?city=" + latitude + ","
                + longitude + "&key=" + guoKey;
        HttpUtil.sendOKHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {//请求失败调用
//                Toast.makeText(getActivity(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "获取天气信息失败");
                swipeRefresh.setRefreshing(false);//关闭刷新进度条
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("responseText", responseText);
                        message.what = UPDATE_INFO;
                        message.setData(bundle);
                        handler.sendMessage(message);
                        swipeRefresh.setRefreshing(false);//关闭刷新进度条
                    }
                }).start();
            }
        });
    }

    /*将天气信息存在数据库中*/
    private void saveWeatherInfo(Weather weather) {
//                    mlist.clear();//先清空
        for (Forecast forecast : weather.forecastList) {
            WeatherBean mbean = new WeatherBean();

            mbean.setCity(weather.basic.cityName);//城市
            mbean.setWeatherTypeNow(weather.now.more.info);//实时天气种类;
            mbean.setNowTemperature(weather.now.temperature + "℃");//实时气温
            //2017-08-14取字段8月14日
            String d1 = forecast.date.split("-")[1];//截取月
            String d2 = forecast.date.split("-")[2];//截取日
            mbean.setDate(d1 + "月" + d2 + "日");
            mbean.setIcon(forecast.more.iconId);//天气种类id
            mbean.setMaxTemperature(forecast.temperature.max);//最高温
            mbean.setMinTemperature(forecast.temperature.min);//最低温
            mbean.setWeatherType1(forecast.more.info1);//白天天气种类
            mbean.setWeatherType2(forecast.more.info2);//晚上天气种类
            mbean.setDir(forecast.wind.dir);//风向
            mbean.setSc(forecast.wind.sc);//风向
            mbean.setSunRise(forecast.sun.sr);//日出
            mbean.setSunSet(forecast.sun.ss);//日落
            setBitmap(mbean);
            list.add(mbean);
            mOperator.insert(mbean);//添加到数据库中去
        }
        Log.d(TAG + "网络请求后的list：", String.valueOf(list.size()));
    }

    /*为Weatherbean设置bitmap属性*/
    private void setBitmap(WeatherBean bean) {
        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        Bitmap bitmap;
        String iconName = "a" + bean.getIcon();//设置图片名
        //第一个参数是图片名，第二个是位置目录，第三个是获取项目中的包
        int resID = getResources().getIdentifier(iconName, "mipmap", appInfo.packageName);
        bitmap = BitmapFactory.decodeResource(getResources(), resID);
        bean.setMbitMap(bitmap);
    }


    /*gridView子项点击事件*/
    //问题，我怎么知道哪一个onItemClick是谁的。
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bean = new WeatherBean();
        bean = list.get(position);
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra("mybean", bean);
        startActivity(intent);//先获取到当前的Activity，再做跳转
    }

    @Override
    public void onRefresh() {
//        for (int i = 1; i <list.size(); i++) {
//            mOperator.delete(i);//删除数据库中的数据
//        }
//        initLocation();//先获取经纬度
//        getForecastWeatherInfo(latitude, longitude);//从服务器上去查询数据并存到数据库中,此段代码后时list集合中已有数据！
        list = mOperator.queryAll();
        swipeRefresh.setRefreshing(false);

        adapter = new MyGridViewAdapter(getActivity(), list); // 初始化适配器
        for (int i = 0; i < list.size(); i++) {
            WeatherBean bean = list.get(i);
            setBitmap(bean);
        }
        gridView.setAdapter(adapter);// gridView与适配器绑定
    }

////    下拉刷新
//    @Override
//    public void onRefresh() {
//        Log.d(TAG, "下拉刷新");
//        initLocation();//先获取经纬度
//        //不能做添加，要做的是更新数据数据/
////        getForecastWeatherInfo(latitude, longitude);//从服务器上去查询数据并存到数据库中
//
//    }
}

/*三方jar包要求重写ImageLoader*/
class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
//        eg：
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
        //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView);
//        用fresco加载图片简单用法，记得要写下面的createImageView方法
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
    }
    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//    @Override
//    public ImageView createImageView(Context context) {
//        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//        return simpleDraweeView;
//    }
}
