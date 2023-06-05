package com.gazi.afetbildir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gazi.afetbildir.databinding.ActivityLoginBinding;

import java.util.Objects;

public class Login extends AppCompatActivity
{
    ActivityLoginBinding binding;

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),  Home.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SQLiteDatabase MyDatabase = this.openOrCreateDatabase("AafetBildir.db", MODE_PRIVATE, null);
        MyDatabase.execSQL("DROP TABLE IF EXISTS Admin");
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS Admin (email, password)");
        MyDatabase.execSQL("INSERT INTO Admin (email, password) VALUES ('admin@gmail.com', 12345) ");

        binding.ButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor cursor = MyDatabase.rawQuery("SELECT * FROM Admin", null);

                int emailIndex = cursor.getColumnIndex("email");
                int passwordIndex = cursor.getColumnIndex("password");

                String email = binding.EditTextEmail.getText().toString();
                String password = binding.EditTextPassword.getText().toString();

                if (email.equals("") || password.equals(""))
                {
                    Toast.makeText(Login.this, "Tüm Alanları Doldurmak Zorunludur!", Toast.LENGTH_SHORT).show();
                }

                while(cursor.moveToNext())
                {
                    if(Objects.equals(cursor.getString(emailIndex), email) && Objects.equals(cursor.getString(passwordIndex), password))
                    {
                        Toast.makeText(Login.this, "Giriş Başarılı.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),  ManagerHome.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Giriş Başarısız!", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
            }
        });
    }
}