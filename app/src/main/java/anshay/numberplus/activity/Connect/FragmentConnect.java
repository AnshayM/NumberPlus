package anshay.numberplus.activity.Connect;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.Operator.MOperator;
import anshay.numberplus.R;

/**
 * Created by Anshay on 2017/8/11.
 * 联系人界面
 */

public class FragmentConnect extends Fragment {
    private TextView mtv;//页面介绍
    private RecyclerView recyclerView;
    private ArrayList<WeatherBean> list = new ArrayList<>();
    private MOperator mOperator;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        initView(view);
        mOperator = new MOperator(getContext());//初始化数据库操作类
        list = mOperator.queryAll();//从数据库获取信息，因为app默认先走Fragment，所以此时已有数据。问题不大。
        //为数据库获取的list根据icon信息配置图片
        for (int i = 0; i < list.size(); i++) {
            WeatherBean bean = list.get(i);
            setBitmap(bean);
        }
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向滑动
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setBitmap(WeatherBean bean) {//此方法多次使用，可考虑放在工具类中
        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        String iconName = "a" + bean.getIcon();//设置图片名
        //第一个参数是图片名，第二个是位置目录，第三个是获取项目中的包
        int resID = getResources().getIdentifier(iconName, "mipmap", appInfo.packageName);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
        bean.setMyBitMap(bitmap);
    }

    private void initView(View view) {
        mtv = (TextView) view.findViewById(R.id.fragmentConnect_tv);
        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
    }
}
