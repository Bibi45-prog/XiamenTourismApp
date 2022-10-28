package com.xiamenTourism.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.xiamenTourism.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class ViewImageActivity extends AppCompatActivity {
    int wallpaper;
    RelativeLayout relative_layout_main;
    PhotoView image_view_wallpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            wallpaper = bundle.getInt("keyimage");
        }


        relative_layout_main = findViewById(R.id.relative_layout_main);
        image_view_wallpaper = findViewById(R.id.image_view_wallpaper);


//        Implicit Intent
        findViewById(R.id.share_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),wallpaper);
                String path = getExternalCacheDir()+"/shareimage.jpg";
                java.io.OutputStream out = null;
                java.io.File file=new java.io.File(path);
                try { out = new java.io.FileOutputStream(file); bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); out.flush(); out.close(); } catch (Exception e) { e.printStackTrace(); } path=file.getPath();
//
                Uri uri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    uri = FileProvider.getUriForFile(ViewImageActivity.this,
                            "com.xiamenTourism.fileprovider", file);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    uri = Uri.fromFile(file);
                }
                shareIntent = new Intent(android.content.Intent.ACTION_SEND); shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri); shareIntent.setType("image/jpg");
                startActivity(Intent.createChooser(shareIntent,"Share with"));

            }
        });


        Glide.with(this).asBitmap().load(wallpaper).into(image_view_wallpaper);


    }


}