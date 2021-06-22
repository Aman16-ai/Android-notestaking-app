package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class UserPasswordGenerator extends AppCompatActivity {

    Button savebtn;
    Button passgenbtn;
    EditText titleet;
    EditText lenet;
    TextView tv;
    String password = "";
    int len;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_password_generator);
        savebtn = findViewById(R.id.btn_save_pass);
        passgenbtn = findViewById(R.id.btn_gen_pass);
        titleet = findViewById(R.id.et_pass_title);
        lenet = findViewById(R.id.et_pass_len);
        tv = findViewById(R.id.tv_passgen);

        passgenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = Integer.parseInt(lenet.getText().toString());
                title = titleet.getText().toString();
                if(!title.isEmpty()) {
                    GeneratePassword(len);

                }
                else {
                    Toast.makeText(UserPasswordGenerator.this, "title not entered", Toast.LENGTH_SHORT).show();
                }


            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordModel model = new PasswordModel();
                model.setTitle(title);
                model.setPassword(password);
                DBHandler handler = new DBHandler(getApplicationContext());
                handler.InsertPassword(model);
                startActivity(new Intent(getBaseContext(),PassGenManager.class));
            }
        });
    }

    private void GeneratePassword(int length) {

            Random random = new Random();
            String Allchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
            len = length;
            for(int i =0;i<len;i++) {
                password = password + Allchars.charAt(random.nextInt(71));
            }
            tv.setText("Your password : "+password);


    }
}