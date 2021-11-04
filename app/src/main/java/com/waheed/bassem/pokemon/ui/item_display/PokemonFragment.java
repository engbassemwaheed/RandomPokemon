package com.waheed.bassem.pokemon.ui.item_display;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.waheed.bassem.pokemon.R;
import com.waheed.bassem.pokemon.data.Pokemon;

import java.util.ArrayList;

public class PokemonFragment extends Fragment {

    public static final String POKEMON_DATA_KEY = "pokemon_data_key";

    private ImageView titleImageView;
    private ImageView frontImageView;
    private ImageView backImageView;
    private ImageView frontShinyImageView;
    private ImageView backShinyImageView;
    private ImageView femaleFrontImageView;
    private ImageView femaleBackImageView;
    private ImageView femaleFrontShinyImageView;
    private ImageView femaleBackShinyImageView;

    private TextView titleTextView;
    private TextView movesTextView;

    private LinearLayout defaultImagesLinearLayout;
    private LinearLayout femaleImagesLinearLayout;
    private LinearLayout movesLinearLayout;
    private LinearLayout statsContainerLinearLayout;
    private LinearLayout statsLinearLayout;

    private MaterialCardView materialCardView;

    public PokemonFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon, container, false);

        materialCardView = rootView.findViewById(R.id.pokemon_card_view);

        titleImageView = rootView.findViewById(R.id.title_image_view);
        frontImageView = rootView.findViewById(R.id.default_front_image_view);
        backImageView = rootView.findViewById(R.id.default_back_image_view);
        frontShinyImageView = rootView.findViewById(R.id.default_shiny_front_image_view);
        backShinyImageView = rootView.findViewById(R.id.default_shiny_back_image_view);
        femaleFrontImageView = rootView.findViewById(R.id.female_front_image_view);
        femaleBackImageView = rootView.findViewById(R.id.female_back_image_view);
        femaleFrontShinyImageView = rootView.findViewById(R.id.female_shiny_front_image_view);
        femaleBackShinyImageView = rootView.findViewById(R.id.female_shiny_back_image_view);

        titleTextView = rootView.findViewById(R.id.title_text_view);
        movesTextView = rootView.findViewById(R.id.moves_text_view);

        defaultImagesLinearLayout = rootView.findViewById(R.id.default_image_linear_layout);
        femaleImagesLinearLayout = rootView.findViewById(R.id.female_image_linear_layout);
        movesLinearLayout = rootView.findViewById(R.id.moves_linear_layout);
        statsContainerLinearLayout = rootView.findViewById(R.id.stats_container_linear_layout);
        statsLinearLayout = rootView.findViewById(R.id.stats_linear_layout);

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            Pokemon pokemon = args.getParcelable(POKEMON_DATA_KEY);
            if (pokemon != null) {
                displayImages(pokemon);
                displayText(pokemon);
                displayStats(pokemon);
            }
        }
    }

    private void displayStats(Pokemon pokemon) {
        ArrayList<Pair<Integer, String>> statsPairs = pokemon.getStatsPairsArrayList();

        if (statsPairs != null && !statsPairs.isEmpty()) {
            statsContainerLinearLayout.setVisibility(View.VISIBLE);


            for (Pair<Integer, String> dataPair : statsPairs) {
                View statsView =  getLayoutInflater().inflate(R.layout.stats_item, statsLinearLayout, false);

                TextView textView = statsView.findViewById(R.id.stats_text_view);
                ProgressBar progressBar = statsView.findViewById(R.id.stats_progress_bar);

                textView.setText(dataPair.second);
                progressBar.setProgress(dataPair.first);

                statsContainerLinearLayout.addView(statsView);
            }

        } else {
            statsContainerLinearLayout.setVisibility(View.GONE);
        }
    }

    private void displayText(Pokemon pokemon) {
        titleTextView.setText(pokemon.getName());

        String movesString = pokemon.getMovesString();
        if (movesString!= null) {
            movesLinearLayout.setVisibility(View.VISIBLE);
            movesTextView.setText(movesString);
        } else {
            movesLinearLayout.setVisibility(View.GONE);
        }
    }

    private void displayImages(Pokemon pokemon) {

        loadImageIntoView(pokemon.getArtWorkImageUrl(), titleImageView);

        if (pokemon.hasDefaultImages()) {
            defaultImagesLinearLayout.setVisibility(View.VISIBLE);

            loadImageIntoView(pokemon.getFrontImageUrl(), frontImageView);
            loadImageIntoView(pokemon.getBackImageUrl(), backImageView);
            loadImageIntoView(pokemon.getFrontShinyImageUrl(), frontShinyImageView);
            loadImageIntoView(pokemon.getBackShinyImageUrl(), backShinyImageView);

        } else {
            defaultImagesLinearLayout.setVisibility(View.GONE);
        }

        if (pokemon.hasFemaleImages()) {
            femaleImagesLinearLayout.setVisibility(View.VISIBLE);

            loadImageIntoView(pokemon.getFrontFemaleImageUrl(), femaleFrontImageView);
            loadImageIntoView(pokemon.getBackFemaleImageUrl(), femaleBackImageView);
            loadImageIntoView(pokemon.getFrontFemaleShinyUrl(), femaleFrontShinyImageView);
            loadImageIntoView(pokemon.getBackFemaleShinyUrl(), femaleBackShinyImageView);

        } else {
            femaleImagesLinearLayout.setVisibility(View.GONE);
        }
    }

    private void loadImageIntoView(String url, ImageView imageView) {
        if (url == null) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(requireContext())
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
