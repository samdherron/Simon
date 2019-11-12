package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList playerSelectionPattern;
    ArrayList generatedSimonPattern;

    {
        playerSelectionPattern = new ArrayList();
        generatedSimonPattern = new ArrayList();
    }

    String[] colourValues = {"red", "green", "yellow", "blue"};
    Random randomColour = new Random();

    LinearLayout backgroundLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundLayout = findViewById(R.id.simonBackground);
        initialSetup();
    }


    public void initialSetup() {
        for (int i = 0; i < 10; i++) {
            String firstColour = generateNextPatternEntry();
            generatedSimonPattern.add(firstColour);
        }
    }

    public void flashPlayerEntry() {

        String currentColour = "";

        for (int i = 0; i <= playerSelectionPattern.size(); i++) {



            switch (currentColour) {

                case "red":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_redhit);
                    break;

                case "green":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_greenhit);
                    break;

                case "yellow":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_yellowhit);
                    break;

                case "blue":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_bluehit);
                    break;
            }

        }

    }

    public void flashSimonColour() {

        String currentColour = "";

        for (int i = 0; i < generatedSimonPattern.size(); i++) {

            SystemClock.sleep(500);

            currentColour = generatedSimonPattern.get(i).toString();

            switch (currentColour) {

                case "red":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_redhit);
                    break;

                case "green":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_greenhit);
                    break;

                case "yellow":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_yellowhit);
                    break;

                case "blue":
                    backgroundLayout.setBackgroundResource(R.drawable.simongamebackground_bluehit);
                    break;
            }


        }

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
