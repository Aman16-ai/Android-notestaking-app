package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class UserPasswordGenerator extends AppCompatActivity {

    Button savebtn;
    Button passgenbtn;
    EditText titleet;
    EditText lenet;
    TextView tv;
    String password = "";
    int len;
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
                Random random = new Random();
                String Allchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
                len = Integer.parseInt(lenet.getText().toString());
                for(int i =0;i<len;i++) {
                    password = password + Allchars.charAt(random.nextInt(71));
                }
                tv.setText("Your password : "+password);
            }
        });
    }
}