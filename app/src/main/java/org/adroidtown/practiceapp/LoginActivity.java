package org.adroidtown.practiceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by bomeeryu_c on 2017. 5. 8..
 */

public class LoginActivity extends BaseActivity {
    LoginFragment1 loginFragment1;
    LoginFragment2 loginFragment2;
    FragmentManager fm;
    Context context;
     //4-2 4-1에 가져온 리스너가 메모리에 로딩됨

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
     }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void butterKnifeInject() {

    }

    @Override
    public void initViews() {

        context = this;
        loginFragment1 = new LoginFragment1();
        loginFragment2 = new LoginFragment2();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment1).commit();


    }

    @Override
    public void setupListener() {
        loginFragment1.setOnBtnListener(new LoginFragment1.OnBtnListener() {
            @Override
            public void onClick() {
                onFragmentChanged(true);
            }
        });

        loginFragment2.setOnBackBtnListener(new LoginFragment2.OnBackBtnListener() {
            @Override
            public void onClick() {
                onFragmentChanged(false);
            }
        });

        loginFragment2.setOnGoToMainListener(new LoginFragment2.OnGoToMainListener() {
            @Override
            public void onClick() {
                goToMainActivity(true);
            }
        });
    }


    private void onFragmentChanged(boolean goToNext) {
        if (goToNext == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment2).commit();
          //  Toast.makeText(getApplicationContext(), "onFragmentChanged 실행", Toast.LENGTH_LONG).show();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment1).commit();
            Toast.makeText(getApplicationContext(), "뒤로가기", Toast.LENGTH_LONG).show();
        }
    }

    private void goToMainActivity(boolean check) {
        if (check) {
          //  Toast.makeText(this, "gotoMainActivity들어옴", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "실행할 수 없음", Toast.LENGTH_LONG).show();
        }
    }

}
