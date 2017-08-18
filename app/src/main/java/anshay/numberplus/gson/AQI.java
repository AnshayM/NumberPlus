package anshay.numberplus.gson;

/**
 * 天气的一些属性，pm2.5和aqi
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;

    }
}
