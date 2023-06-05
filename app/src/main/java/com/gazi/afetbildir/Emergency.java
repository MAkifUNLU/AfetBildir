package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gazi.afetbildir.databinding.ActivityEmergencyBinding;
import com.gazi.afetbildir.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class Emergency extends AppCompatActivity
{
    ActivityEmergencyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        binding = ActivityEmergencyBinding.inflate(getLayoutInflater());
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

        List<String> disaster = new ArrayList<>();
        disaster.add(0, "Maruz Kalınan Afet Türü");
        disaster.add("Sel");
        disaster.add("Çığ");
        disaster.add("Deprem");
        disaster.add("Yangın");
        disaster.add("Hortum");
        disaster.add("Fırtına");
        disaster.add("Kaya Düşmesi");
        disaster.add("Toprak Kayması");

        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter(this, R.layout.color_spinner_layout, disaster);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.SpinnerDisaster.setAdapter(dataAdapter2);

        binding.SpinnerDisaster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (parent.getItemAtPosition(position).equals("Maruz Kalınan Afet Türü")) {}
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
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS emergency" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Disaster, Situation)");

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
                String Disaster = binding.SpinnerDisaster.getSelectedItem().toString();
                String Situation = binding.EditTextSituation.getText().toString();

                if (NameAndSurname.equals("") || IdentificationNumber.equals("") || Gender.equals("Cinsiyet")
                        || Age.equals("") || PhoneNumber.equals("") || Location.equals("")
                        || Disaster.equals("Maruz Kalınan Afet Türü") || Situation.equals(""))
                {
                    Toast.makeText(Emergency.this, "Tüm Alanları Doldurmak Zorunludur!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyDatabase.execSQL("INSERT INTO emergency" +
                            "(NameAndSurname, IdentificationNumber, Gender, Age, PhoneNumber, Location, Disaster, Situation)" +
                            "VALUES ('"+NameAndSurname+"', '"+IdentificationNumber+"', '"+Gender+"', '"+Age+"', '"+PhoneNumber+"', '"+Location+"', '"+Disaster+"', '"+Situation+"') ");

                    Toast.makeText(Emergency.this, "Form Gönderildi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}