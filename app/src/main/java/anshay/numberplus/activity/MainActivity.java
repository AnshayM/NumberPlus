package anshay.numberplus.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import anshay.numberplus.R;

public class MainActivity extends AppCompatActivity {

    private FragmentHome f1;
    private FragmentMsg f2;
    private FragmentConnect f3;
    private FragmentSetting f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setDefaultFragment();
    }

    //设置第一次加载时候的默认页为FragmentHome
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(f1==null){
            f1 = new FragmentHome();
            transaction.add(R.id.content,f1);
        }else{
            transaction.show(f1);
        }
        transaction.commit();//提交，页面进行变化
    }


    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
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
                case R.id.navigation_home:
                    if(f1==null){
                        f1 = new FragmentHome();
                        transaction.add(R.id.content,f1);
                    }else{
                        transaction.show(f1);
                    }
                    transaction.commit();//提交，页面进行变化
                    return true;
                case R.id.navigation_dashboard:
                    if(f2==null){
                        f2 = new FragmentMsg();
                        transaction.add(R.id.content,f2);
                    }else{
                        transaction.show(f2);
                    }
                    Log.d("主页点击","f2");
                    transaction.commit();//提交，页面进行变化
                    return true;
                case R.id.navigation_notifications:
                    if(f3==null){
                        f3 = new FragmentConnect();
                        transaction.add(R.id.content,f3);
                    }else{
                        transaction.show(f3);
                    }
                    transaction.commit();//提交，页面进行变化
                    Log.d("主页点击","f3");
                    return true;
                case R.id.navigation_settings:
                    if(f4==null){
                        f4 = new FragmentSetting();
                        transaction.add(R.id.content,f4);
                    }else{
                        transaction.show(f4);
                    }
                    transaction.commit();//提交，页面进行变化
                    return true;

            }
            return false;
        }
    };
}
