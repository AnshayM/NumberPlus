package anshay.numberplus.activity.Setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anshay.numberplus.R;

/**
 * Created by Anshay on 2017/8/11.
 * 设置界面
 */

public class FragmentSetting extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        return view;
    }
}
