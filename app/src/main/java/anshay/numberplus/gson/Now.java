package anshay.numberplus.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 实时气温的一些信息
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
