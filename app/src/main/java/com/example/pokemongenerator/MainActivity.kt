package com.example.pokemongenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var pokemonImageURL = ""
    private lateinit var pokemonList: MutableList<String>
    private lateinit var rvPokemon: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPokemonImageURL()
        Log.d("pokemonImageURL", "pokemon image URL set")

        val button = findViewById<Button>(R.id.nextPokemon)
        val imageView = findViewById<ImageView>(R.id.pokemonImage)

        rvPokemon = findViewById(R.id.pokemon_list)
        pokemonList = mutableListOf()
    }

    private fun getPokemonImageURL() {
        val client = AsyncHttpClient()

        client["https://pokeapi.co/api/v2/pokemon/20", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful$json")

                pokemonImageURL = json.jsonObject.getString("message")

                val pokemonImageArray = json.jsonObject.getJSONArray("message")

                for (i in 0 until pokemonImageArray.length()) {
                    pokemonList.add(pokemonImageArray.getString(i))
                }

                val adapter = PokemonAdapter(pokemonList)
                rvPokemon.adapter = adapter
                rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }

    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getPokemonImageURL()

            Glide.with(this)
                . load(pokemonImageURL)
                .fitCenter()
                .into(imageView)
        }
    }
}