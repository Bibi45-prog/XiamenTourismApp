package com.xiamenTourism.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiamenTourism.R;
import com.xiamenTourism.activities.AttractionsDetialActivity;
import com.xiamenTourism.model.AttractionsModel;

import java.util.ArrayList;

public class AttractionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<AttractionsModel> mediaModels;

    public AttractionsAdapter(Context context, ArrayList<AttractionsModel> mediaModels) {
        this.context = context;
        this.mediaModels = mediaModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.attraction_model, null);
            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder.getItemViewType() == 1)
            return;

        if (holder instanceof MyViewHolder) {
            MyViewHolder holder1 = (MyViewHolder) holder;

            Glide.with(context).asBitmap().load(mediaModels.get(position).image1).into(holder1.image_1);

            holder1.name_1.setText(mediaModels.get(position).name1);


        }

    }

    @Override
    public int getItemCount() {
        if (mediaModels != null) {
            return mediaModels.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_1;
        TextView name_1;
RelativeLayout rl_main;
        public MyViewHolder(View itemView) {
            super(itemView);

            image_1 = itemView.findViewById(R.id.image);

            name_1 = itemView.findViewById(R.id.name);
            rl_main = itemView.findViewById(R.id.rl_main);


            rl_main.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i = new Intent(context, AttractionsDetialActivity.class);
        i.putExtra("name",mediaModels.get(getAdapterPosition()).name1);
        context.startActivity(i);
    }
});








        }

    }




}