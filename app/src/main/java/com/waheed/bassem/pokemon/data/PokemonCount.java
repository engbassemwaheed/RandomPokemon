package com.waheed.bassem.pokemon.data;

import com.google.gson.annotations.SerializedName;

public class PokemonCount {

    @SerializedName("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }
}
