package org.adroidtown.practiceapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bomeeryu_c on 2017. 5. 8..
 */

public class LoginFragment1 extends Fragment {

    CustomButton kakaoBtn;
    CustomButton facebookBtn;
    CustomButton naverBtn;
    Context mContext;
    OnBtnListener listener;

    public interface OnBtnListener{
        void onClick();
    }
    public void setOnBtnListener(OnBtnListener btnListener){this.listener = btnListener;}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login1, container, false);

        kakaoBtn = (CustomButton) rootView.findViewById(R.id.kakaoButton);
        facebookBtn = (CustomButton) rootView.findViewById(R.id.facebookButton);
        naverBtn = (CustomButton) rootView.findViewById(R.id.naverButton);

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
        return rootView;
    }
}