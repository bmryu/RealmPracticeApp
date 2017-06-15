package org.adroidtown.practiceapp;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by bomeeryu_c on 2017. 6. 2..
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
     //   Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);


    }


    public void progressON (Activity activity){
        mProgressDialog = ProgressDialog.show(activity,"","잠시만 기다려주세요");
    }
    public void progressOFF(){
    if(mProgressDialog != null && mProgressDialog.isShowing()){
        mProgressDialog.dismiss();
    }
    }
}
