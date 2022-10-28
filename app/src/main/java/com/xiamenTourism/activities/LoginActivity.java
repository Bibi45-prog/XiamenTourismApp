package com.xiamenTourism.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xiamenTourism.MainActivity;
import com.xiamenTourism.R;
import com.xiamenTourism.database.TourismDatabase;
import com.xiamenTourism.model.TourismModel;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TourismDatabase tourismDatabase;
    EditText email, password;
    Button btn_login;
    ArrayList<TourismModel> userDataArrayList;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tourismDatabase = new TourismDatabase(this);
tourismDatabase.OpenDatabase();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);
        userDataArrayList = new ArrayList<>();


        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


// login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmial = email.getText().toString();

                String userPassword = password.getText().toString();

                userDataArrayList = tourismDatabase.getUserData();

                if (tourismDatabase.IsDatabaseOpen()) {
                    if (email == null) {
                        Toast.makeText(LoginActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    } else if (password == null) {
                        Toast.makeText(LoginActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                    } else {
                        if (userDataArrayList != null) {

                            for (int i = 0; i <= userDataArrayList.size() - 1; i++) {
                                if (userEmial.equals(userDataArrayList.get(i).Email) && userPassword.equals(userDataArrayList.get(i).Password)) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                    editor.putString("isEmail", userDataArrayList.get(i).Email);
                                    editor.putBoolean("isAlreadyLogin", true);
                                    editor.apply();
                                    finish();
                                    return;
                                }
                                if (i == userDataArrayList.size() - 1) {
                                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }else {
                            Toast.makeText(LoginActivity.this, "Email not register", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    tourismDatabase.OpenDatabase();
                    if (tourismDatabase.IsDatabaseOpen()) {
                        if (email == null) {
                            Toast.makeText(LoginActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                        } else if (password == null) {
                            Toast.makeText(LoginActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                        } else {
                            if (userDataArrayList != null) {
                                for (int i = 0; i <= userDataArrayList.size() - 1; i++) {
                                    if (userEmial.equals(userDataArrayList.get(i).Email) && userPassword.equals(userDataArrayList.get(i).Password)) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                        editor.putString("isEmail", userDataArrayList.get(i).Email);
                                        editor.putBoolean("isAlreadyLogin", true);

                                        editor.apply();
                                        return;
                                    }
                                    if (i == userDataArrayList.size() - 1) {
                                        Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Email not register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });


    }
}