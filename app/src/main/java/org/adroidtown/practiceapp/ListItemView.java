package org.adroidtown.practiceapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bomeeryu_c on 2017. 5. 10..
 */

public class ListItemView extends LinearLayout {
    ImageView imageView;
    TextView nameTextView;
    TextView contentTextView;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.name);
        contentTextView = (TextView) findViewById(R.id.content);

    }

    public ListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setResID(int resID) {
        imageView.setImageResource(resID);
    }

    public void setName(String name) {
        nameTextView.setText(name);

    }

    public void setContent(String content) {
        contentTextView.setText(content);
    }

}
