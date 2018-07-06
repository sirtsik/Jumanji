package com.example.android.jumanji.utils;

import com.example.android.jumanji.Card;
import com.example.android.jumanji.R;

import java.util.Arrays;
import java.util.List;

public class CardUtils {

    private static final Card[] allCards = {
            new Card(R.drawable.ca, R.string.card_text_a),
            new Card(R.drawable.c2, R.string.card_text_2),
            new Card(R.drawable.c3, R.string.card_text_3),
            new Card(R.drawable.c4, R.string.card_text_4),
            new Card(R.drawable.c5, R.string.card_text_5),
            new Card(R.drawable.c6, R.string.card_text_6),
            new Card(R.drawable.c7, R.string.card_text_7),
            new Card(R.drawable.c8, R.string.card_text_8),
            new Card(R.drawable.c9, R.string.card_text_9),
            new Card(R.drawable.c10, R.string.card_text_10),
            new Card(R.drawable.cj, R.string.card_text_j),
            new Card(R.drawable.cq, R.string.card_text_q),
            new Card(R.drawable.ck, R.string.card_text_k),
            new Card(R.drawable.da, R.string.card_text_a),
            new Card(R.drawable.d2, R.string.card_text_2),
            new Card(R.drawable.d3, R.string.card_text_3),
            new Card(R.drawable.d4, R.string.card_text_4),
            new Card(R.drawable.d5, R.string.card_text_5),
            new Card(R.drawable.d6, R.string.card_text_6),
            new Card(R.drawable.d7, R.string.card_text_7),
            new Card(R.drawable.d8, R.string.card_text_8),
            new Card(R.drawable.d9, R.string.card_text_9),
            new Card(R.drawable.d10, R.string.card_text_10),
            new Card(R.drawable.dj, R.string.card_text_j),
            new Card(R.drawable.dq, R.string.card_text_q),
            new Card(R.drawable.dk, R.string.card_text_k),
            new Card(R.drawable.ha, R.string.card_text_a),
            new Card(R.drawable.h2, R.string.card_text_2),
            new Card(R.drawable.h3, R.string.card_text_3),
            new Card(R.drawable.h4, R.string.card_text_4),
            new Card(R.drawable.h5, R.string.card_text_5),
            new Card(R.drawable.h6, R.string.card_text_6),
            new Card(R.drawable.h7, R.string.card_text_7),
            new Card(R.drawable.h8, R.string.card_text_8),
            new Card(R.drawable.h9, R.string.card_text_9),
            new Card(R.drawable.h10, R.string.card_text_10),
            new Card(R.drawable.hj, R.string.card_text_j),
            new Card(R.drawable.hq, R.string.card_text_q),
            new Card(R.drawable.hk, R.string.card_text_k),
            new Card(R.drawable.sa, R.string.card_text_a),
            new Card(R.drawable.s2, R.string.card_text_2),
            new Card(R.drawable.s3, R.string.card_text_3),
            new Card(R.drawable.s4, R.string.card_text_4),
            new Card(R.drawable.s5, R.string.card_text_5),
            new Card(R.drawable.s6, R.string.card_text_6),
            new Card(R.drawable.s7, R.string.card_text_7),
            new Card(R.drawable.s8, R.string.card_text_8),
            new Card(R.drawable.s9, R.string.card_text_9),
            new Card(R.drawable.s10, R.string.card_text_10),
            new Card(R.drawable.sj, R.string.card_text_j),
            new Card(R.drawable.sq, R.string.card_text_q),
            new Card(R.drawable.sk, R.string.card_text_k)
    };

    public static List<Card> getAllCards() {
        return Arrays.asList(allCards);
    }
}
