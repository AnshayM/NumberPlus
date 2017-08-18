package anshay.numberplus.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anshay.numberplus.R;

/**
 * Created by Anshay on 2017/8/11.
 *联系人界面
 */

public class FragmentConnect extends Fragment{
    private TextView mView;//页面介绍

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect,container,false);
        mView = (TextView)view.findViewById(R.id.fragmentConnect_tv);
        return view;
    }
}
