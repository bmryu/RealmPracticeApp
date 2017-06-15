package org.adroidtown.practiceapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

/**
 * Created by bomeeryu_c on 2017. 6. 15..
 */

public class BaseFragment extends Fragment {

   CustomProgressDialog customProgressDialog;
    Thread thread;

    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            customProgressDialog.dismiss(); // 다이얼로그 삭제
            // View갱신
        }
    };


    protected void progressON(Context context) {
        customProgressDialog = new CustomProgressDialog(getContext());
        customProgressDialog.show();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                handler.sendEmptyMessage(0);
                progressOFF();
            }
        });
        thread.start();

    }
    protected void progressOFF(){
        customProgressDialog.dismiss();
    }
}
