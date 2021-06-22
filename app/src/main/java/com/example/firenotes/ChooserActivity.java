package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooserActivity extends AppCompatActivity {
    Button passGenBtn;
    Button NotesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        passGenBtn = findViewById(R.id.PasswordGeneratorBtn);
        NotesBtn = findViewById(R.id.NotesBtn);
        NotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        passGenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getshared = getSharedPreferences("check",MODE_PRIVATE);
                if(getshared.getString("isDone","default").equals("Yes")) {
                    startActivity(new Intent(ChooserActivity.this,LoginPassGen.class));
                }
                else {
                    startActivity(new Intent(ChooserActivity.this,SetPassword.class));
                    SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isDone","Yes");
                    editor.apply();

                }
            }
        });
    }
}