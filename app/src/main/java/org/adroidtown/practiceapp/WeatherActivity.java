package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    public void vollyRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String city = editCity.getText().toString();
        String county = editCounty.getText().toString();
        String village = editVillage.getText().toString();
        String appkey = "6cbd5ce4-f2f0-3836-819d-ed008fb0e4a0";
        String url = "http://apis.skplanetx.com/weather/current/hourly?version1=&lat=&lon=&city=" + city + "&county=" + county + "&village=" + village + "&appKey=" + appkey;
        String urlTest = "http://apis.skplanetx.com/weather/current/hourly?lon=&village=서교동&county=마포구&lat=&city=서울&version=1&appKey="+appkey;
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlTest, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("Network test",response);
//                response.
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Network test",error.toString());
//            }
//        });
//        queue.add(stringRequest);
//

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, urlTest, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Network test",response.toString());
                    textHumidity.setText(response.getString("humidity")) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjRequest);

    }

    @OnClick(R.id.content)
    public void onViewClicked() {
        vollyRequest();
    }
}
