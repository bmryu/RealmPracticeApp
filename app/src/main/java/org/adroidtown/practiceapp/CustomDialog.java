package org.adroidtown.practiceapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bomeeryu_c on 2017. 5. 17..
 */

public class CustomDialog extends Dialog {
    TextView textView;
    ImageView dialogImage;
    TextView dialogText;
    TextView dialogTime;
    Intent intent;
    public CustomDialog(@NonNull Context context, Intent intent) {
        super(context);
        this.intent = intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_completepost);

        textView = (TextView)findViewById(R.id.textView);
        dialogImage = (ImageView)findViewById(R.id.dialogImage);
        dialogText = (TextView)findViewById(R.id.dialogText);
        dialogTime = (TextView)findViewById(R.id.dialogTime);
        if (intent.getExtras().getParcelable("path")!= null) {
            String text = intent.getExtras().getString("editText");
            Uri uriText = intent.getExtras().getParcelable("path");
            String time = intent.getExtras().getString("time");
            dialogTime.setText(time);
            dialogText.setText(text);
        //    Glide.with(getContext()).load(uriText).into(dialogImage);
        } else{
            String text = intent.getExtras().getString("editText");
            String uriText = intent.getExtras().getString("image"); //절대경로 받아오기
            //         Log.d("kk9991",uriText);
            String time = intent.getExtras().getString("time");
            dialogTime.setText(time);
            dialogText.setText(text);
        //    Glide.with(getContext()).load(uriText).into(dialogImage);
        }

    }

}
