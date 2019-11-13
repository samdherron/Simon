package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity
        implements View.OnClickListener {

    Button btnStart;
    Intent gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        gameScreen = new Intent(getApplicationContext(), MainActivity.class);
        btnStart = findViewById(R.id.btnStartGame);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        new Thread(new Runnable() {
            public void run() {
                try {

                    startActivity(gameScreen);

                } catch (Exception e) {

                }
            }

        }).start();


    }

}


