package com.waheed.bassem.pokemon.ui.item_display;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.waheed.bassem.pokemon.data.Pokemon;

import java.util.ArrayList;

public class PokemonViewPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Pokemon> pokemons;

    public PokemonViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Pokemon> pokemons) {
        super(fragmentActivity);
        this.pokemons = pokemons;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        args.putParcelable(PokemonFragment.POKEMON_DATA_KEY, pokemons.get(position));

        PokemonFragment pokemonFragment = new PokemonFragment();
        pokemonFragment.setArguments(args);

        return pokemonFragment;
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
