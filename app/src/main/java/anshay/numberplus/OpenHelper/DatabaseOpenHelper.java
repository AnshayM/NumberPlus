package anshay.numberplus.OpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anshay on 2017/8/21.
 * 创建数据库
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static int Ver = 1;// 数据库的版本号
    public static String TableWeather = "WeatherInfo";// 天气表
    public static String DBName = "WeatherDB";// 数据库名字
    // 多态构造函数
    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
    }

    // 多态构造函数
    public DatabaseOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    // 多态构造函数
    public DatabaseOpenHelper(Context context, String name) {
        this(context, name, Ver);
    }

    // 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create Table "
                + TableWeather
                + "(id integer primary key autoincrement," +
                "myId integer, " +
                "city varchar(100), date varchar(100)," +
                "max varchar(100), min varchar(100)," +
                "typeDay varchar(100),typeNight varchar(100)," +
                "typeNow varchar(100), nowTemperature varchar(100)," +
                "sunrise varchar(100), sunset varchar(100)," +
                "dir varchar(100), sc varchar(100)," +"icon varchar(100) )";
        db.execSQL(sql);

    }

    // 更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
