package com.xiamenTourism.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiamenTourism.R;
import com.xiamenTourism.activities.ViewImageActivity;
import com.xiamenTourism.model.MediaModel;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<MediaModel> mediaModels;


    public MediaAdapter(Context context, ArrayList<MediaModel> mediaModels) {
        this.context = context;
        this.mediaModels = mediaModels;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.media_model, null);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder.getItemViewType() == 1)
            return;

        if (holder instanceof MyViewHolder) {
            MyViewHolder holder1 = (MyViewHolder) holder;

            Glide.with(context).asBitmap().load(mediaModels.get(position).image1).into(holder1.imageView);
            Glide.with(context).asBitmap().load(mediaModels.get(position).image2).into(holder1.imageView1);


            holder1.textView.setText(mediaModels.get(position).name1);
            holder1.textView1.setText(mediaModels.get(position).name2);


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
        ImageView imageView, imageView1;
        TextView textView, textView1;

        public MyViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image);
            imageView1 = itemView.findViewById(R.id.image1);


            textView = itemView.findViewById(R.id.name);
            textView1 = itemView.findViewById(R.id.name1);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWallpaper(mediaModels.get(getAdapterPosition()).image1);
                }
            });

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWallpaper(mediaModels.get(getAdapterPosition()).image2);
                }
            });


        }

        private void openWallpaper(int wallpaper) {
            Intent i = new Intent(context, ViewImageActivity.class);
            i.putExtra("keyimage", wallpaper);

            context.startActivity(i);


        }
    }


}