package com.xiamenTourism.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.xiamenTourism.MainActivity;
import com.xiamenTourism.R;
import com.xiamenTourism.database.TourismDatabase;


public class SplashActivity extends AppCompatActivity {
    boolean isAlreadyLogin;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TourismDatabase tourismDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

// first time open database
        tourismDatabase = new TourismDatabase(this);
        tourismDatabase.OpenDatabase();


        //shared preference

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        isAlreadyLogin = prefs.getBoolean("isAlreadyLogin", false);

        if (isAlreadyLogin) {
//            Explicit Intent
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }


        findViewById(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               Explicit Intent
                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Explicit Intent
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}