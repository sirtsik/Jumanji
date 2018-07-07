package com.laube.sirje.jumanji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener{

    Button startGame;
    Button showRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        startGame = findViewById(R.id.btn_start_game);
        showRules = findViewById(R.id.btn_show_rules);
        startGame.setOnClickListener(this);
        showRules.setOnClickListener(this);

    }
    public void startGame()
    {
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showRules()
    {
        Intent intent = new Intent(HomeScreenActivity.this, RulesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_start_game) {
            startGame();
        }
        else if(view.getId() == R.id.btn_show_rules) {
            showRules();
        }
    }
}
