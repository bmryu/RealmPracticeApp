package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by bomeeryu_c on 2017. 5. 8..
 */

public class LoginFragment2 extends Fragment {
    Button backButton;
    EditText editID;
    EditText editPW;
    Button loginBtn;
    //    LoginActivity loginActivity;
    OnBackBtnListener listener;
    OnGoToMainListener mainListener;
    public interface OnGoToMainListener {
        void onClick();
    }

    public interface  OnBackBtnListener {
        void onClick();
    }

    public void setOnBackBtnListener ( OnBackBtnListener btnListener) {
        this.listener = btnListener;
    }
    public void setOnGoToMainListener ( OnGoToMainListener goToMainListener) {
        this.mainListener = goToMainListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login2, container, false);
        backButton = (Button) rootView.findViewById(R.id.backButton);
        editID = (EditText) rootView.findViewById(R.id.editID);
        editPW = (EditText) rootView.findViewById(R.id.editPw);
        loginBtn = (Button) rootView.findViewById(R.id.loginButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editID.length() >= 8 && editID.length() <= 20) && (editPW.length() >= 8 && editPW.length() <= 16)) {
                   mainListener.onClick();
                } else {
                    listener.onClick();
                }
            }
        });
        return rootView;
    }
}
