package com.waheed.bassem.pokemon.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.waheed.bassem.pokemon.apis.PokeClient;
import com.waheed.bassem.pokemon.data.Pokemon;
import com.waheed.bassem.pokemon.data.PokemonCount;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {

    private ModelInterface modelInterface;
    private final MutableLiveData<Pokemon> pokemonMutableLiveData;
    private final PokeClient pokeClient;
    private boolean isFirstTime;
    private Integer pokemonMaxNumber = null;
    private final ArrayList<Pokemon> currentPokemonList;

    public PokemonViewModel() {
        pokemonMutableLiveData = new MutableLiveData<>();
        pokeClient = PokeClient.getInstance();
        isFirstTime = true;
        currentPokemonList = new ArrayList<>();
    }

    public void setModelInterface (ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }

    public MutableLiveData<Pokemon> getPokemonMutableLiveData() {
        return pokemonMutableLiveData;
    }

    public void getRandomPokemon() {
        isFirstTime = false;
        if (pokemonMaxNumber != null) {
            getRandomPokemon(pokemonMaxNumber);
        } else {
            getPokemonCount();
        }
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public ArrayList<Pokemon> getCurrentPokemonArrayList() {
        return currentPokemonList;
    }

    public int getPokemonArrayListSize() {
        return currentPokemonList.size();
    }

    private void getRandomPokemon (int count) {
        pokeClient.getRandomPokemon(count).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                if (pokemon != null) {
                    currentPokemonList.add(pokemon);
                    pokemonMutableLiveData.setValue(pokemon);
                } else {
                    if (modelInterface != null) modelInterface.onError();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                if (modelInterface != null) modelInterface.onError();
            }
        });
    }

    private void getPokemonCount () {
        pokeClient.getPokemonCount().enqueue(new Callback<PokemonCount>() {
            @Override
            public void onResponse(Call<PokemonCount> call, Response<PokemonCount> response) {
                PokemonCount pokemonCount = response.body();
                if (pokemonCount!=null) {
                    pokemonMaxNumber = pokemonCount.getCount();
                    if (pokemonMaxNumber != null) {
                        getRandomPokemon();
                    } else {
                        if (modelInterface != null) modelInterface.onError();
                    }
                } else {
                    if (modelInterface != null) modelInterface.onError();
                }
            }

            @Override
            public void onFailure(Call<PokemonCount> call, Throwable t) {
                if (modelInterface != null) modelInterface.onError();
            }
        });
    }

}
