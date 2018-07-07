package com.laube.sirje.jumanji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.laube.sirje.jumanji.utils.CardUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayersActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView ;
    FloatingActionButton addPlayer;
    EditText playerNameField;
    Button startGame;
    ArrayList<String> names = new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_players_screen);

        listView = (ListView) findViewById(R.id.list_of_players);
        addPlayer = (FloatingActionButton) findViewById(R.id.add_player);
        startGame = (Button) findViewById(R.id.btn_start_game);

        addPlayer.setOnClickListener(this);
        startGame.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, names);

        listView.setAdapter(adapter);

    }
        public void startGame()
    {
        Intent intent = new Intent(PlayersActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.add_player) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            playerNameField = new EditText(this);
            playerNameField.setSingleLine();
            builder.setMessage("Lisa mängija");
            builder.setView(playerNameField);
            builder.setPositiveButton(R.string.add_player, new DialogInterface.OnClickListener() {

                public void onClick(final DialogInterface dialog, int id) {
                    playerNameField.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                            String playerName = playerNameField.getText().toString();
                            names.add(playerName);
                        }
                    });
//                    if (playerName == "" || playerName == null) {
//
//                    }
//                    else {
//                        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
//                        names.add(playerName);
//                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (view.getId() == R.id.btn_start_game) {
            startGame();
        }
    }

}
