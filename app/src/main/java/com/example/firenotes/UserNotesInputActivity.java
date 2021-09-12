package com.example.firenotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.io.IOException;

public class UserNotesInputActivity extends AppCompatActivity {

    EditText et_title;
    EditText et_body;
    FloatingActionButton btn_save;
    FloatingActionButton btn_camera;
    Uri mUri;
    TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    private static final int PICK_IMAGE = 1;
    
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
        btn_save = (FloatingActionButton) findViewById(R.id.input_save_btn);
        btn_camera = (FloatingActionButton) findViewById(R.id.cameraBtn);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage();
            }
        });
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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }
            }
        });
    }
    private void PickImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE);
    }
    private void DetectText() {
        InputImage image;
        try {
            image = InputImage.fromFilePath(this,mUri);
            recognizer.process(image)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(@NonNull Text text) {
                            et_body.setText(text.getText());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserNotesInputActivity.this, "Note able to detect the text from image", Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            mUri = data.getData();
            DetectText();
        }
    }
}