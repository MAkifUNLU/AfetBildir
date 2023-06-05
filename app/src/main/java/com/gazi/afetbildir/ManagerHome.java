package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gazi.afetbildir.databinding.ActivityManagerHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class ManagerHome extends AppCompatActivity
{
    ActivityManagerHomeBinding binding;

    int counter = 0;
    @Override
    public void onBackPressed()
    {
        counter++;
        if (counter == 2)
        {
            Intent intent = new Intent(getApplicationContext(),  Login.class);
            startActivity(intent);

            Toast.makeText(this, "Sistemden Çıkış Yapıldı.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityManagerHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ButtonEmergency.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),  EmergencyHome.class);
                startActivity(intent);
            }
        });

        binding.ButtonSupport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),  SupportHome.class);
                startActivity(intent);
            }
        });

        binding.ButtonExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),  Login.class);
                startActivity(intent);

                Toast.makeText(ManagerHome.this, "Sistemden Çıkış Yapıldı.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}