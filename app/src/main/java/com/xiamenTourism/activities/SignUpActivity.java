package com.xiamenTourism.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiamenTourism.MainActivity;
import com.xiamenTourism.R;
import com.xiamenTourism.database.TourismDatabase;
import com.xiamenTourism.model.TourismModel;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    TourismDatabase tourismDatabase;
    EditText user_name, email, phone_number, password;
    Button btn_sign_up;
    ArrayList<TourismModel> userDataArrayList;
    boolean isAlreadyRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        tourismDatabase = new TourismDatabase(this);
        tourismDatabase.OpenDatabase();
        user_name = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone_number = findViewById(R.id.phone_number);
        btn_sign_up = findViewById(R.id.sign_up);

// signUp button
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user_name.getText().toString();
                String userEmail = email.getText().toString();
                String userPhoneNumber = phone_number.getText().toString();
                String userPassword = password.getText().toString();


                if (tourismDatabase.IsDatabaseOpen()) {
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(SignUpActivity.this, "Enter your name", Toast.LENGTH_LONG).show();
                    } else if (TextUtils.isEmpty(userEmail)) {
                        Toast.makeText(SignUpActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(userPhoneNumber)) {
                        Toast.makeText(SignUpActivity.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(userPassword)) {
                        Toast.makeText(SignUpActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                    } else {
                        userDataArrayList = tourismDatabase.getUserData();
                        if (userDataArrayList!=null) {
                            for (int i = 0; i <= userDataArrayList.size() - 1; i++) {
                                if (userEmail.equals(userDataArrayList.get(i).Email)) {

                                    isAlreadyRegister = true;
                                    break;
                                }

//                            else if ( phone_number.equals(userDataArrayList.get(i).PhoneNumber)){
//                                Toast.makeText(SignUpActivity.this, "Phone number already Register", Toast.LENGTH_SHORT).show();
//
//                            }

                            }
                        }
                        if (isAlreadyRegister) {
                            Toast.makeText(SignUpActivity.this, "Email Already Register", Toast.LENGTH_SHORT).show();
                            isAlreadyRegister = false;
                        } else {
                            tourismDatabase.insertUserData(userName, userEmail, userPhoneNumber, userPassword);
                            Toast.makeText(SignUpActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }

                } else {
                    tourismDatabase.OpenDatabase();
                    if (tourismDatabase.IsDatabaseOpen()) {
                        if (TextUtils.isEmpty(userName)) {
                            Toast.makeText(SignUpActivity.this, "Enter your name", Toast.LENGTH_LONG).show();
                        } else if (TextUtils.isEmpty(userEmail)) {
                            Toast.makeText(SignUpActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(userPhoneNumber)) {
                            Toast.makeText(SignUpActivity.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(userPassword)) {
                            Toast.makeText(SignUpActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                        } else {
                            userDataArrayList = tourismDatabase.getUserData();
                            if (userDataArrayList != null) {
                                for (int i = 0; i <= userDataArrayList.size() - 1; i++) {
                                    if (userEmail.equals(userDataArrayList.get(i).Email)) {

                                        isAlreadyRegister = true;
                                        break;
                                    }


                                }
                            }
                            if (isAlreadyRegister) {
                                Toast.makeText(SignUpActivity.this, "Email Already Register", Toast.LENGTH_SHORT).show();
                                isAlreadyRegister = false;
                            } else {
                                tourismDatabase.insertUserData(userName, userEmail, userPhoneNumber, userPassword);
                                Toast.makeText(SignUpActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }

                    }
                }
            }
        });


    }
}