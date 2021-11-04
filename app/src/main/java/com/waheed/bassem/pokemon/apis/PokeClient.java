package com.waheed.bassem.pokemon.apis;

import com.waheed.bassem.pokemon.data.Pokemon;
import com.waheed.bassem.pokemon.data.PokemonCount;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeClient {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private static PokeClient pokeClient;
    private final PokeAPI pokeAPI;

    private PokeClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeAPI = retrofit.create(PokeAPI.class);
    }

    public static PokeClient getInstance() {
        if (pokeClient == null) {
            pokeClient = new PokeClient();
        }
        return pokeClient;
    }

    public Call<PokemonCount> getPokemonCount() {
        return pokeAPI.getPokemonCount();
    }

    public Call<Pokemon> getRandomPokemon(int maxValue) {
        int randomId = new Random().nextInt(maxValue + 1); // added 1 to include the last ID
        return getPokemon(randomId);

    }

    public Call<Pokemon> getPokemon(int pokemonId) {
        return pokeAPI.getPokemon(pokemonId);
    }


}
