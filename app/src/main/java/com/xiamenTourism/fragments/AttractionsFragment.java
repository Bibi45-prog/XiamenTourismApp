package com.xiamenTourism.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.xiamenTourism.R;
import com.xiamenTourism.adapters.AttractionsAdapter;
import com.xiamenTourism.model.AttractionsModel;
import com.xiamenTourism.model.AttractionsSearchModel;
import com.xiamenTourism.model.MediaModel;

import java.util.ArrayList;
import java.util.Arrays;


public class AttractionsFragment extends Fragment {

    Context context;
    AutoCompleteTextView autoCompleteTextView;
    private RecyclerView recyclerView;

    private ArrayList<AttractionsModel> attractionsModels;
    public AttractionsFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attractions, container, false);
        autoCompleteTextView = view.findViewById(R.id.auto_complete_text);
        recyclerView = view.findViewById(R.id.attraction_recycler);
        String[] attractions = {
                "Gulangyu Island",
                "Xiamen Botanical Garden",
                "Nanputuo Temple",
                "Xiamen University",
                "Kualagsu Huandao Road",
                "Zhongshan Road Walking Street",
                "Xiamen Riegu Hot Spring Resort"

        };






        attractionsModels = new ArrayList<>();
        NearByPlaces();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AttractionsAdapter(context, attractionsModels));

        LoadData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, attractions);

        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = Arrays.asList(attractions).indexOf(parent.getItemAtPosition(position).toString());

                recyclerView.scrollToPosition(pos);
            }
        });

        return view;
    }



    public ArrayList<AttractionsSearchModel> attractionsSearchModels;


    private void LoadData() {
        attractionsSearchModels = new ArrayList<>();

        attractionsSearchModels.add(new AttractionsSearchModel("Gulangyu Island"));
        attractionsSearchModels.add(new AttractionsSearchModel("Xiamen Botanical Garden"));
        attractionsSearchModels.add(new AttractionsSearchModel("Nanputuo Temple"));
        attractionsSearchModels.add(new AttractionsSearchModel("Xiamen University"));
        attractionsSearchModels.add(new AttractionsSearchModel("Kualagsu Huandao Road"));
        attractionsSearchModels.add(new AttractionsSearchModel("Zhongshan Road Walking Street"));
        attractionsSearchModels.add(new AttractionsSearchModel("Xiamen Riegu Hot Spring Resort"));



    }




    private void NearByPlaces() {
        try {

            attractionsModels.add(new AttractionsModel(R.drawable.attractions_1, "Gulangyu Island"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_2, "Xiamen Botanical Garden"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_3, "Nanputuo Temple"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_4, "Xiamen University"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_5, "Kualagsu Huandao Road"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_6, "Zhongshan Road Walking Street"));
            attractionsModels.add(new AttractionsModel(R.drawable.attractions_7, "Xiamen Riegu Hot Spring Resort"));

        } catch (Exception ignored) {
        }
    }
}