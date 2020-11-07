package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter noteAdapter;
    static NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SQL
        NoteDatabase noteDatabase = new NoteDatabase(this);
        //myDatabase.execSQL("CREATE ")

        // main screen
        ListView mainList = findViewById(R.id.listView);

        notes.add("Welcome To DICKNOTE");

        noteAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        mainList.setAdapter(noteAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.i("tag", "you click" + notes.get(position));
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });

        mainList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemToDelete = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                noteAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    // Menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addNote) {
//            notes.add("New Note");
//            noteAdapter.notifyDataSetChanged();
            NoteInfo newNote = new NoteInfo(-1, "New Note", "Example");
            notes.add(newNote.getTitle());
            noteAdapter.notifyDataSetChanged();
//            Toast.makeText(getApplicationContext(), newNote.toString(), Toast.LENGTH_SHORT).show();
            noteDatabase.insertNote(newNote);
//            Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
//            startActivity(intent);
            return true;
        }
        return false;
    }
}