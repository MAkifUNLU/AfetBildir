package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.gazi.afetbildir.databinding.ActivityContactBinding;

public class Contact extends AppCompatActivity
{
    ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact);

        binding = ActivityContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ButtonCall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+90 530 799 64 01"));
                startActivity(intent);
            }
        });

        binding.ButtonMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("smsto:+90 530 799 64 01");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
            }
        });

        binding.ButtonMail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","afetbildir@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Afet Yardım");
                startActivity(Intent.createChooser(emailIntent, "Mesaj Gönderin..."));
            }
        });
    }
}