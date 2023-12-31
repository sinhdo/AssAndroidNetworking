package com.example.assignmentandroidnetworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignmentandroidnetworking.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.assignmentandroidnetworking.databinding.ActivityMainBinding;
import com.example.assignmentandroidnetworking.fragment.CartFragment;
import com.example.assignmentandroidnetworking.fragment.HomeFragment;
import com.example.assignmentandroidnetworking.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment =new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.cart) {
                replaceFragment(new CartFragment());
            } else if (id == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment) {
         fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}