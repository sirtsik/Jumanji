package com.laube.sirje.jumanji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laube.sirje.jumanji.utils.CardUtils;

import java.util.ArrayList;
import java.util.Random;

import static com.laube.sirje.jumanji.PlayersActivity.names;
import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_REMAINING_CARD_POOL = "remainingCardPool";
    public static final String KEY_CURRENT_CARD = "currentCard";

    ImageView cardStack;
    TextView cardText;
    TextView displayNextPlayer;
    TextView displayCurrentPlayer;
    String currentPlayerName;
    String nextPlayerName;
    Card currentCard;
    ArrayList<Card> remainingCardPool = new ArrayList<>();
    ArrayList<String> playerNames = names;
    Integer playerIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStack = findViewById(R.id.card_stack);
        cardText = findViewById(R.id.card_text);
        displayNextPlayer = findViewById(R.id.next_player);
        displayCurrentPlayer = findViewById(R.id.current_player);

        if (savedInstanceState == null) {
            remainingCardPool.addAll(CardUtils.getAllCards());
        } else {
            ArrayList<Card> parcelableArrayList = savedInstanceState.getParcelableArrayList(KEY_REMAINING_CARD_POOL);
            remainingCardPool.addAll(parcelableArrayList);
            currentCard = savedInstanceState.getParcelable(KEY_CURRENT_CARD);
            updateCardUI();
        }

        cardStack.setOnClickListener(this);

        displayCurrentPlayer.setVisibility(View.INVISIBLE);
        nextPlayerName = playerNames.get(playerIndex);
        displayNextPlayer.setText(String.format(getString(R.string.next_player_name), nextPlayerName));
        playerIndex += 1;
        playerIndex = playerIndex % playerNames.size();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_info:
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(KEY_REMAINING_CARD_POOL, remainingCardPool);
        outState.putParcelable(KEY_CURRENT_CARD, currentCard);
    }

    public void updateCardUI() {
        cardStack.setImageResource(currentCard.getCardId());
        cardText.setText(currentCard.getCardTextId());
    }

    @Override
    public void onClick(View view) {
        Random r = new Random();
        int currentCardId = (r.nextInt(remainingCardPool.size()));
        currentCard = remainingCardPool.get(currentCardId);
        displayCurrentPlayer.setVisibility(View.VISIBLE);
        if(playerIndex < playerNames.size()) {
            if( playerIndex == 0) {
                nextPlayerName = playerNames.get(playerIndex);
                displayNextPlayer.setText(String.format(getString(R.string.next_player_name), nextPlayerName));
                currentPlayerName = playerNames.get(playerNames.size()-1);
                displayCurrentPlayer.setText(String.format(getString(R.string.current_player), currentPlayerName));
                playerIndex += 1;
                playerIndex = playerIndex % playerNames.size();
            } else {
                nextPlayerName = playerNames.get(playerIndex);
                currentPlayerName = playerNames.get(playerIndex - 1);
                displayNextPlayer.setText(String.format(getString(R.string.next_player_name), nextPlayerName));
                displayCurrentPlayer.setText(String.format(getString(R.string.current_player), currentPlayerName));
                playerIndex += 1;
                playerIndex = playerIndex % playerNames.size();
            }
        } else {
            playerIndex = 0;
            nextPlayerName = playerNames.get(playerIndex);
            displayNextPlayer.setText(String.format(getString(R.string.next_player_name), nextPlayerName));
            currentPlayerName = playerNames.get(playerNames.size()-1);
            displayCurrentPlayer.setText(String.format(getString(R.string.current_player), currentPlayerName));
            playerIndex += 1;
            playerIndex = playerIndex % playerNames.size();
        }



        updateCardUI();
        remainingCardPool.remove(currentCard);
        if (remainingCardPool.isEmpty()) {
//            cardStack.setImageResource(R.drawable.gray_back);
            cardText.setText(R.string.end_text);
            displayCurrentPlayer.setVisibility(View.INVISIBLE);
            displayNextPlayer.setVisibility(View.INVISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Mäng läbi!");
            builder.setPositiveButton(R.string.btn_start_new_game, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    remainingCardPool.addAll(CardUtils.getAllCards());
                    Intent intent = new Intent(MainActivity.this, PlayersActivity.class);
                    startActivity(intent);
                    names.clear();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
