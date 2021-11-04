package com.waheed.bassem.pokemon.data.spirits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Spirits implements Parcelable {

    @SerializedName("front_default")
    private final String frontImageUrl;

    @SerializedName("back_default")
    private final String backImageUrl;

    @SerializedName("front_shiny")
    private final String frontShinyUrl;

    @SerializedName("back_shiny")
    private final String backShinyUrl;

    @SerializedName("front_female")
    private final String frontFemaleUrl;

    @SerializedName("back_female")
    private final String backFemaleUrl;

    @SerializedName("front_shiny_female")
    private final String frontFemaleShinyUrl;

    @SerializedName("back_shiny_female")
    private final String backFemaleShinyUrl;

    @SerializedName("other")
    private final SpiritsOther spiritsOther;


    public Spirits(String frontImageUrl,
                   String backImageUrl,
                   String frontShinyUrl,
                   String backShinyUrl,
                   String frontFemaleUrl,
                   String backFemaleUrl,
                   String frontFemaleShinyUrl,
                   String backFemaleShinyUrl,
                   SpiritsOther spiritsOther) {
        this.frontImageUrl = frontImageUrl;
        this.backImageUrl = backImageUrl;
        this.frontShinyUrl = frontShinyUrl;
        this.backShinyUrl = backShinyUrl;
        this.frontFemaleUrl = frontFemaleUrl;
        this.backFemaleUrl = backFemaleUrl;
        this.frontFemaleShinyUrl = frontFemaleShinyUrl;
        this.backFemaleShinyUrl = backFemaleShinyUrl;
        this.spiritsOther = spiritsOther;
    }

    protected Spirits(Parcel in) {
        frontImageUrl = in.readString();
        backImageUrl = in.readString();
        frontShinyUrl = in.readString();
        backShinyUrl = in.readString();
        frontFemaleUrl = in.readString();
        backFemaleUrl = in.readString();
        frontFemaleShinyUrl = in.readString();
        backFemaleShinyUrl = in.readString();
        spiritsOther = in.readParcelable(SpiritsOther.class.getClassLoader());
    }

    public static final Creator<Spirits> CREATOR = new Creator<Spirits>() {
        @Override
        public Spirits createFromParcel(Parcel in) {
            return new Spirits(in);
        }

        @Override
        public Spirits[] newArray(int size) {
            return new Spirits[size];
        }
    };

    public String getFrontImageUrl() {
        return frontImageUrl;
    }

    public String getBackImageUrl() {
        return backImageUrl;
    }

    public String getFrontShinyUrl() {
        return frontShinyUrl;
    }

    public String getBackShinyUrl() {
        return backShinyUrl;
    }

    public String getFrontFemaleUrl() {
        return frontFemaleUrl;
    }

    public String getBackFemaleUrl() {
        return backFemaleUrl;
    }

    public String getFrontFemaleShinyUrl() {
        return frontFemaleShinyUrl;
    }

    public String getBackFemaleShinyUrl() {
        return backFemaleShinyUrl;
    }

    public String getArtWorkUrl() {
        if (spiritsOther != null) {
            SpiritsArtWork spiritsArtWork = spiritsOther.getSpiritsArtWork();
            if (spiritsArtWork != null) {
                return spiritsArtWork.getImageUrl();
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(frontImageUrl);
        parcel.writeString(backImageUrl);
        parcel.writeString(frontShinyUrl);
        parcel.writeString(backShinyUrl);
        parcel.writeString(frontFemaleUrl);
        parcel.writeString(backFemaleUrl);
        parcel.writeString(frontFemaleShinyUrl);
        parcel.writeString(backFemaleShinyUrl);
        parcel.writeParcelable(spiritsOther, i);
    }
}
