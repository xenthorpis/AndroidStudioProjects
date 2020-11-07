package com.example.storefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences file = this.getSharedPreferences("com.example.storefile", Context.MODE_PRIVATE);

        ArrayList<String> pets = new ArrayList<>();

        pets.add("Duck");
        pets.add("Dog");
        pets.add("Cat");

        try {
            file.edit().putString("pets", ObjectSerializer.serialize(pets)).apply();
            Log.i("my pets", file.getString("pets", ObjectSerializer.serialize(pets)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newPets = new ArrayList<>();
        try {
            newPets = (ArrayList<String>) ObjectSerializer.deserialize(file.getString("pets", ObjectSerializer.serialize(new ArrayList<String>())));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Log.i("New Pets", newPets.toString() );


//        file.edit().putString("username", "bozhou").apply();
//
//        String getWhat = file.getString("username", "");
//
//        Log.i("what is this: ", getWhat);
    }
}