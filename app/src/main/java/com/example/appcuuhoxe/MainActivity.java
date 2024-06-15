package com.example.appcuuhoxe;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appcuuhoxe.databinding.ActivityMainBinding;
import com.example.appcuuhoxe.fragments.AccountFragment;
import com.example.appcuuhoxe.fragments.MapsFragment;
import com.example.appcuuhoxe.fragments.RecuseApplicationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BottomNavigationView bt_nav_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bt_nav_view = findViewById(R.id.bottomNavigationView);
        ReplaceFragment(new MapsFragment());
        bt_nav_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.locate){
                    ReplaceFragment(new MapsFragment());
                }
                if(item.getItemId() == R.id.driver){
                    ReplaceFragment(new RecuseApplicationFragment());
                }
                if(item.getItemId() == R.id.account){
                    ReplaceFragment(new AccountFragment());
                }
                return true;
            }
        });
    }
    private void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentmanager,fragment);
        fragmentTransaction.commit();
    }

}