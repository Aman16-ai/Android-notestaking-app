package com.example.firenotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdatePassword extends AppCompatActivity {
    
    EditText ettitle;
    EditText etPass;
    ImageButton deletebtn;
    ImageButton updatebtn;
    int id;
    String title;
    String pass_text;
    DBHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        handler  = new DBHandler(getApplicationContext());
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        title  =intent.getStringExtra("title");
        pass_text = intent.getStringExtra("pass");

        ettitle = findViewById(R.id.tv_pass_title);
        etPass = findViewById(R.id.tv_pass_len);
        deletebtn = findViewById(R.id.pass_delete_btn);
        updatebtn = findViewById(R.id.pass_update_btn);

        ettitle.setText(title);
        etPass.setText(pass_text);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmUpdate();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDelete();
            }
        });

    }
    public void ConfirmUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to update ?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PasswordModel model = new PasswordModel();
                model.setId(id);
                model.setTitle(ettitle.getText().toString());
                model.setPassword(etPass.getText().toString());
                handler.UpdatePassword(model);
                startActivity(new Intent(getApplicationContext(),PassGenManager.class));
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void ConfirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.DeletePassword(id);
                startActivity(new Intent(getApplicationContext(),PassGenManager.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}