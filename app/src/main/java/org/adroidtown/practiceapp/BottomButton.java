package org.adroidtown.practiceapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by bomeeryu_c on 2017. 5. 24..
 */

public class BottomButton extends LinearLayout implements View.OnClickListener{
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    public BottomButton(Context context) {
        super(context);
        init(context);
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_button,this,true);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        setFalse();
        v.setSelected(true);
    }

    public void setFalse() {
        button1.setSelected(false);
        button2.setSelected(false);
        button3.setSelected(false);
        button4.setSelected(false);
        button5.setSelected(false);
        button6.setSelected(false);
    }
}
