package com.rubio.movstore.presentation.home

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
import com.rubio.movstore.presentation.home.sliders.adapter.SlidersAdapter
import com.rubio.movstore.presentation.home.viewmodel.HomeViewModel
import com.rubio.movstore.presentation.login.LoginViewModel
import com.rubio.movstore.presentation.movcatalogue.viewModel.CatalogueViewModel
import com.rubio.movstore.utils.MovStoreConstants
import kotlin.math.abs

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val catalogueViewModel: CatalogueViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    private val sliderAdapter by lazy { SlidersAdapter(binding.vpImages) }
    private val sliderHandler = Handler()


    private val sliderRunnable = Runnable {
        binding.vpImages.currentItem = binding.vpImages.currentItem + 1
    }

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
        catalogueViewModel.showSimpleToolbar()
        setupInitHome()
    }

    private fun setupInitHome() {
        catalogueViewModel.titleToolbar.value = "Home"
        loginViewModel.closeSessionResponse.value = true
        homeViewModel.isHome.value = true
    }

    private fun setupListeners() {
        binding.cat1.setOnClickListener {
            navigationMovies(MovStoreConstants.VALUE_MOVIE_POPULAR)
        }
        binding.cat2.setOnClickListener {
            navigationMovies(MovStoreConstants.VALUE_MOVIE_LATEST)
        }
        binding.cat3.setOnClickListener {
            navigationMovies(MovStoreConstants.VALUE_MOVIE_TOP_RATED)
        }
        binding.cat4.setOnClickListener {
            navigationMovies(MovStoreConstants.VALUE_MOVIE_UPCOMING)
        }


        /*   binding.btnCloseSession.setOnClickListener {
               val username = PreferencesHelper(requireActivity()).prefUserName
               val password = PreferencesHelper(requireActivity()).prefUserPassword
               if (username.isNotEmpty() && password.isNotEmpty()) {
                   loginViewModel.closeSession(username, password)
               }
           }*/
    }
    private fun navigationMovies(category:String){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCatalogueFragment(category))
    }

    private fun observeLiveData() {
        homeViewModel.sliders.observe(viewLifecycleOwner, Observer {
            sliderAdapter.addSlider(it)
        })
        /*loginViewModel.closeSessionResponse.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                homeViewModel.isHome.value = false
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        })*/
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

}