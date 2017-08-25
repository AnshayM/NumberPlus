package anshay.numberplus.Operator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import anshay.numberplus.Bean.WeatherBean;
import anshay.numberplus.OpenHelper.DatabaseOpenHelper;


/**
 * Created by Anshay on 2017/8/21.
 * Email: anshaym@163.com
 * 数据库管理器
 */

public class MOperator {
    String TAG = "Mine";// LogCat标签
    DatabaseOpenHelper dbHelp;// 数据库适配变量
    SQLiteDatabase db;// 数据库访问变量
    // 构造函数

    public MOperator(Context context) {
        this.dbHelp = new DatabaseOpenHelper(context, DatabaseOpenHelper.DBName);
    }

    // 天气数据插入到数据库TableWeather表中
    public void insert(WeatherBean bean) {
        ContentValues values = new ContentValues();
        try {
            values.put("myID", bean.getMyId());
            values.put("updateTime", bean.getUpdateTime());
            values.put("city", bean.getCity());
            values.put("date", bean.getDate());
            values.put("max", bean.getMaxTemperature());
            values.put("min", bean.getMinTemperature());
            values.put("typeDay", bean.getWeatherTypeDay());
            values.put("typeNight", bean.getWeatherTypeNight());
            values.put("sunrise", bean.getSunRise());
            values.put("sunset", bean.getSunSet());
            values.put("dir", bean.getDir());
            values.put("sc", bean.getSc());
            values.put("nowTemperature", bean.getNowTemperature());
            values.put("typeNow", bean.getWeatherTypeNow());
            values.put("icon", bean.getIcon());
            db = dbHelp.getWritableDatabase();
            db.insert(DatabaseOpenHelper.TableWeather, null, values);
            values.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    // 更新天气表的单条数据
    public void update(WeatherBean bean) {
        ContentValues values = new ContentValues();
        try {
            try {
                values.put("updateTime", bean.getUpdateTime());
                values.put("city", bean.getCity());
                values.put("date", bean.getDate());
                values.put("max", bean.getMaxTemperature());
                values.put("min", bean.getMinTemperature());
                values.put("typeDay", bean.getWeatherTypeDay());
                values.put("typeNight", bean.getWeatherTypeNight());
                values.put("sunrise", bean.getSunRise());
                values.put("sunset", bean.getSunSet());
                values.put("dir", bean.getDir());
                values.put("sc", bean.getSc());
                values.put("nowTemperature", bean.getNowTemperature());
                values.put("typeNow", bean.getWeatherTypeNow());
                values.put("icon", bean.getIcon());
                db = dbHelp.getWritableDatabase();
                db.update(DatabaseOpenHelper.TableWeather, values, "myId=?", new String[]{bean.getMyId() + ""});//根据myId来修改
                values.clear();
            } finally {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "更新单条数据" + bean.getMyId());
    }

    //   根据传入的myId来删除TableWeather表中的数据
    public void delete(int myId) {
        db = dbHelp.getWritableDatabase();
        try {
            try {
                db.delete(DatabaseOpenHelper.TableWeather, "myId=?", new String[]{myId + ""});
            } finally {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "删除单条数据" + myId);
    }

    // 获取数据库中TableWeather表所有数据，返回一个list集合
    public ArrayList<WeatherBean> queryAll() {
        Log.i(TAG + "query", "Start");
        ArrayList<WeatherBean> list = new ArrayList<WeatherBean>();
        WeatherBean bean = null;
        db = dbHelp.getReadableDatabase();
        Cursor cursor;
        cursor = db.query(DatabaseOpenHelper.TableWeather, null, null, null, null, null, null);
        try {
            try {
                while (cursor.moveToNext()) {
                    bean = new WeatherBean();
                    bean.setMyId(cursor.getInt(cursor.getColumnIndex("myId")));
                    bean.setUpdateTime(cursor.getString(cursor.getColumnIndex("updateTime")));
                    bean.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    bean.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    bean.setMinTemperature(cursor.getString(cursor.getColumnIndex("min")));
                    bean.setMaxTemperature(cursor.getString(cursor.getColumnIndex("max")));
                    bean.setWeatherTypeDay(cursor.getString(cursor.getColumnIndex("typeDay")));
                    bean.setWeatherTypeNight(cursor.getString(cursor.getColumnIndex("typeNight")));
                    bean.setSunRise(cursor.getString(cursor.getColumnIndex("sunrise")));
                    bean.setSunSet(cursor.getString(cursor.getColumnIndex("sunset")));
                    bean.setDir(cursor.getString(cursor.getColumnIndex("dir")));
                    bean.setSc(cursor.getString(cursor.getColumnIndex("sc")));

                    bean.setNowTemperature(cursor.getString(cursor.getColumnIndex("nowTemperature")));
                    bean.setWeatherTypeNow(cursor.getString(cursor.getColumnIndex("typeNow")));
                    bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                    Log.d(TAG, "这是第几个：" + cursor.getString(cursor.getColumnIndex("id")));

                    list.add(bean);
                }
            } finally {
                cursor.close();
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG + "queryAll", "完成数据库查询");
        return list;
    }

}


