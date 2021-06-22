package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class SetPassword extends AppCompatActivity {

    EditText et_pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        et_pass = findViewById(R.id.et_setPass);
        btn = findViewById(R.id.btn_sign);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PasswordSharedPreferences",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("MyPass",et_pass.getText().toString());
                editor.apply();
                Toast.makeText(SetPassword.this, "Password successfully created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SetPassword.this,PassGenManager.class));
                finish();
            }
        });

    }
}