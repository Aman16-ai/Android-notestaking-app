package com.example.firenotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class showParticularNotes extends AppCompatActivity {

    EditText title_tv;
    EditText body_tv;
    ImageButton delete_btn;
    ImageButton update_btn;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_particular_notes);
        id = getIntent().getIntExtra("id",0);
        String title = getIntent().getStringExtra("TITLE");
        String body = getIntent().getStringExtra("BODY");
        title_tv = (EditText) findViewById(R.id.tv_title);
        body_tv = (EditText)findViewById(R.id.tv_body);
        delete_btn = (ImageButton)findViewById(R.id.delete_btn);
        update_btn = (ImageButton)findViewById(R.id.update_btn);

        title_tv.setText(title);
        body_tv.setText(body);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(showParticularNotes.this);
                builder.setTitle("Do you want to delete ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHandler handler = new DBHandler(getApplicationContext());
                        handler.deleteNote(id);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesModel model = new NotesModel();
                model.setId(id);
                model.setTitle(title_tv.getText().toString());
                model.setBody(body_tv.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(showParticularNotes.this);
                builder.setTitle("Do you want to update ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHandler handler = new DBHandler(getApplicationContext());
                        handler.updateNote(model);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        });



    }
}