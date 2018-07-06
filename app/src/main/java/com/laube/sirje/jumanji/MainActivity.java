package com.laube.sirje.jumanji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            remainingCardPool.addAll(CardUtils.getAllCards());
        }
    }
}
