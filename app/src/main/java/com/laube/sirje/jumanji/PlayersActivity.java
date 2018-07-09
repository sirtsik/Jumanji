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
import android.widget.Adapter;
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
    private static final String KEY_PLAYER_LIST = "playerList";
    ListView listView;
    FloatingActionButton addPlayer;
    EditText playerNameField;
    Button startGame;
    public static final String KEY_PLAYER_NAMES = "playerName";
    private ArrayList<String> names = new ArrayList<String>() {};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_players_screen);

        listView = (ListView) findViewById(R.id.list_of_players);
        addPlayer = (FloatingActionButton) findViewById(R.id.add_player);
        startGame = (Button) findViewById(R.id.btn_start_game);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        listView.setAdapter(adapter);

        if (savedInstanceState == null){

        }
        else {
            ArrayList<String> stringArrayList = savedInstanceState.getStringArrayList(KEY_PLAYER_LIST);
            names.addAll(stringArrayList);

        }

        addPlayer.setOnClickListener(this);
        startGame.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(KEY_PLAYER_LIST, names);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Kas soovid katkestada mängijate lisamise? Valides 'jah\" kustuvad kõik lisatud mängijate nimed.");
        builder.setPositiveButton("Jah", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Ei", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startGame() {
        Intent intent = new Intent(PlayersActivity.this, MainActivity.class);
        intent.putStringArrayListExtra(KEY_PLAYER_NAMES, names);
        startActivity(intent);
        names.clear();
        adapter.notifyDataSetChanged();

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
                    String playerName = playerNameField.getText().toString();

                    if (!playerName.equals("")) {
                        names.add(playerName);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

            builder.create().show();
        } else if (view.getId() == R.id.btn_start_game) {
            if (names.size() >= 2) {
                startGame();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                playerNameField = new EditText(this);
                playerNameField.setSingleLine();
                builder.setMessage("Mängu alustamiseks on vaja vähemalt kahte mängijat.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        }
    }

}
