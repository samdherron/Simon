package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    ArrayList playerSelectionPattern;
    ArrayList generatedSimonPattern;

    Handler imageChangeHandler;

    int currentIndex = 0;

    {
        playerSelectionPattern = new ArrayList();
        generatedSimonPattern = new ArrayList();
    }

    String[] colourValues = {"red", "green", "yellow", "blue"};
    Random randomColour = new Random();

    Random randomSleep = new Random();

    LinearLayout backgroundLayout;
    TextView currentColourDisplay;
    Button beginGame;
    MediaPlayer soundEffectPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundLayout = findViewById(R.id.simonBackground);
        currentColourDisplay = findViewById(R.id.currentColourDebug);
        beginGame = findViewById(R.id.beginGame);
        beginGame.setOnClickListener(this);
        newSimonColour();
        soundEffectPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hit_c);
    }

    @Override
    public void onClick(View view) {

        // Gets the id of the button that was clicked
        int id = view.getId();

        if (id == R.id.beginGame) {

            startGame();
        }

    }

    /*Calls method to generate next pattern entry,
      then adds it to the ArrayList */
    public void newSimonColour() {

            String colourAdding = generateNextPatternEntry();
            generatedSimonPattern.add(colourAdding);

    }

    /* Checks for a game over by iterating through the player
       entered pattern. If each index of their pattern
       doesn't match with the Simon pattern, game over. */
    public boolean lossCheck() {

        boolean gameOver = false;

        if (playerSelectionPattern.size() > 0) {
            for (int i = 0; i < playerSelectionPattern.size(); i++) {

                if (playerSelectionPattern.get(i) != generatedSimonPattern.get(i)) {
                    gameOver = true;
                }
            }
        }

        return gameOver;
    }

    public void triggerGameOver() {

    }


    public void startGame() {

        boolean gameOver_IncorrectSelection = lossCheck();

        if (!gameOver_IncorrectSelection) {

            long newSleepTime;

            imageChangeHandler = new Handler();

            MediaPlayer soundEffectPlayer;

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < generatedSimonPattern.size(); i++) {
                        currentIndex = i;


                        try {
                            Thread.sleep(700);
                            flashSimonColour();

                            if (currentIndex == (generatedSimonPattern.size() - 1)) {
                                Thread.sleep(500);
                                backgroundLayout.setBackgroundResource(R.drawable.simongamebackground);
                            }
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }).start();

        } else {

            triggerGameOver();

        }

    }

    public void flashPlayerEntry() {

        String currentColour = "";

            switch (currentColour) {

                case "red":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_redhit_v3);
                    break;

                case "green":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_greenhit_v3);
                    break;

                case "yellow":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_yellowhit_v3);
                    break;

                case "blue":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_bluehit_v3);
                    break;
            }

    }


    public void flashSimonColour() {

        int index = currentIndex;
        String currentColour = "";
        ToneGenerator beepSound = new ToneGenerator(AudioManager.STREAM_MUSIC, 175);

        currentColour = generatedSimonPattern.get(index).toString();


        switch (currentColour) {

            case "red":
                backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_redhit_v3);
                soundEffectPlayer.selectTrack(R.raw.hit_c);


                break;

            case "green":
                backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_greenhit_v3);
                soundEffectPlayer.selectTrack(R.raw.hit_g);
                break;

            case "yellow":
                backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_yellowhit_v3);
                soundEffectPlayer.selectTrack(R.raw.hit_chigh);
                break;

            case "blue":
                backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_bluehit_v3);
                soundEffectPlayer.selectTrack(R.raw.hit_e);
                break;
        }

        currentColourDisplay.setText("Current Colour: " + currentColour + ". ");

        soundEffectPlayer.start();



    }



    public String generateNextPatternEntry() {

        String nextColour = "";

        int randomNum = randomColour.nextInt(colourValues.length);
        randomColour.nextInt();

        nextColour = colourValues[randomNum];

        return nextColour;
    }

    public void Refresh_NewGame() {

        playerSelectionPattern.clear();
        generatedSimonPattern.clear();

    }


}
