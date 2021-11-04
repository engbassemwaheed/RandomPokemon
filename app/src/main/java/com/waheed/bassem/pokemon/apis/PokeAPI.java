package com.waheed.bassem.pokemon.apis;

import com.waheed.bassem.pokemon.data.Pokemon;
import com.waheed.bassem.pokemon.data.PokemonCount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface PokeAPI {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int pokemonId);

    @GET("pokemon")
    Call<PokemonCount> getPokemonCount ();

}
