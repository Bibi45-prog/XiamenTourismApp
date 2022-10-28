package com.xiamenTourism;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.xiamenTourism.fragments.AccountFragment;
import com.xiamenTourism.fragments.AttractionsFragment;
import com.xiamenTourism.fragments.HomeFragment;
import com.xiamenTourism.fragments.LocationFragment;
import com.xiamenTourism.fragments.MediaFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fragment = new HomeFragment();
        loadFragment(fragment);
        toolbar.setTitle("Home");



//        bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        toolbar.setTitle("Home");
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_media:
                        fragment = new MediaFragment();
                        toolbar.setTitle("Media");
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_attractions:
                        fragment = new AttractionsFragment();
                        toolbar.setTitle("Attractions");
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_location:
                        fragment = new LocationFragment();
                        toolbar.setTitle("Location");
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_account:
                        fragment = new AccountFragment();
                        toolbar.setTitle("Account");
                        loadFragment(fragment);
                        return true;
                }


                return false;
            }

        });


    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}