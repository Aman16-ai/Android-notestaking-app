package com.example.firenotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

public class UserNotesInputActivity extends AppCompatActivity {

    EditText et_title;
    EditText et_body;
    Button  btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notes_input);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Add note");
        actionBar.setLogo(R.drawable.notes_logo_layout);
        et_title = (EditText)findViewById(R.id.input_title);
        et_body = (EditText)findViewById(R.id.input_body);
        btn_save = (Button)findViewById(R.id.input_save_btn);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String body = et_body.getText().toString();

                if(title.equals("") || title.equals(" ") || body.equals("")) {
                    Toast.makeText(UserNotesInputActivity.this, "Please write something", Toast.LENGTH_SHORT).show();
                }
                else {
                    NotesModel model = new NotesModel(title,body);
                    DBHandler handler = new DBHandler(getApplicationContext());
                    long i = handler.InsertNote(model);
                    if(i == -1) {

                        Toast.makeText(UserNotesInputActivity.this, "Notes not saved!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(UserNotesInputActivity.this, "Notes saved!", Toast.LENGTH_SHORT).show();
                        handler.notify();
                    }
                }
            }
        });
    }
}