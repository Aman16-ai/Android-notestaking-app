package com.example.firenotes;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "notesTable";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";

    //For passwords.
    public static final String TABLE_NAME1 = "passTable";
    public static final String KEY_ID1 = "id";
    public static final String KEY_TITLE1 = "passtitle";
    public static final String KEY_PASS = "password";
    Context context;
    public DBHandler(@Nullable Context context) {
        super(context, "NotesData", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "create table notesTable(id integer primary key autoincrement,title Text,body Text)";
        String create1 = "create table passTable(id integer primary key autoincrement,passtitle Text,password Text)";
        db.execSQL(create);
        db.execSQL(create1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public long InsertNote(NotesModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,model.getTitle());
        values.put(KEY_BODY,model.getBody());;
        long i = db.insert(TABLE_NAME,null,values);
        return  i;
    }
    public List<NotesModel> ReadNotes() {
        SQLiteDatabase db =  this.getReadableDatabase();
        List<NotesModel> modelList = new ArrayList<>();
        String select  = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                NotesModel model = new NotesModel();
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setBody(cursor.getString(2));

                modelList.add(model);
            }while(cursor.moveToNext());
        }
        return modelList;
    }
    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }
    public void updateNote(NotesModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,model.getId());
        values.put(KEY_TITLE,model.getTitle());
        values.put(KEY_BODY,model.getBody());
        db.update(TABLE_NAME,values,"id = ?",new String[]{String.valueOf(model.getId())});
    }

    public void DeleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }

    public void InsertPassword(PasswordModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE1,model.getTitle());
        values.put(KEY_PASS,model.getPassword());
        long i = db.insert(TABLE_NAME1,null,values);
        if(i != -1) {
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
    public List<PasswordModel> FetchPassword() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<PasswordModel> models = new ArrayList<>();
        String select = "select * from "+TABLE_NAME1;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do{
                PasswordModel model = new PasswordModel();
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setPassword(cursor.getString(2));
                models.add(model);
            }while(cursor.moveToNext());
        }
        return models;
    }
    public void UpdatePassword(PasswordModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID1,model.getId());
        values.put(KEY_TITLE1,model.getTitle());
        values.put(KEY_PASS,model.getPassword());
        db.update(TABLE_NAME1,values,"id =?",new String[]{String.valueOf(model.getId())});
    }
    public void DeletePassword(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME1,KEY_ID1+"=?",new String[]{String.valueOf(id)});
    }


}
