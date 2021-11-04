package com.waheed.bassem.pokemon.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.waheed.bassem.pokemon.R;
import com.waheed.bassem.pokemon.Utils;
import com.waheed.bassem.pokemon.ui.item_display.PokemonViewPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private MaterialButton loadPokemonButton;
    private DotsIndicator dotsIndicator;
    private ProgressBar loadingProgressBar;
    private ScrollView scrollView;
    private TextView noPokemonTextView;

    private PokemonViewModel pokemonViewModel;
    private PokemonViewPagerAdapter pokemonViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();
        initViews();
        setListeners();

        if (pokemonViewModel.isFirstTime()) {
            handleEmptyList();
            loadNewPokemon();
        }
    }

    private void initViews() {
        //view pager
        viewPager2 = findViewById(R.id.main_view_pager);
        viewPager2.setSaveEnabled(false);
        viewPager2.setAdapter(pokemonViewPagerAdapter);

        //scroll view
        scrollView = findViewById(R.id.scroll_view);

        //no pokemon textview
        noPokemonTextView = findViewById(R.id.no_pokemon_text_view);

        //dots indicator
        dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager2(viewPager2);

        //buttons
        loadPokemonButton = findViewById(R.id.new_pokemon_material_button);

        //progress bar
        loadingProgressBar = findViewById(R.id.loading_progress_bar);
    }


    private void initVariables() {
        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewPagerAdapter = new PokemonViewPagerAdapter(this, pokemonViewModel.getCurrentPokemonArrayList());
    }

    private void setListeners() {
        pokemonViewModel.setModelInterface(() -> {
            showSnackbar(R.string.error);
            setLoading(false);
        });

        pokemonViewModel.getPokemonMutableLiveData().observe(this, pokemon -> {
            pokemonViewPagerAdapter.notifyDataSetChanged();
            viewPager2.setCurrentItem(pokemonViewModel.getPokemonArrayListSize(), true);

            handleEmptyList();

            setLoading(false);

            adjustDotsIndicator();
        });

        loadPokemonButton.setOnClickListener(view -> loadNewPokemon());
    }

    private void adjustDotsIndicator() {
        //remove the dots indicator if it goes off screen
        if (dotsIndicator.getWidth() >= viewPager2.getWidth()) {
            dotsIndicator.setVisibility(View.GONE);
        } else {
            dotsIndicator.setVisibility(View.VISIBLE);
        }
    }

    private void loadNewPokemon() {
        if (Utils.isNetworkConnected(this)) {
            pokemonViewModel.getRandomPokemon();
            setLoading(true);
        } else {
            showSnackbar(R.string.please_connect_to_internet);
            setLoading(false);
        }
    }

    private void handleEmptyList() {
       if (pokemonViewModel.getPokemonArrayListSize() == 0) {
            noPokemonTextView.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            noPokemonTextView.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
           scrollView.scrollTo(0, 0);
        }
    }

    private void setLoading(boolean loading) {
        if (loading) {
            loadPokemonButton.setVisibility(View.GONE);
            loadingProgressBar.setVisibility(View.VISIBLE);
        } else {
            loadPokemonButton.setVisibility(View.VISIBLE);
            loadingProgressBar.setVisibility(View.GONE);
        }
    }

    private void showSnackbar(int messageId) {
        Snackbar snackbar = Snackbar.make(viewPager2, messageId, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}