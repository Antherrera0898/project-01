package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daclink.drew.sp22.cst438_project01_starter.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.viewHolder>{
    private ArrayList<Pokemon> dataset;

    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.pokeName.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImage);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemon(ArrayList<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView pokeImage;
        private TextView pokeName;

        public viewHolder(View viewItem){
            super(viewItem);

            pokeImage = (ImageView) viewItem.findViewById(R.id.pokeImage);
            pokeName = (TextView) viewItem.findViewById(R.id.pokeName);
        }
    }
}
