package anshay.numberplus;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public class Carousel extends FrameLayout {
    private static final int DELAY = 3000;
    private Context mContext;
    //存放图片的ImageView
    private List<View> views;
    //存放圆点的ImageView
    private List<ImageView> mDotsIV;
    //待显示图片的资源ID
    private int[] mDrawableIds = {R.mipmap.befor1, R.mipmap.befor2, R.mipmap.befor3, R.mipmap.befor4, R.mipmap.befor5};
    // 设置轮播长度为图片数量
    int len = mDrawableIds.length;
    private ViewPager myViewPager;
    private boolean isAutoPlay = true;
    private int currentItem = 0;

    private Handler mHandler = new Handler();

    public Carousel(Context context) {
        this(context, null);
    }

    public Carousel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Carousel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        views = new ArrayList<>();
        mDotsIV = new ArrayList<>();
        initContent();
    }

    /*初始化页面内容*/
    private void initContent() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.carousel_layout, this, true);
        myViewPager = (ViewPager) view.findViewById(R.id.view_pager);//找到viewpager控件
        LinearLayout mDotsLayout = (LinearLayout) view.findViewById(R.id.dots);//找到引导原点所在的线性布局
        /**/
        for (int i = 0; i < len; i++) {
            ImageView dotIV = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = params.rightMargin = 4;
            mDotsLayout.addView(dotIV, params);//把单个圆点图控件加到mDotsLayout布局中
            mDotsIV.add(dotIV);//向圆点图集合中添加圆点控件实例
            if (i == 0) {
                mDotsIV.get(i).setImageResource(R.drawable.dot_focus);
            } else {
                mDotsIV.get(i).setImageResource(R.drawable.dot_blur);
            }
        }
        /*添加轮播图片 */
        for (int i = 0; i < len; i++) {
            ImageView pageIV = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);//设置这个控件的宽高
            pageIV.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置图片为按比例缩放填满控件，居中显示
            pageIV.setImageResource(mDrawableIds[i]);//按照id设置轮播图片控件背景图
            views.add(pageIV);//向轮播图片集合中添加单个图片控件实例
        }


        myViewPager.setAdapter(new TopPagerAdapter());
        myViewPager.setFocusable(true);//设置可聚焦
        myViewPager.setCurrentItem(currentItem);//设置当前页面
        myViewPager.addOnPageChangeListener(new TopOnPageChangeListener());
        startCarousel();
    }

    private void startCarousel() {
        if (isAutoPlay) {//如果是自动滑动，才调用这个方法
            mHandler.postDelayed(task, DELAY);
        }
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = (currentItem + 1) % (views.size());
                myViewPager.setCurrentItem(currentItem);
                mHandler.postDelayed(task, DELAY);
            } else {
                mHandler.postDelayed(task, DELAY);//功能为隔一个延迟时间再检测一遍
            }
        }
    };

    class TopPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            return Integer.MAX_VALUE;
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            //     功能：该函数用来判断instantiateItem( )函数所返回来的Key与一个页面视图是否是代表的同一个视图
            // 返回值：如果对应的是同一个View，返回True，否则返回False。此处返回一个表达式，相同则为true，不同则为false
            // return arg0 == arg1;//根据传来的key，找到view,判断与传来的参数View arg0是不是同一个视图
//            return arg0 == views.get((int) Integer.parseInt(arg1.toString()));
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
//            return position;
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {//从容器中移除页面
            container.removeView((View) object);
        }

    }

    /*页面变化监听器*/
    class TopOnPageChangeListener implements ViewPager.OnPageChangeListener {
        //页面正在滑动时
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d("onPageScrolled", "页面正在滑动");
        }

        //页面被选择时
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mDotsIV.size(); i++) {
                if (i == position) {
                    mDotsIV.get(i).setImageResource(R.drawable.dot_focus);
                } else {
                    mDotsIV.get(i).setImageResource(R.drawable.dot_blur);
                }
            }
            Log.d("mine","当前位置是: "+ String.valueOf(position));
            currentItem = position;
        }

        //页面滑动状态
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                //SCROLL_STATE_DRAGGING
                case 1:
                    // 1 dragging（拖动），理解为：只要触发拖动/滑动事件时，则 state = ViewPager.SCROLL_STATE_DRAGGING
                    isAutoPlay = false;
                    Log.d("mine", "拖动:" + String.valueOf(currentItem));
                    break;
                //SCROLL_STATE_SETTLING
                case 2:
                    //2 settling(安放、定居、解决)，理解为：通过拖动/滑动，安放到了目标页，则 state = ViewPager.SCROLL_STATE_SETTLING
                    isAutoPlay = true;
//                    if (isAutoPlay) {
//                        if (currentItem == 0) {
//                            currentItem = 5;
//                            myViewPager.setCurrentItem(currentItem);
//                            break;
//                        }
//                        if (currentItem == 6) {
//                            currentItem = 1;
//                            myViewPager.setCurrentItem(currentItem);
//                            break;
//                        }
//                    } else {//手动滑动时
////                        TopPagerAdapter topPagerAdapter = new TopPagerAdapter();
////                        currentItem=topPagerAdapter.instantiateItem()
//                        if (currentItem == 0) {
//                            currentItem = 5;
//                            myViewPager.setCurrentItem(currentItem);
//                            break;
//                        }
//                        if (currentItem == 6) {
//                            currentItem = 1;
//                            myViewPager.setCurrentItem(currentItem);
//                            break;
//                        }
//                    }
//                    if (!isAutoPlay&&currentItem ==views.size()) {
//                        Log.d("滑动状态", "手动滑动安放前:"+String.valueOf(currentItem));
//                        currentItem = currentItem  % (views.size());
//                        myViewPager.setCurrentItem(currentItem);
//                        Log.d("滑动状态","手动滑动安放后:"+ String.valueOf(currentItem));
//                    }
//                    Log.d("滑动状态", "自动滑动安放:"+String.valueOf(currentItem));

                    break;

                case 0:
                    //0 idle(空闲，挂空挡)， 理解为：只要拖动/滑动结束，无论是否安放到了目标页，则 state = ViewPager.SCROLL_STATE_IDLE
//                    isAutoPlay = true;//这个时候开始自动滑动

                    Log.d("mine", "空档:" + String.valueOf(currentItem));
                    break;
                default:
                    break;
            }
        }
    }

}
