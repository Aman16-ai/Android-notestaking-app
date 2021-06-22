package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PassGenManager extends AppCompatActivity {


    RecyclerView recyclerView;
    List<PasswordModel> models;
    FloatingActionButton floatingbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_gen_manager);
        floatingbtn = findViewById(R.id.floatbtn);
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserPasswordGenerator.class));
            }
        });
        DBHandler handler = new DBHandler(getApplicationContext());
        models = handler.FetchPassword();
        for(PasswordModel model : models) {
            Log.d("pass", "onViewCreated: "+model.getId());
        }
        recyclerView = (RecyclerView)findViewById(R.id.passwordRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new PasswordAdapter(this,models));
    }
}