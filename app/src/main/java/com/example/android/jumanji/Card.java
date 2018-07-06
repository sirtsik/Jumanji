package com.example.android.jumanji;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {

    private int cardId;
    private int cardTextId;

    public Card(int cardId, int cardTextId) {
        this.cardId = cardId;
        this.cardTextId = cardTextId;
    }

    private Card(Parcel in) {
        cardId = in.readInt();
        cardTextId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cardId);
        dest.writeInt(cardTextId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public int getCardId() {
        return cardId;
    }

    public int getCardTextId() {
        return cardTextId;
    }
}
