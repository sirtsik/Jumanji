package com.laube.sirje.jumanji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laube.sirje.jumanji.utils.CardUtils;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_REMAINING_CARD_POOL = "remainingCardPool";
    public static final String KEY_CURRENT_CARD = "currentCard";

    ImageView cardStack;
    TextView cardText;
    Card currentCard;
    ArrayList<Card> remainingCardPool = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStack = findViewById(R.id.card_stack);
        cardText = findViewById(R.id.card_text);

        if (savedInstanceState == null) {
            remainingCardPool.addAll(CardUtils.getAllCards());
        } else {
            ArrayList<Card> parcelableArrayList = savedInstanceState.getParcelableArrayList(KEY_REMAINING_CARD_POOL);
            remainingCardPool.addAll(parcelableArrayList);
            currentCard = savedInstanceState.getParcelable(KEY_CURRENT_CARD);
            updateCardUI();
        }

        cardStack.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
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
        updateCardUI();
        remainingCardPool.remove(currentCard);
        if (remainingCardPool.isEmpty()) {
            cardStack.setImageResource(R.drawable.gray_back);
            cardText.setText(R.string.end_text);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Mäng läbi!");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    remainingCardPool.addAll(CardUtils.getAllCards());
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
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
