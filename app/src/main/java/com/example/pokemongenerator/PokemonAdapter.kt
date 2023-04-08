package com.example.pokemongenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter (private val pokemonList: List<String>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>()
{

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonImage: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            pokemonImage = view.findViewById(R.id.pokemon_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Get element from your dataset at this position and replace the contents of the view with that element")
        Glide.with(holder.itemView)
            .load(pokemonList[position])
            .centerCrop()
            .into(holder.pokemonImage)
    }

    override fun getItemCount() = pokemonList.size
}