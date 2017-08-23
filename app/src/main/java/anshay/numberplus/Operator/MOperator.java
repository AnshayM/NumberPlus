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

    // 日记记录插入到数据库日记表中
    public void insert(WeatherBean bean) {
        ContentValues values = new ContentValues();
        try {
            values.put("city", bean.getCity());
            values.put("date", bean.getDate());
            values.put("max", bean.getMaxTemperature());
            values.put("min", bean.getMinTemperature());
            values.put("type1", bean.getWeatherType1());
            values.put("type2", bean.getWeatherType2());
            values.put("sunrise", bean.getSunRise());
            values.put("sunset", bean.getSunSet());
            values.put("dir", bean.getDir());
            values.put("sc", bean.getSc());
            values.put("nowtemperature", bean.getNowTemperature());
            values.put("typenow", bean.getWeatherTypeNow());
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

//    // 更新数据库天气表的单条数据
//    public void update(WeatherBean bean) {
//        ContentValues values = new ContentValues();
//        values.put("city", bean.getCity());
//        values.put("date", bean.getDate());
//        values.put("max", bean.getMaxTemperature());
//        values.put("min", bean.getMinTemperature());
//        values.put("type1", bean.getWeatherType1());
//        values.put("type2", bean.getWeatherType2());
//        values.put("sunrise", bean.getSunRise());
//        values.put("sunset", bean.getSunSet());
//        values.put("dir", bean.getDir());
//        values.put("sc", bean.getSc());
//        values.put("nowtemperature",bean.getNowTemperature());
//        values.put("typenow",bean.getWeatherTypeNow());
//        values.put("icon",bean.getIcon());
//        db = dbHelp.getWritableDatabase();
//        db.update(DatabaseOpenHelper.TableWeather, values, "id=?", new String[] { bean.getId() + "" });//根据id来修改
//        values.clear();
//        db.close();
//    }

    //     删除数据库日记表中指定日记编号日记记录
    public void delete(int id) {
        db = dbHelp.getWritableDatabase();
        try {
            try {
                db.delete(DatabaseOpenHelper.TableWeather, "id=?", new String[]{id + ""});
            } finally {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取日记表中所有日记记录
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
                    bean.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    bean.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    bean.setMinTemperature(cursor.getString(cursor.getColumnIndex("min")));
                    bean.setMaxTemperature(cursor.getString(cursor.getColumnIndex("max")));
                    bean.setWeatherType1(cursor.getString(cursor.getColumnIndex("type1")));
                    bean.setWeatherType2(cursor.getString(cursor.getColumnIndex("type2")));
                    bean.setSunRise(cursor.getString(cursor.getColumnIndex("sunrise")));
                    bean.setSunSet(cursor.getString(cursor.getColumnIndex("sunset")));
                    bean.setDir(cursor.getString(cursor.getColumnIndex("dir")));
                    bean.setSc(cursor.getString(cursor.getColumnIndex("sc")));

                    bean.setNowTemperature(cursor.getString(cursor.getColumnIndex("nowtemperature")));
                    bean.setWeatherTypeNow(cursor.getString(cursor.getColumnIndex("typenow")));
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

    /*获取日记表中指定时间段的所有日记记录*/
//    public ArrayList<WeatherBean> query(String a, String b) {
//        ArrayList<WeatherBean> list = new ArrayList<WeatherBean>();
//        WeatherBean bean = null;
//        db = dbHelp.getReadableDatabase();
//        Cursor cursor;
//        cursor = db.query(DatabaseOpenHelper.TableWeather, null, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            Log.i(TAG + "cursor", cursor.getString(3));
//            Log.i(TAG + "a", a);
//            Log.i(TAG + "b", b);
//            Log.i(TAG + "getCursor", cursor.getString(3));
//            Log.i(TAG + "getCursor2int", Integer.parseInt(cursor.getString(3)) + "");
//            Log.i(TAG + "a2int", Integer.parseInt(a) + "");
//            Log.i(TAG + "b2int", Integer.parseInt(b) + "");
//            if (Integer.parseInt(cursor.getString(3)) >= Integer.parseInt(a)
//                    && Integer.parseInt(cursor.getString(3)) <= Integer.parseInt(b)) {
//                Log.i(TAG, "这个是有用的!");
//                bean = new WeatherBean();
//                bean.setId(cursor.getInt(0));
//                bean.setDate(cursor.getString(cursor.getColumnIndex("date")));
//                bean.setCity(cursor.getString(cursor.getColumnIndex("city")));
//                bean.setMinTemperature(cursor.getString(cursor.getColumnIndex("min")));
//                bean.setMaxTemperature(cursor.getString(cursor.getColumnIndex("max")));
//                bean.setWeatherType1(cursor.getString(cursor.getColumnIndex("type1")));
//                bean.setWeatherType2(cursor.getString(cursor.getColumnIndex("type2")));
//                bean.setSunRise(cursor.getString(cursor.getColumnIndex("sunrise")));
//                bean.setSunSet(cursor.getString(cursor.getColumnIndex("sunset")));
//                bean.setDir(cursor.getString(cursor.getColumnIndex("dir")));
//                bean.setSc(cursor.getString(cursor.getColumnIndex("sc")));
//
//                bean.setNowTemperature(cursor.getString(cursor.getColumnIndex("nowtemperature")));
//                bean.setWeatherTypeNow(cursor.getString(cursor.getColumnIndex("typenow")));
//
//                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
//                list.add(bean);
//            }
//
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }

//    // 获取日记表中指定日记记录编号的日记记录
//    public ArrayList<WeatherBean> query(int id) {
//        ArrayList<WeatherBean> list = new ArrayList<WeatherBean>();
//        WeatherBean bean = null;
//        db = dbHelp.getReadableDatabase();
//        Cursor cursor;
//        cursor = db.query(DatabaseOpenHelper.TableWeather, null, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            if (cursor.getInt(0) == id) {
//                bean = new WeatherBean();
//                bean.setId(cursor.getInt(0));
//                bean.setDate(cursor.getString(cursor.getColumnIndex("date")));
//                bean.setCity(cursor.getString(cursor.getColumnIndex("city")));
//                bean.setMinTemperature(cursor.getString(cursor.getColumnIndex("min")));
//                bean.setMaxTemperature(cursor.getString(cursor.getColumnIndex("max")));
//                bean.setWeatherType1(cursor.getString(cursor.getColumnIndex("type1")));
//                bean.setWeatherType2(cursor.getString(cursor.getColumnIndex("type2")));
//                bean.setSunRise(cursor.getString(cursor.getColumnIndex("sunrise")));
//                bean.setSunSet(cursor.getString(cursor.getColumnIndex("sunset")));
//                bean.setDir(cursor.getString(cursor.getColumnIndex("dir")));
//                bean.setSc(cursor.getString(cursor.getColumnIndex("sc")));
//
//                bean.setNowTemperature(cursor.getString(cursor.getColumnIndex("nowtemperature")));
//                bean.setWeatherTypeNow(cursor.getString(cursor.getColumnIndex("typenow")));
//
//                bean.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
//                list.add(bean);
//            }
//
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
}


