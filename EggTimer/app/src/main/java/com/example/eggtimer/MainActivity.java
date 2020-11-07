package com.example.eggtimer;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 3 variables
    SeekBar timerSeekBar;
    TextView timerTextView;
    Button timerButton;
    Boolean isActive = false;
    CountDownTimer countDownTimer;

    // reset
    public void reset() {
        isActive = false;
        timerButton.setText("START");
        timerSeekBar.setProgress(30);
        timerTextView.setText("00:30");
        countDownTimer.cancel();
    }

    public void buttonClick(View view) {
        if (isActive) {
            reset();
        }
        else {
            isActive = true;
            timerButton.setText("STOP");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTime( (int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();
                    reset();
                }
            }.start();
        }
    }

    // Time update
    public void updateTime(int currentTime) {
        int minutes = currentTime / 60;
        int seconds = currentTime - minutes * 60;

        // 60 > second > 9
        String Second = Integer.toString(seconds);

        // second <= 9
        if (seconds <= 9) {
            Second = "0" + Second;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + Second);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // update 3 variables through finding id
        timerSeekBar = findViewById(R.id.eggTimerSeekBar);
        timerTextView = findViewById(R.id.eggTimerTextView);
        timerButton = findViewById(R.id.startbutton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}