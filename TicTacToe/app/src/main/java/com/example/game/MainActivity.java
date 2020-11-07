package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Boolean isPlayerOneTurn = true;
    Boolean isGameNotOver = true;
    Boolean isGameEven = true;
    int movesLeft = 9;
    int[] gameState = new int[9]; // [2, 2, 2] String[] string = new String[9] null
    int[][] winningState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int position = Integer.parseInt(counter.getTag().toString());

        if (gameState[position] == 0 && isGameNotOver) {

            movesLeft -= 1;
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            if (isPlayerOneTurn) {
                counter.setImageResource(R.drawable.o);
                isPlayerOneTurn = false;
                gameState[position] = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                isPlayerOneTurn = true;
                gameState[position] = 2;
            }

            result();
        }
    }

    public void result() {
        for (int[] winPossibility : winningState) {
            if (gameState[winPossibility[0]] == gameState[winPossibility[1]] && gameState[winPossibility[1]] == gameState[winPossibility[2]] && gameState[winPossibility[0]] != 0) {
                isGameEven = false;
                isGameNotOver = false;
                if (gameState[winPossibility[0]] == 1) {
                    Toast.makeText(getApplicationContext(), "Winner is Heart", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getApplicationContext(), "Winner is X", Toast.LENGTH_SHORT).show();
            }
        }
        if (movesLeft == 0 && isGameEven) Toast.makeText(getApplicationContext(), "Even Game", Toast.LENGTH_SHORT).show();
    }

    public void buttonClick(View view) {
        for (int i = 0; i < gameState.length; i ++){
            gameState[i] = 0;
        }
        isGameNotOver = true;
        movesLeft = 9;
        resetImage();
    }

    public void resetImage() {
        for (int i = 1; i <= 9; i ++) {
            int id = getResources().getIdentifier("imageView" + i, "id", getPackageName());
            ImageView image = (ImageView) findViewById(id);
            image.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}