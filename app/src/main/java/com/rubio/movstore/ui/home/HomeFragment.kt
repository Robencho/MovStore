package com.rubio.movstore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentHomeBinding
import com.rubio.movstore.ui.home.viewmodel.HomeViewModel
import com.rubio.movstore.ui.movcatalogue.adapter.SlidersAdapter
import com.rubio.movstore.ui.movcatalogue.sliders.SliderOneFragment
import com.rubio.movstore.ui.movcatalogue.sliders.SliderThreeFragment
import com.rubio.movstore.ui.movcatalogue.sliders.SliderTwoFragment

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.homeViewModel = homeViewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeLiveData()
        setupSliders()

        homeViewModel.showSliders()
    }

    private fun setupListeners() {
        homeViewModel.timerLoading()
        binding.cat1.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCatalogueFragment())
        }
    }

    private fun observeLiveData() {

    }

    private fun setupSliders() {
        val sliders: ArrayList<Fragment> = arrayListOf(
            SliderOneFragment(),
            SliderTwoFragment(),
            SliderThreeFragment()
        )
        val adapter = SlidersAdapter(sliders, this)
        binding.vpImages.adapter = adapter
    }

}