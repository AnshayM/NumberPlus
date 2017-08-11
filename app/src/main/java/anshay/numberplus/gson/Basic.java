package anshay.numberplus.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 地区名字和id
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
