package org.adroidtown.practiceapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by bomeeryu_c on 2017. 6. 23..
 */

public class MyVolley {
    private static MyVolley one;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private MyVolley (Context context){
        requestQueue = Volley.newRequestQueue(context);

    }

    public static MyVolley getInstance(Context context){
        if(one == null) {
            one = new MyVolley(context);
        }
        return one;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

}
