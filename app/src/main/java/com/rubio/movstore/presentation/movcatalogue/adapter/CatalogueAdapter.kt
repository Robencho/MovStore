package com.rubio.movstore.presentation.movcatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rubio.movstore.data.models.MovieParcelable
import com.rubio.movstore.databinding.ItemMovStoreBinding
import com.rubio.movstore.domain.entities.Movie

class CatalogueAdapter : RecyclerView.Adapter<CatalogueAdapter.ViewHolder>() {

    var listMovStore: ArrayList<Movie> = ArrayList()
    var onItemClicked:((data:Movie)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovStoreBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovStore.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listMovStore[position]
        holder.bind(current)
    }

    inner class ViewHolder(private val binding: ItemMovStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movItem: Movie) {
            binding.movStoreModel = movItem
            binding.imgItemMovie.setOnClickListener {
                onItemClicked?.invoke(movItem)
            }
        }
    }
    internal fun addMovies(movies: List<Movie>) {
        listMovStore = movies as ArrayList<Movie>
        notifyDataSetChanged()
    }
}