package org.adroidtown.practiceapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by bomeeryu_c on 2017. 6. 23..
 */

public class WeatherRepo {

    @SerializedName("result")
    Result result;

    @SerializedName("weather")
    weather weather;

    public class Result {
        @SerializedName("message")
        String message;
        @SerializedName("code")
        String code;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }

    public class weather {

        public List<hourly> hourly = new ArrayList<>();

        public List<hourly> getHourly() {
            return hourly;
        }


        public class hourly {
            @SerializedName("sky")
            sky sky;
            @SerializedName("precipitation")
            precipitation precipitation;
            @SerializedName("temperature")
            temperature temperature;
            @SerializedName("humidity")
            String humidity;
            public String getHumidity() {return humidity;}
            @SerializedName("timeRelease")
            String timeRelease;
            public String getTime() {return timeRelease;}

            public class sky {
                @SerializedName("name")
                String name;
                @SerializedName("code")
                String code;

                public String getName() {
                    return name;
                }

                public String getCode() {
                    return code;
                }
            }

            public class precipitation {
                @SerializedName("sinceOntime")
                String sinceOntime;
                @SerializedName("type")
                String type;

                public String getSinceOntime() {
                    return sinceOntime;
                }

                public String getType() {
                    return type;
                }

            }

            public class temperature {
                @SerializedName("tc")
                String tc;
                @SerializedName("tmin")
                String tmin;
                @SerializedName("tmax")
                String tmax;

                public String getTc() {
                    return tc;
                }

                public String getTmin() {
                    return tmin;
                }

                public String getTmax() {
                    return tmax;
                }

            }
            public sky getSky() {
                return sky;
            }
            public temperature getTemperature(){
                return temperature;
            }
        }
    }
        public weather getWeather() {return weather;}

        public interface WeatherApiInterface {
            @GET("weather/current/hourly")
            Call<WeatherRepo> getWeatherRetrofit(        @Query("version") int version,
                                                         @Query("village")String village,
                                                         @Query("county") String county,
                                                         @Query("city") String city,
                                                         @Query("appKey") String appKey);
        }
    }


