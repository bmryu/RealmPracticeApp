package org.adroidtown.practiceapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bomeeryu_c on 2017. 5. 17..
 */

public class DialogActivity extends Activity {
    ImageView dialogImage;
    TextView textView;
    TextView dialogText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("kk9991","DialogActicity 실행");
        LayoutInflater inflater = LayoutInflater.from(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(inflater.inflate(R.layout.dialog_completepost,null));
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.7);
        int height = (int) (display.getHeight() * 0.7);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        Intent intent = getIntent();

       textView = (TextView)findViewById(R.id.textView);
   //     dialogImage = (ImageView)findViewById(R.id.dialogImage);
        dialogText = (TextView)findViewById(R.id.dialogText);
        if (intent.getExtras().getParcelable("path")!= null) {
            String text = intent.getExtras().getString("editText");
            Uri uriText = intent.getExtras().getParcelable("path");
            dialogText.setText(text);
     //       Glide.with(this).load(uriText).override(100,100).into(dialogImage);
        } else{
            String text = intent.getExtras().getString("editText");
            String uriText = intent.getExtras().getString("image"); //절대경로 받아오기
   //         Log.d("kk9991",uriText);
            dialogText.setText(text);
     //       Glide.with(this).load(Uri.parse(uriText)).override(100,100).into(dialogImage);
        }

    }
}
