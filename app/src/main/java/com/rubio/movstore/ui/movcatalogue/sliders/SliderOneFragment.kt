package com.rubio.movstore.ui.movcatalogue.sliders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentSliderOneBinding
import com.rubio.movstore.ui.home.viewmodel.HomeViewModel

class SliderOneFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding:FragmentSliderOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_slider_one, container, false)
        binding.lifecycleOwner = this
        binding.sliderModel = homeViewModel
        return binding.root
    }
}