package org.adroidtown.practiceapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by bomeeryu_c on 2017. 5. 17..
 */

public class Receiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("kk9991", "onReceive실행");
                String name = intent.getAction();
//        Intent intent1 = intent;
        if(name.equals("action1")){
            Intent dIntent = new Intent(context, DialogActivity.class);
            if(intent.getExtras().getParcelable("path")!=null){
                dIntent.putExtra("path",intent.getExtras().getParcelable("path"));
                dIntent.putExtra("editText",intent.getExtras().getString("editText"));
            } else {
                dIntent.putExtra("editText",intent.getExtras().getString("editText"));
                dIntent.putExtra("image",intent.getExtras().getString("image"));

            }


            PendingIntent pi = PendingIntent.getActivity(context,0, dIntent,PendingIntent.FLAG_ONE_SHOT);

            try{
                pi.send();
            }catch (Exception e){
                Log.d("kk9991","onReceive에서 pi.send() 오류");
            }
            }
            Log.d("kk9991", "onReceive실행");

//        Dialog dialog = new Dialog();
//        dialog.setTitle("테스트입니다");
//        dialog.show();
//            Log.d("kk9991", "인텐트 정보 들어왔는지 확인" + intent.getData().toString());
    }
}
