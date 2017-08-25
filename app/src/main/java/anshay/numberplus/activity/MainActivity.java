package anshay.numberplus.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import anshay.numberplus.Fragment.FragmentHome;
import anshay.numberplus.Fragment.FragmentMsg;
import anshay.numberplus.Fragment.FragmentSetting;
import anshay.numberplus.R;
import anshay.numberplus.Fragment.FragmentConnect;
/**
 * Created by Anshay on 2017/8/13.
 * 首页
 */
public class MainActivity extends AppCompatActivity {

    private FragmentHome home;
    private FragmentMsg msg;
    private FragmentConnect connect;
    private FragmentSetting setting;
    private List<Fragment> fList;//碎片集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();//初始化控件
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        showFragment(home,transaction);//设置第一次加载时候的默认页为FragmentHome

    }

    private void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fList = new ArrayList<>();//初始化集合
        home=new FragmentHome();
        msg=new FragmentMsg();
        connect = new FragmentConnect();
        setting = new FragmentSetting();
    }

    /*显示碎片页面*/
    private void showFragment(Fragment fragment,FragmentTransaction transaction) {
        //判断集合中是否存在这个碎片
        if(!fList.contains(fragment)){
            fList.add(fragment);
            transaction.add(R.id.content,fragment);
        }else{
            transaction.show(fragment);
        }
        transaction.commit();//提交，页面进行变化
    }

   /* 隐藏所有Fragment*/
    public void hideAllFragment(FragmentTransaction transaction){
        for (Fragment fragment : fList) {
            if (fList.contains(fragment)) {
                transaction.hide(fragment);
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i("碎片：", "点击启动: ");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);//每次点击后，先把碎片隐藏

            switch (item.getItemId()) {
                case R.id.navigation_home://主页
                    showFragment(home,transaction);
                    return true;
                case R.id.navigation_dashboard://消息
                    showFragment(msg,transaction);
                    return true;
                case R.id.navigation_notifications://联系人
                    showFragment(connect,transaction);
                    return true;
                case R.id.navigation_settings://设置
                    showFragment(setting,transaction);
                    return true;
            }
            return false;
        }
    };
}