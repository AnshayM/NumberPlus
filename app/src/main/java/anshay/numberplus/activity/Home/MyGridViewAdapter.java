package anshay.numberplus.activity.Home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.R;


/**
 * Created by Anshay on 2017/8/13.
 * gridView的适配器
 */

public class MyGridViewAdapter extends BaseAdapter {

    //定义信息的集合体
    private List<WeatherBean> list;
    //定义上下文
    private Context ctx;
    //动态加载Layout布局文件
    private LayoutInflater mInflater;

    //构造初始化
    public MyGridViewAdapter(Context context, List<WeatherBean> list) {
        //上下文，列表信息由调用者传递
        ctx = context;
        this.list = list;
        //动态加载的mInflater由调用者Activity获取
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    //Adapter的作用就是View界面与数据之间的桥梁，当列表里的每一项显示到页面时，都会调用Adapter的getView方法返回一个View。
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherBean entity = list.get(position);
        ViewHolder viewHolder = null;
        //View的每次创建是比较耗时的，因此对于getview方法传入的convertView应充分利用 != null的判断
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gridview_item, null);

            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.img);//图片
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.min = (TextView) convertView.findViewById(R.id.min);
            viewHolder.max = (TextView) convertView.findViewById(R.id.max);
            //View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
            //之后就用View.getTag()方法来获得ViewHolder对象。
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.date.setText(entity.getDate());
        viewHolder.type.setText(entity.getWeatherTypeDay());
        viewHolder.min.setText(entity.getMinTemperature());
        viewHolder.max.setText(entity.getMaxTemperature() + "℃");

        viewHolder.icon.setImageBitmap(entity.getMyBitMap());

        convertView.setBackgroundColor(Color.WHITE);
        return convertView;
    }

    static class ViewHolder {
        public ImageView icon;
        public TextView date;
        public TextView type;
        public TextView min;
        public TextView max;
    }
}


