package anshay.numberplus.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import anshay.numberplus.R;


public class LocationActivity extends AppCompatActivity {

    public LocationManager locationManager;
    private TextView positionTextView;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        positionTextView = (TextView) findViewById(R.id.postion_text_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);//获取所有可用的位置提供器
        //传入True表示只有启用的位置提供器才会被返回
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = locationManager.GPS_PROVIDER;
            Log.i("log", "gps");
        } else if (providerList.contains(locationManager.NETWORK_PROVIDER)) {
            provider = locationManager.NETWORK_PROVIDER;
            Log.i("log", "network");
        } else {
            Toast.makeText(this, "no location provider to use", Toast.LENGTH_SHORT).show();
        }
//        List<String> permissionList = new ArrayList<>();
//        if (ContextCompat.checkSelfPermission(LocationActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//        if (ContextCompat.checkSelfPermission(LocationActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.READ_PHONE_STATE);
//        }
//        if (ContextCompat.checkSelfPermission(LocationActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (!permissionList.isEmpty()) {
//            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
//            ActivityCompat.requestPermissions(LocationActivity.this,permissions,1);
//        }else


        initposition();
    }

    private void initposition() {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("权限log", "没有权限");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.i("权限log", "拒绝声明");
                Toast.makeText(this, "u had rejected the request", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {//如果有权限，就直接进行事件处理
            Log.i("权限log", "有权限");
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                Log.i("权限log", "显示位置");
                showLocation(location);//自定义方法
            }
        }
    }

        public void OnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions,grantResults);
            switch (requestCode) {
                case 1:
                    if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        Log.i("权限log", "回调");
                        Toast.makeText(LocationActivity.this, "回调  ", Toast.LENGTH_SHORT).show();
                        initposition();
                    } else {
                        // Permission Denied
                        Toast.makeText(LocationActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                    return;
            }
        }

            /*把拿到的位置信息显示出来*/
           private void showLocation(Location location) {

               String currentPosition = "latitude is" + location.getLatitude() + "\n" + "longitude is" + location.getLongitude();
               positionTextView.setText(currentPosition);
           }
    }








