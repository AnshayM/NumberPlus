package anshay.numberplus.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast>forecastList;

     /*因为JSON中有些字段可能不太适合直接作为java字段来命名，因此这里使用了
    @SerializedName注解的方式来让json字段和java字段建立映射关系*/
}
