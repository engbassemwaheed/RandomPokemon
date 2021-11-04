package com.waheed.bassem.pokemon.data.spirits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class SpiritsOther implements Parcelable {

    @SerializedName("official-artwork")
    private final SpiritsArtWork spiritsArtWork;

    SpiritsOther(SpiritsArtWork spiritsArtWork) {
        this.spiritsArtWork = spiritsArtWork;
    }

    protected SpiritsOther(Parcel in) {
        spiritsArtWork = in.readParcelable(SpiritsArtWork.class.getClassLoader());
    }

    public static final Creator<SpiritsOther> CREATOR = new Creator<SpiritsOther>() {
        @Override
        public SpiritsOther createFromParcel(Parcel in) {
            return new SpiritsOther(in);
        }

        @Override
        public SpiritsOther[] newArray(int size) {
            return new SpiritsOther[size];
        }
    };

    SpiritsArtWork getSpiritsArtWork() {
        return spiritsArtWork;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(spiritsArtWork, i);
    }
}
