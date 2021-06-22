package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPassGen extends AppCompatActivity {

    EditText et_pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pass_gen);
        et_pass = findViewById(R.id.et_loginPass);
        btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PasswordSharedPreferences",MODE_PRIVATE);
                if(sharedPreferences.getString("MyPass","default").equals(et_pass.getText().toString())) {
                    Toast.makeText(LoginPassGen.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginPassGen.this,PassGenManager.class));
                    finish();
                }
            }
        });
    }
}