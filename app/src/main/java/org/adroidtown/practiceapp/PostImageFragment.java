package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by bomeeryu_c on 2017. 5. 15..
 */

public class PostImageFragment extends Fragment {
    ImageView imageView;
    EditText editText;
    Button completeButton;
    OnImageClickListener imageClickListener;
    OnPostBtnListener postBtnListener;
    public interface OnImageClickListener {
        void onClick();
    }
    public interface OnPostBtnListener {
        void onClick();
    }

    public void setOnImageClickListener (OnImageClickListener imageClickListener) {this.imageClickListener = imageClickListener;}
    public void setOnPostBtnListener (OnPostBtnListener postBtnListener) {this.postBtnListener = postBtnListener;}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_post_image,container,false);

        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        editText = (EditText)rootView.findViewById(R.id.editText);
        completeButton = (Button)rootView.findViewById(R.id.postBtn);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickListener.onClick();
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBtnListener.onClick();
            }
        });

        return rootView;
    }
}
