package com.pokemon.api.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.flavorpais.R

import com.pokemon.api.data.Model
//import kotlinx.android.synthetic.mexico.item_pokemon.view.*
//import kotlinx.android.synthetic.peru.item_pokemon.view.*

class PokemonListAdapter (val pokemonList: ArrayList<Model.Pokemon>, val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(){

    interface ItemClickListener{
        fun onItemClick(position: Int)
    }

    fun updatePokemonList(newPokemonList: List<Model.Pokemon>){
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(
            view
        )
    }

    override fun getItemCount() = pokemonList.size


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder?.textName.text = pokemonList[position].names
        holder?.textUrl.text = pokemonList[position].url

        var urlPartes = pokemonList[position].url.split("/")
        Log.v("UrlPartes:", urlPartes.toString())
        Log.v("Seleccionado:", urlPartes[urlPartes.size -2].toString())
        var numberPartes = urlPartes[urlPartes.size -2]

        val options = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        holder?.imgPokemon.let {
            if (it != null) {
                Glide.with(it)
                    .setDefaultRequestOptions(options) //https://pokeres.bastionbot.org/images/pokemon/2.png
                    .load("https://pokeres.bastionbot.org/images/pokemon/$numberPartes.png")
                    .into(it)
            }
        }


        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(position)
        }
    }


    class PokemonViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        val imgPokemon = itemView.findViewById<ImageView>(R.id.imgPokemon)
        val textName = itemView.findViewById<TextView>(R.id.textName)
        val textUrl = itemView.findViewById<TextView>(R.id.textUrl)
    }


}