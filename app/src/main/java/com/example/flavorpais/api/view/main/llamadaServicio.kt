package com.example.flavorpais.api.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flavorpais.R
import com.pokemon.api.data.Model
import com.pokemon.api.view.detail.PokemonDetailActivity
import com.pokemon.api.view.main.PokemonListAdapter
import com.pokemon.api.view.main.PokemonViewModel
import kotlinx.android.synthetic.peru.activity_llamada_servicio.*

class llamadaServicio : AppCompatActivity(), PokemonListAdapter.ItemClickListener {

    //Variables
    private var pokemonList: List<Model.Pokemon> = listOf()
    private lateinit var viewModel: PokemonViewModel
    private val pokemonListAdapter = PokemonListAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada_servicio)

        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        viewModel.update()

        recyclerviewPokemon.apply {
            layoutManager = LinearLayoutManager(context)
            //layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonListAdapter
        }

        observerViewModel()

    }

    private fun observerViewModel(){

        viewModel.pokemonList.observe(this, Observer { pokemonResponse ->
            pokemonResponse.let {
                recyclerviewPokemon.visibility = View.VISIBLE
                pokemonList = pokemonResponse.results
                pokemonListAdapter.updatePokemonList(pokemonList)
            }
        })

        viewModel.pokemonLoadError.observe(this, Observer {
            it.let {
                if (it){
                    textError.visibility = View.VISIBLE
                } else {
                    textError.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(this, Observer {
            it.let {
                if (it){
                    loading.visibility = View.VISIBLE
                } else {
                    loading.visibility = View.GONE
                }
            }
        })

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra("namePokemon", pokemonList[position].names)
        startActivity(intent)
    }

}