package org.adroidtown.practiceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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
            Toast.makeText(TimeAlertService.this,"지금은 "+time+"입니다",Toast.LENGTH_SHORT).show();
            return Service.START_STICKY;
//        }
//        else {
//            Log.d("bomee","alertService onStartCommand");
////            TimerThread timerThread = new TimerThread();
////            timerThread.start();
//            Calendar cal = Calendar.getInstance();
//            String time = String.format("%02d시 %02d분",
//                    cal.get(Calendar.HOUR_OF_DAY),
//                    cal.get(Calendar.MINUTE)
//            );
//            Toast.makeText(TimeAlertService.this,"지금은 "+time+"입니다",Toast.LENGTH_SHORT).show();
//        return super.onStartCommand(intent, flags, startId);
//        }
//        TimerThread timerThread = new TimerThread();
//            timerThread.start();
//        Toast.makeText(getApplicationContext(),"안녕하세요",Toast.LENGTH_SHORT).show();
//        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("bomee","alertService 크리에이트 확인");
        Toast.makeText(TimeAlertService.this,"서비스 실행",Toast.LENGTH_SHORT).show();
    }



    class TimerThread extends Thread {
        Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String time = msg.getData().getString("time");
                Toast.makeText(TimeAlertService.this,"지금은 "+time+"입니다",Toast.LENGTH_SHORT).show();
            }
        };
        @Override
        public void run() {
            Calendar cal = Calendar.getInstance();
                Message msg = new Message();
                Bundle bundle = new Bundle();
                String time = String.format("%02d시 %02d분",
                        cal.get(Calendar.HOUR_OF_DAY),
                        cal.get(Calendar.MINUTE)
                );
                bundle.putString("time",time);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
        }
    }
}
