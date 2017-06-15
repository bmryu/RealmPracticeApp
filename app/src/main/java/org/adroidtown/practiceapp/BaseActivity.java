package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bomeeryu_c on 2017. 5. 31..
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract int getContentView();
    public abstract void butterKnifeInject();
    public abstract void initViews();
    public abstract void setupListener();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        butterKnifeInject();
        initViews();
        setupListener();
    }
//    public void progressON(){
//        MyApplication.getInstance().progressON();
//    }
//    public void progressOFF(){
//        MyApplication.getInstance().progressOFF();
//    }
}
