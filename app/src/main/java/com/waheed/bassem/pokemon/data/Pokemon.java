package com.waheed.bassem.pokemon.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import com.google.gson.annotations.SerializedName;
import com.waheed.bassem.pokemon.data.moves.PokemonMove;
import com.waheed.bassem.pokemon.data.spirits.Spirits;
import com.waheed.bassem.pokemon.data.stats.PokemonStats;

import java.util.ArrayList;

public class Pokemon implements Parcelable {

    private static final String UNKNOWN_NAME = "unknown name";
    private static final String EMPTY = " ";

    @SerializedName("id")
    private final Integer id;

    @SerializedName("name")
    private final String name;

    @SerializedName("sprites")
    private final Spirits spirits;

    @SerializedName("moves")
    private final ArrayList<PokemonMove> pokemonMoveArrayList;

    @SerializedName("stats")
    private final ArrayList<PokemonStats> pokemonStatsArrayList;

    public Pokemon(Integer id,
                   String name,
                   Spirits spirits,
                   ArrayList<PokemonMove> pokemonMoveArrayList,
                   ArrayList<PokemonStats> pokemonStatsArrayList) {
        this.id = id;
        this.name = name;
        this.spirits = spirits;
        this.pokemonMoveArrayList = pokemonMoveArrayList;
        this.pokemonStatsArrayList = pokemonStatsArrayList;
    }

    protected Pokemon(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        spirits = in.readParcelable(Spirits.class.getClassLoader());
        pokemonMoveArrayList = in.createTypedArrayList(PokemonMove.CREATOR);
        pokemonStatsArrayList = in.createTypedArrayList(PokemonStats.CREATOR);
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        if (name != null) return name;
        else return UNKNOWN_NAME;
    }

    public String getMovesString() {
        if (pokemonMoveArrayList != null && !pokemonMoveArrayList.isEmpty()) {
            final String DOT = " • ";
            String result = "";

            for (int i = 0; i < pokemonMoveArrayList.size(); i++) {
                PokemonMove pokemonMove = pokemonMoveArrayList.get(i);

                String moveName = pokemonMove.getName();
                if (moveName != null) {
                    result = result.concat(moveName);
                    if (i < pokemonMoveArrayList.size() - 1) result = result.concat(DOT);
                }
            }

            return result;
        }

        return null;
    }

    public ArrayList<Pair<Integer, String>> getStatsPairsArrayList() {
        if (pokemonStatsArrayList != null && !pokemonStatsArrayList.isEmpty()) {
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();

            for (PokemonStats pokemonStats : pokemonStatsArrayList) {

                Integer baseStat = pokemonStats.getBaseStat();
                String statName = pokemonStats.getName();

                if (baseStat != null && statName != null) {
                    result.add(new Pair<>(baseStat, statName));
                }
            }

            return result;
        }

        return null;
    }

    public String getArtWorkImageUrl () {
        if (spirits!=null) {
            String url = spirits.getArtWorkUrl();
            if (url != null) return url;
        }
        return EMPTY;
    }

    public boolean hasFemaleImages() {
        return spirits != null && (spirits.getBackFemaleShinyUrl() != null
                || spirits.getBackFemaleUrl() != null
                || spirits.getFrontFemaleUrl() != null
                || spirits.getFrontFemaleShinyUrl() != null);
    }

    public boolean hasDefaultImages() {
        return spirits != null && (spirits.getFrontImageUrl() != null
                || spirits.getBackImageUrl() != null
                || spirits.getFrontShinyUrl() != null
                || spirits.getBackShinyUrl() != null);
    }

    public String getFrontImageUrl () {
        if (spirits!=null) return spirits.getFrontImageUrl();
        else return null;
    }

    public String getBackImageUrl () {
        if (spirits!=null) return spirits.getBackImageUrl();
        else return null;
    }

    public String getFrontShinyImageUrl () {
        if (spirits!=null) return spirits.getFrontShinyUrl();
        else return null;
    }

    public String getBackShinyImageUrl () {
        if (spirits!=null) return spirits.getBackShinyUrl();
        else return null;
    }

    public String getFrontFemaleImageUrl() {
        if (spirits!=null) return spirits.getFrontFemaleUrl();
        else return null;
    }

    public String getBackFemaleImageUrl() {
        if (spirits!=null) return spirits.getBackFemaleUrl();
        else return null;
    }

    public String getFrontFemaleShinyUrl () {
        if (spirits!=null) return spirits.getFrontFemaleShinyUrl();
        else return null;
    }

    public String getBackFemaleShinyUrl () {
        if (spirits!=null) return spirits.getBackFemaleShinyUrl();
        else return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeParcelable(spirits, i);
        parcel.writeTypedList(pokemonMoveArrayList);
        parcel.writeTypedList(pokemonStatsArrayList);
    }
}
