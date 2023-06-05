package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gazi.afetbildir.databinding.ActivitySupportBinding;

import java.util.ArrayList;
import java.util.List;

public class Support extends AppCompatActivity
{
    ActivitySupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<String> gender = new ArrayList<>();
        gender.add(0, "Cinsiyet");
        gender.add("Erkek");
        gender.add("Kadın");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, R.layout.color_spinner_layout, gender);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.SpinnerGender.setAdapter(dataAdapter);

        binding.SpinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (parent.getItemAtPosition(position).equals("Cinsiyet")) {}
                else
                {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), item + " Seçildi ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // TODO Auto-generated method stub
            }
        });

        SQLiteDatabase MyDatabase = this.openOrCreateDatabase("AfetBildir", MODE_PRIVATE, null);
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS support" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Situation)");

        binding.ButtonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String NameAndSurname = binding.EditTextNameAndSurname.getText().toString();
                String IdentificationNumber = binding.EditTextIdentificationNumber.getText().toString();
                String Gender = binding.SpinnerGender.getSelectedItem().toString();
                String Age = binding.EditTextAge.getText().toString();
                String PhoneNumber = binding.EditTextPhoneNumber.getText().toString();
                String Location = binding.EditTextLocation.getText().toString();
                String Situation = binding.EditTextSituation.getText().toString();

                if (NameAndSurname.equals("") || IdentificationNumber.equals("") || Gender.equals("Cinsiyet")
                        || Age.equals("") || PhoneNumber.equals("") || Location.equals("") || Situation.equals(""))
                {
                    Toast.makeText(Support.this, "Tüm Alanları Doldurmak Zorunludur!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyDatabase.execSQL("INSERT INTO support" +
                            "(NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Situation)" +
                            "VALUES ('"+NameAndSurname+"', '"+IdentificationNumber+"', '"+Gender+"', '"+Age+"', '"+PhoneNumber+"', '"+Location+"', '"+Situation+"') ");

                    Toast.makeText(Support.this, "Kaydedildi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}