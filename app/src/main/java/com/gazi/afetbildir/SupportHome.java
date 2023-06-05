package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gazi.afetbildir.databinding.ActivitySupportHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class SupportHome extends AppCompatActivity
{
    ActivitySupportHomeBinding binding;

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),  ManagerHome.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivitySupportHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SQLiteDatabase MyDatabase = this.openOrCreateDatabase("AfetBildir", MODE_PRIVATE, null);
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS support" +
                "(NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Situation)");

        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM support", null);

        List<String> list = new ArrayList<>();
        while(cursor.moveToNext())
        {
            int NameAndSurnameIndex = cursor.getColumnIndex("NameAndSurname");
            list.add(cursor.getString(NameAndSurnameIndex));
        }
        cursor.close();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.listview_layout, list);

        binding.ListViewSupport.setAdapter(arrayAdapter);

        binding.ListViewSupport.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Cursor cursor = MyDatabase.rawQuery("SELECT * FROM support", null);

                int a = 0;
                while(cursor.moveToNext())
                {
                    if(a == position)
                    {
                        Intent intent = new Intent(getApplicationContext(),  SupportShow.class);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                    a++;
                }
            }
        });
    }
}