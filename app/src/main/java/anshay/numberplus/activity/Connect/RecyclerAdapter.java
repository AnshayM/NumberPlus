package anshay.numberplus.activity.Connect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.R;

/**
 * Created by Anshay on 2017/9/26.
 * Email: anshaym@163.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<WeatherBean> mWeatherList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView date;
        public TextView type;
        public TextView min;
        public TextView max;

        public ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            icon = (ImageView) view.findViewById(R.id.img);//图片
            type = (TextView) view.findViewById(R.id.type);
            min = (TextView) view.findViewById(R.id.min);
            max = (TextView) view.findViewById(R.id.max);
        }
    }

    public RecyclerAdapter(List<WeatherBean> weatherList) {
        mWeatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.gridview_item, parent, false);//复用girdview_item子布局
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        WeatherBean entity = mWeatherList.get(position);

        viewHolder.date.setText(entity.getDate());
        viewHolder.type.setText(entity.getWeatherTypeDay());
        viewHolder.min.setText(entity.getMinTemperature());
        viewHolder.max.setText(entity.getMaxTemperature() + "℃");
        viewHolder.icon.setImageBitmap(entity.getMyBitMap());
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }
}
