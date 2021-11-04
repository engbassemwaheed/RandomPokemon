package com.waheed.bassem.pokemon.data.moves;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonMove implements Parcelable {

    @SerializedName("move")
    private final Move move;

    public PokemonMove(Move move) {
        this.move = move;
    }

    protected PokemonMove(Parcel in) {
        move = in.readParcelable(Move.class.getClassLoader());
    }

    public static final Creator<PokemonMove> CREATOR = new Creator<PokemonMove>() {
        @Override
        public PokemonMove createFromParcel(Parcel in) {
            return new PokemonMove(in);
        }

        @Override
        public PokemonMove[] newArray(int size) {
            return new PokemonMove[size];
        }
    };

    public String getName () {
        return move.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(move, i);
    }
}
