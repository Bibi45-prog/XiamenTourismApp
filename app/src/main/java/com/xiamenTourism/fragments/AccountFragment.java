package com.xiamenTourism.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiamenTourism.MainActivity;
import com.xiamenTourism.R;
import com.xiamenTourism.activities.SplashActivity;
import com.xiamenTourism.database.TourismDatabase;
import com.xiamenTourism.model.TourismModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class AccountFragment extends Fragment {

    EditText edt_profile_name, edt_profile_password, edt_profile_phone_number;
    TextView profile_email, profile_name, profile_password, profile_phone_number;

    TourismDatabase tourismDatabase;
    Button btn_update_profile, btn_done, btn_logout, btn_delete;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    ArrayList<TourismModel> userDataArrayList;
    Context context;
    LinearLayout layout_edit_profile, layout_profile;
    SharedPreferences.Editor editor;
    int userID;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tourismDatabase = new TourismDatabase(context);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        profile_email = view.findViewById(R.id.profile_email);
        profile_name = view.findViewById(R.id.profile_name);
        profile_password = view.findViewById(R.id.profile_password);
        profile_phone_number = view.findViewById(R.id.profile_phone_number);
        btn_update_profile = view.findViewById(R.id.btn_update_profile);
        edt_profile_name = view.findViewById(R.id.edt_profile_name);
        edt_profile_password = view.findViewById(R.id.edt_profile_password);
        edt_profile_phone_number = view.findViewById(R.id.edt_profile_phone_number);
        layout_profile = view.findViewById(R.id.layout_profile);
        layout_edit_profile = view.findViewById(R.id.layout_edit_profile);
        btn_done = view.findViewById(R.id.btn_done);
        btn_delete= view.findViewById(R.id.btn_delete);
        userDataArrayList = tourismDatabase.getUserData();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String isEmail = prefs.getString("isEmail", "");


        if (userDataArrayList != null) {
            for (int i = 0; i <= userDataArrayList.size() - 1; i++) {
                if (isEmail.equals(userDataArrayList.get(i).Email)) {

                    userID = userDataArrayList.get(i).Id;
                    profile_name.setText(userDataArrayList.get(i).UserName);
                    profile_email.setText(userDataArrayList.get(i).Email);
                    profile_password.setText(userDataArrayList.get(i).Password);
                    profile_phone_number.setText(userDataArrayList.get(i).PhoneNumber);
                    edt_profile_name.setText(userDataArrayList.get(i).UserName);
                    edt_profile_password.setText(userDataArrayList.get(i).Password);
                    edt_profile_phone_number.setText(userDataArrayList.get(i).PhoneNumber);
                    break;
                }

            }
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }


//        logout button
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("isAlreadyLogin", false);
                editor.apply();
                startActivity(intent);
            }
        });

        //btn edit profile
        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_edit_profile.setVisibility(View.VISIBLE);
                layout_profile.setVisibility(View.GONE);
                btn_done.setVisibility(View.VISIBLE);
                btn_logout.setVisibility(View.GONE);btn_delete.setVisibility(View.GONE);
                btn_update_profile.setVisibility(View.GONE);

            }
        });
//btn update profile
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tourismDatabase.updateDatabase(userID, edt_profile_name.getText().toString(), edt_profile_password.getText().toString(), edt_profile_phone_number.getText().toString());
Intent intent =new Intent(context, MainActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
startActivity(intent);
            }
        });


        //btn delete account
        btn_delete .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete account?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                tourismDatabase.DeletePlaceById(userID);
                                Intent intent =new Intent(context, SplashActivity.class);

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putBoolean("isAlreadyLogin", false);
                                editor.apply();
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        return view;
    }
}