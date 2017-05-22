package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by bomeeryu_c on 2017. 5. 11..
 */

public class WritePostFragment extends Fragment {
    Button backButton;
    Button postButton;
    EditText editText;
    OnPostCompleteListener pListener;
    public interface  OnPostCompleteListener {
       void onClick();
    }
    public void setOnPostCompleteListener (OnPostCompleteListener pListener){
        this.pListener = pListener;
    }

    OnBackBtnListener bListener;
    public interface OnBackBtnListener {
        void onClick();
    }
    public void setOnBackBtnListener (OnBackBtnListener bListener){
        this.bListener = bListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_write,container,false);

        backButton = (Button)rootView.findViewById(R.id.backButton);
        postButton = (Button)rootView.findViewById(R.id.writeBtn);
        editText = (EditText)rootView.findViewById(R.id.editText);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length() >= 20){

                    Toast.makeText(getActivity(),"글쓰기 완료",Toast.LENGTH_LONG).show();
                    pListener.onClick();
                } else {
                    Toast.makeText(getActivity(),"20자 이상 작성해주세요",Toast.LENGTH_LONG).show();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bListener.onClick();
            }
        });
        return rootView;
    }

}
