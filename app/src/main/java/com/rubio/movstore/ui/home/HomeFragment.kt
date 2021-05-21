package com.rubio.movstore.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentHomeBinding
import com.rubio.movstore.ui.home.sliders.adapter.SlidersAdapter
import com.rubio.movstore.ui.home.viewmodel.HomeViewModel
import com.rubio.movstore.ui.login.LoginViewModel
import com.rubio.movstore.utils.PreferencesHelper
import kotlin.math.abs

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    private val sliderAdapter by lazy { SlidersAdapter(binding.vpImages) }
    private val sliderHandler = Handler()

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
        setupInitHome()
    }

    private fun setupInitHome() {
        loginViewModel.closeSessionResponse.value = true
        homeViewModel.isHome.value = true
    }

    private fun setupListeners() {
        homeViewModel.timerLoading()
        binding.cat1.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCatalogueFragment())
        }

        binding.btnCloseSession.setOnClickListener {
            val username = PreferencesHelper(requireActivity()).prefUserName
            val password = PreferencesHelper(requireActivity()).prefUserPassword
            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.closeSession(username, password)
            }
        }
    }

    private fun observeLiveData() {
        homeViewModel.sliders.observe(viewLifecycleOwner, Observer {
            sliderAdapter.addSlider(it)
        })
        loginViewModel.closeSessionResponse.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                homeViewModel.isHome.value = false
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        })
    }

    private fun setupSliders() {
        binding.vpImages.adapter = sliderAdapter
        binding.vpImages.clipToPadding = false
        binding.vpImages.clipChildren = false
        binding.vpImages.offscreenPageLimit = 3
        binding.vpImages.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.88f + r * 0.15f
        })

        binding.vpImages.setPageTransformer(transformer)
        binding.vpImages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })

    }

    private val sliderRunnable = Runnable {
        binding.vpImages.currentItem = binding.vpImages.currentItem + 1
    }

}