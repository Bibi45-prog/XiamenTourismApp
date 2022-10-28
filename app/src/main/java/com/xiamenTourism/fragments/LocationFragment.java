package com.xiamenTourism.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xiamenTourism.R;

public class LocationFragment extends Fragment {

    Context context;
    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_location, container, false);

        Button locate_on_map=view.findViewById(R.id.locate_on_map);
        locate_on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri IntentUri;
                IntentUri = Uri.parse("geo:0,0?q=" + "Xiamen");

                Intent mIntent = new Intent(Intent.ACTION_VIEW, IntentUri);
                mIntent.setPackage("com.google.android.apps.maps");
                if (mIntent.resolveActivity(context.getPackageManager()) != null) {
                    startActivity(mIntent);
                }
            }
        });
        return view;
    }
}