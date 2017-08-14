package anshay.numberplus.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/31.
 */

public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    public Wind wind;

    @SerializedName("astro")
    public Sun sun;

    @SerializedName("cond")
    public More more;

    public class Sun {
        public String sr;//日出
        public String ss;//日落
    }

    public class Wind {
        public String dir;//风向
        public String sc;//风力
    }


    public class Temperature {
        public String max;
        public String min;
    }

    public class More {
        @SerializedName("txt_d")
        public String info1;
        @SerializedName("txt_n")
        public String info2;
        @SerializedName("code")
        public String iconNumb;
    }

}
