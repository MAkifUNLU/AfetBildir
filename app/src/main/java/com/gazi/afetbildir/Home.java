package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;

import com.gazi.afetbildir.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity
{
    ActivityHomeBinding binding;

    int counter = 0;
    @Override
    public void onBackPressed()
    {
        counter++;
        if (counter == 2)
        {
            this.finishAffinity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ButtonEmergency.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Emergency.class);
                startActivity(intent);
            }
        });

        binding.ButtonSupport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Support.class);
                startActivity(intent);
            }
        });

        binding.ButtonContact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);
            }
        });

        binding.ButtonAboutUs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });

        binding.ButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        binding.ButtonAlert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 10000);
                toneGen1.startTone(ToneGenerator.TONE_DTMF_1,2500);
            }
        });
    }
}