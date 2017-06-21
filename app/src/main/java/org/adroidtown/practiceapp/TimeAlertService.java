package org.adroidtown.practiceapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by bomeeryu_c on 2017. 6. 20..
 */

public class TimeAlertService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if(intent == null) {
//
//            Log.d("bomee","alertService onStartCommand -> intent null");
        Calendar cal = Calendar.getInstance();
        String time = String.format("%02d시 %02d분",
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE)
        );
        Toast.makeText(TimeAlertService.this, "지금은 " + time + "입니다", Toast.LENGTH_SHORT).show();


        return Service.START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Bomee", "alertService 크리에이트 확인");
        Toast.makeText(TimeAlertService.this, "서비스 실행", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction("timetime");
        PendingIntent sender = PendingIntent.getService(this, 555, intent, 0);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, cal.getTime().getHours());

        switch (cal.get(Calendar.MINUTE) % 5) {
            case 0:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                break;
            case 1:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 4);
                break;
            case 2:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 3);
                break;
            case 3:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 2);
                break;
            case 4:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 1);
                break;
        }

        Log.d("bomee", "setAlarm확인 : " + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND));
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, sender);
        Log.d("bomee", "setAlarm확인 : " + am);
        Log.d("bomee", "sender 확인 : " + sender);
    }

}
