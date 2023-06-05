package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.gazi.afetbildir.databinding.ActivityAboutUsBinding;

public class AboutUs extends AppCompatActivity
{
    ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about_us);

        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}