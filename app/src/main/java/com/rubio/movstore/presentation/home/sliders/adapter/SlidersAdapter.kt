package com.rubio.movstore.presentation.home.sliders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rubio.movstore.databinding.SliderItemBinding
import com.rubio.movstore.domain.models.Slider

class SlidersAdapter(private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<SlidersAdapter.ViewHolder>() {

    var listItemsToSlider: ArrayList<Slider> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SliderItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItemsToSlider.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listItemsToSlider[position]
        holder.bind(current)
        if (position == listItemsToSlider.size - 2) {
            viewPager2.post(sliderRunnable)
        }
    }

    inner class ViewHolder(private val itemBinding: SliderItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(sliderItem: Slider) {
            itemBinding.sliderModel = sliderItem
        }
    }

    internal fun addSlider(sliders: List<Slider>) {
        listItemsToSlider = sliders as ArrayList<Slider>
        notifyDataSetChanged()
    }

    private val sliderRunnable = Runnable {
        listItemsToSlider.addAll(listItemsToSlider)
        notifyDataSetChanged()
    }
}