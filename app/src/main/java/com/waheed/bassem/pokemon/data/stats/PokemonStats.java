package com.waheed.bassem.pokemon.data.stats;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonStats implements Parcelable {

    @SerializedName("base_stat")
    private final Integer baseStat;

    @SerializedName("stat")
    private final Stat stat;

    public PokemonStats(Integer baseStat, Stat stat) {
        this.baseStat = baseStat;
        this.stat = stat;
    }

    protected PokemonStats(Parcel in) {
        if (in.readByte() == 0) {
            baseStat = null;
        } else {
            baseStat = in.readInt();
        }
        stat = in.readParcelable(Stat.class.getClassLoader());
    }

    public static final Creator<PokemonStats> CREATOR = new Creator<PokemonStats>() {
        @Override
        public PokemonStats createFromParcel(Parcel in) {
            return new PokemonStats(in);
        }

        @Override
        public PokemonStats[] newArray(int size) {
            return new PokemonStats[size];
        }
    };

    public Integer getBaseStat() {
        return baseStat;
    }

    public String getName() {
        return stat.getName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (baseStat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(baseStat);
        }
        parcel.writeParcelable(stat, i);
    }
}
