package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bomeeryu_c on 2017. 6. 22..
 */

public class WeatherActivity extends BaseActivity {

    @BindView(R.id.edit_city)
    EditText editCity;
    @BindView(R.id.edit_county)
    EditText editCounty;
    @BindView(R.id.edit_village)
    EditText editVillage;
    @BindView(R.id.text_result)
    TextView textResult;
    @BindView(R.id.text_sky)
    TextView textSky;
    @BindView(R.id.text_tmax)
    TextView textTmax;
    @BindView(R.id.text_tmin)
    TextView textTmin;
    @BindView(R.id.text_humidity)
    TextView textHumidity;
    @BindView(R.id.image_sky_state)
    ImageView imageSkyState;
    static String JSON_TAG = "JSON TEST";
    @BindView(R.id.text_tc)
    TextView textTc;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.bottom)
    BottomButton bottom;
    WeatherRepo weatherRepo;
    @Override
    public int getContentView() {
        return R.layout.activity_weather;
    }

    @Override
    public void butterKnifeInject() {
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {


    }

    @Override
    public void setupListener() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void volleyRequest() {
        RequestQueue queue = MyVolley.getInstance(this).getRequestQueue();

        String city = editCity.getText().toString();
        String county = editCounty.getText().toString();
        String village = editVillage.getText().toString();

        String appkey = "6cbd5ce4-f2f0-3836-819d-ed008fb0e4a0";

        Retrofit client = new Retrofit.Builder().baseUrl("http://apis.skplanetx.com/").addConverterFactory(GsonConverterFactory.create()).build();
        WeatherRepo.WeatherApiInterface service = client.create(WeatherRepo.WeatherApiInterface.class);
        Call<WeatherRepo> call = service.getWeatherRetrofit(1,"서교동","마포구","서울","6cbd5ce4-f2f0-3836-819d-ed008fb0e4a0");
        call.enqueue((new Callback<WeatherRepo>() {
            @Override
            public void onResponse(Call<WeatherRepo> call, Response<WeatherRepo> response) {
                if(response.isSuccessful()){
                    weatherRepo = response.body();
                    Log.d(JSON_TAG,response.raw().toString());
                    textSky.setText(weatherRepo.getWeather().getHourly().get(0).getSky().getName());
                    textHumidity.setText(weatherRepo.getWeather().getHourly().get(0).getHumidity());
                    textTmax.setText(weatherRepo.getWeather().getHourly().get(0).getTemperature().getTmax());
                    textTc.setText(weatherRepo.getWeather().getHourly().get(0).getTemperature().getTc());
                    textTmin.setText(weatherRepo.getWeather().getHourly().get(0).getTemperature().getTmin());
                    textResult.setText(weatherRepo.getWeather().getHourly().get(0).getTime());

                }
            }

            @Override
            public void onFailure(Call<WeatherRepo> call, Throwable t) {
                Log.d(JSON_TAG,"정보 불러오기 실패");
            }
        }));


//  String urlTest = "http://apis.skplanetx.com/weather/current/hourly?lon=&village=서교동&county=마포구&lat=&city=서울&version=1&appKey=" + appkey;
//        String urlTest = "http://apis.skplanetx.com/weather/current/hourly?lon=&village="+ village + "&county=" + county + "&lat=&city="+ city +"&version=1&appKey=" + appkey;
//
//
//        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, urlTest, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                JSONObject jsonObjectWeather = null;
//                String humidityObject;
//                String skyStatus;
//                String tc;
//                String tmax;
//                String tmin;
//                String currentTime;
//
//                try {
//                    Log.d(JSON_TAG, "받아온 전체 제이슨 : " + response.toString());
//                    jsonObjectWeather = response.getJSONObject("weather");
//                    JSONArray hourlyArray = jsonObjectWeather.getJSONArray("hourly");
//
//                    for (int i = 0; i < hourlyArray.length(); i++) { //hourly어레이의 길이만큼 포문을 돌리는데
//
//                        JSONObject skyObject = hourlyArray.getJSONObject(i).getJSONObject("sky");
//                        JSONObject temperatureObject = hourlyArray.getJSONObject(i).getJSONObject("temperature");
//
//                        currentTime = hourlyArray.getJSONObject(i).getString("timeRelease");
//                        humidityObject = hourlyArray.getJSONObject(i).getString("humidity");
//                        skyStatus = skyObject.getString("name");
//                        tc = temperatureObject.getString("tc");
//                        tmax = temperatureObject.getString("tmax");
//                        tmin = temperatureObject.getString("tmin");
//
//                        textResult.setText(currentTime);
//                        textSky.setText(skyStatus);
//                        textHumidity.setText(humidityObject);
//                        textTmax.setText(tmax);
//                        textTmin.setText(tmin);
//                        textTc.setText(tc);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(jsonObjRequest);
    }

    @OnClick(R.id.text_result)
    public void onViewClicked() {

        volleyRequest();

    }

}
