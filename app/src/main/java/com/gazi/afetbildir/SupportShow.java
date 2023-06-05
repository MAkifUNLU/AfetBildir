package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gazi.afetbildir.databinding.ActivitySupportShowBinding;

public class SupportShow extends AppCompatActivity
{
    ActivitySupportShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivitySupportShowBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SQLiteDatabase MyDatabase = this.openOrCreateDatabase("AfetBildir", MODE_PRIVATE, null);
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS support" +
                "(NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Situation)");

        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM support", null);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        int a = 0;
        while(cursor.moveToNext())
        {
            if(a == position)
            {
                int NameAndSurnameIndex = cursor.getColumnIndex("NameAndSurname");
                int IdentificationNumberIndex = cursor.getColumnIndex("IdentificationNumber");
                int GenderIndex = cursor.getColumnIndex("Gender");
                int AgeIndex = cursor.getColumnIndex("Age");
                int PhoneNumberIndex = cursor.getColumnIndex("PhoneNumber");
                int LocationIndex = cursor.getColumnIndex("Location");
                int SituationIndex = cursor.getColumnIndex("Situation");

                binding.TextViewNameAndSurnameShow.setText(cursor.getString(NameAndSurnameIndex));
                binding.TextViewIdentificationNumberShow.setText(cursor.getString(IdentificationNumberIndex));
                binding.TextViewGenderShow.setText(cursor.getString(GenderIndex));
                binding.TextViewAgeShow.setText(cursor.getString(AgeIndex));
                binding.TextViewPhoneNumberShow.setText(cursor.getString(PhoneNumberIndex));
                binding.TextViewLocationShow.setText(cursor.getString(LocationIndex));
                binding.TextViewSituationShow.setText(cursor.getString(SituationIndex));
            }
            a++;
        }
        cursor.close();

        binding.ButtonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor cursor = MyDatabase.rawQuery("SELECT * FROM support", null);

                int a = 0;
                while(cursor.moveToNext())
                {
                    if (a == position)
                    {
                        int IDIndex = cursor.getColumnIndex("ID");
                        int ID = cursor.getInt(IDIndex);
                        MyDatabase.execSQL("DELETE FROM support WHERE ID == '"+ID+"'");

                        Intent intent = new Intent(getApplicationContext(),  SupportHome.class);
                        startActivity(intent);
                        cursor.close();

                        Toast.makeText(SupportShow.this, "Yardım Bildirildi.", Toast.LENGTH_SHORT).show();
                    }
                    a++;
                }
            }
        });
    }
}