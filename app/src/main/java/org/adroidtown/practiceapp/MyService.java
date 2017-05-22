package org.adroidtown.practiceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by bomeeryu_c on 2017. 5. 18..
 */

public class MyService extends Service {

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service123","onCreate");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Log.d("Service123","서비스 null");
            return Service.START_STICKY;
        } else {

            processCommand(intent);
            Log.d("Service123","processCommand()");
        }

        return super.onStartCommand(intent, flags, startId);

    }

    private void processCommand(Intent intent) {
        Log.d("Service123","processCommand");
        Calendar cal = Calendar.getInstance();
        String time = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                                    cal.get(Calendar.YEAR),
                                    (cal.get(Calendar.MONTH) + 1),
                                    cal.get(Calendar.DAY_OF_MONTH),

                                    cal.get(Calendar.HOUR_OF_DAY),
                                    cal.get(Calendar.MINUTE),
                                    cal.get(Calendar.SECOND)
                                     );
        Intent goToMainIntent = new Intent (getApplicationContext(), MainActivity.class);
        goToMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        goToMainIntent.putExtra("time", time);

        if(intent.getExtras().getParcelable("path")!=null){
            goToMainIntent.putExtra("path",intent.getExtras().getParcelable("path"));
           goToMainIntent.putExtra("editText",intent.getExtras().getString("editText"));
        } else {
            goToMainIntent.putExtra("editText",intent.getExtras().getString("editText"));
           goToMainIntent.putExtra("image",intent.getExtras().getString("image"));

        }

        startActivity(goToMainIntent);
    }


}
