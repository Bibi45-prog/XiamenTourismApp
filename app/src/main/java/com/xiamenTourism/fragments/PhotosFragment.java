package com.xiamenTourism.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiamenTourism.R;
import com.xiamenTourism.adapters.MediaAdapter;
import com.xiamenTourism.model.MediaModel;

import java.util.ArrayList;


public class PhotosFragment extends Fragment {
    Context context;

RecyclerView recyclerView;
    private ArrayList<MediaModel> mediaModels;

    public PhotosFragment() {
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

        View view= inflater.inflate(R.layout.fragment_photos, container, false);

        recyclerView = view.findViewById(R.id.recycler_View);

        mediaModels = new ArrayList<>();
        NearByPlaces();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MediaAdapter(context, mediaModels));


        return view;
    }

    private void NearByPlaces() {
        try {

            mediaModels.add(new MediaModel(R.drawable.image_1, "Demo", R.drawable.image_2, "Demo"));
            mediaModels.add(new MediaModel(R.drawable.image_3, "Demo", R.drawable.image_4, "Demo"));
            mediaModels.add(new MediaModel(R.drawable.image_5, "Demo", R.drawable.image_6, "Demo"));
            mediaModels.add(new MediaModel(R.drawable.image_7, "Demo", R.drawable.image_8, "Demo"));
            mediaModels.add(new MediaModel(R.drawable.image_9, "Demo", R.drawable.image_10, "Demo"));

        } catch (Exception ignored) {
        }
    }

}