package anshay.numberplus.activity.Msg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anshay.numberplus.R;

/**
 * Created by Anshay on 2017/8/11.
 * 消息界面
 */

public class FragmentMsg extends Fragment{
    private TextView mtv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg,container,false);

        mtv = (TextView)view.findViewById(R.id.fragmentmsg_tv);
        Log.d("碎片：", "onCreateView2: ");
        return view;
    }
}
