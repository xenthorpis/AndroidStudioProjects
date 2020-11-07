package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final String NOTE_DATABASE = "noteDatabse";
    public static final String NOTE_ID = "noteID";
    public static final String NOTE_TITLE = "noteTitle";
    public static final String NOTE_CONTENT = "noteContent";

    public NoteDatabase(@Nullable Context context) {
        super(context, "noteDatabse.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + NOTE_DATABASE + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_TITLE + " String, " + NOTE_CONTENT + " CharSequence)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_DATABASE);
        onCreate(db);
    }

    public boolean insertNote(NoteInfo newNote) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTE_ID, newNote.getId());
        cv.put(NOTE_TITLE, newNote.getTitle());
        cv.put(NOTE_CONTENT, (String) newNote.getContent());
        db.insert(NOTE_DATABASE, null, cv);
        return true;
    }

    public boolean updateNote(NoteInfo latestNote) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTE_ID, latestNote.getId());
        cv.put(NOTE_TITLE, latestNote.getTitle());
        cv.put(NOTE_CONTENT, (String) latestNote.getContent());
        db.update(NOTE_DATABASE, cv, NOTE_ID + " = ? ", new String[] { NOTE_ID } );
        return true;
    }

}
