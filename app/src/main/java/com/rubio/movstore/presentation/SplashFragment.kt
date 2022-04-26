package com.rubio.movstore.presentation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentSplashBinding
import com.rubio.movstore.presentation.home.viewmodel.HomeViewModel
import com.rubio.movstore.presentation.login.LoginViewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // timer()
        setupInit()
        observeLiveData()
    }

    private fun setupInit() {
        loginViewModel.gertUser()
    }

    private fun observeLiveData() {
        loginViewModel.existUser.observe(viewLifecycleOwner, Observer { existUser ->
            if (existUser) {
                loginViewModel.getStateLoginByUser(loginViewModel.user.user_id)
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToRegisterFragment())
            }
        })

        loginViewModel.initSetup.observe(viewLifecycleOwner, Observer { isLogin ->
            if (isLogin) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        })
    }

    private fun timer() {
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                homeViewModel.showLoading.value = true
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
        timer.start()
    }
}