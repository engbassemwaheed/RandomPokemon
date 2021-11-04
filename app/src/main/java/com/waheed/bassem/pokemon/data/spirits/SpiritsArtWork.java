package com.waheed.bassem.pokemon.data.spirits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


class SpiritsArtWork implements Parcelable {

    @SerializedName("front_default")
    private final String imageUrl;

    SpiritsArtWork(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    protected SpiritsArtWork(Parcel in) {
        imageUrl = in.readString();
    }

    public static final Creator<SpiritsArtWork> CREATOR = new Creator<SpiritsArtWork>() {
        @Override
        public SpiritsArtWork createFromParcel(Parcel in) {
            return new SpiritsArtWork(in);
        }

        @Override
        public SpiritsArtWork[] newArray(int size) {
            return new SpiritsArtWork[size];
        }
    };

    String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
    }
}
