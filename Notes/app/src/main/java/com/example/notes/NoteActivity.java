package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        EditText noteActivity = findViewById(R.id.mainContent);
        EditText titleActivity = findViewById(R.id.title);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

//        if (noteId != -1) {
//            noteActivity.setText(MainActivity.notes.get(noteId));
//        } else {
//            noteId = MainActivity.notes.size() - 1;
////            Log.i("size is ", Integer.toString(MainActivity.notes.size()));
////            Log.i("id is ", Integer.toString(noteId));
//        }
        noteActivity.addTextChangedListener(new TextWatcher() {
            int checkState;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        titleActivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    MainActivity.notes.set(noteId, String.valueOf(s));
//                } else {
//                    MainActivity.notes.set(noteId, "New Note");
//                }
//                MainActivity.noteAdapter.notifyDataSetChanged();
                NoteInfo newNote = new NoteInfo(noteId, MainActivity.notes.get(noteId), "You update your title");
                MainActivity.noteDatabase.updateNote(newNote);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        titleActivity.setText(MainActivity.notes.get(noteId));
        //NoteInfo newNote = new NoteInfo(noteId, MainActivity.notes.get(noteId), "You update your title");
        //MainActivity.noteDatabase.updateNote(newNote);
    }
}