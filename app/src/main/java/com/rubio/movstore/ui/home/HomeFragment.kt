package com.rubio.movstore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentHomeBinding
import com.rubio.movstore.ui.home.viewmodel.HomeViewModel
import com.rubio.movstore.ui.login.LoginViewModel
import com.rubio.movstore.ui.movcatalogue.adapter.SlidersAdapter
import com.rubio.movstore.ui.movcatalogue.sliders.SliderOneFragment
import com.rubio.movstore.ui.movcatalogue.sliders.SliderThreeFragment
import com.rubio.movstore.ui.movcatalogue.sliders.SliderTwoFragment
import com.rubio.movstore.utils.PreferencesHelper

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
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
        loginViewModel.closeSessionResponse.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                homeViewModel.isHome.value = false
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        })
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