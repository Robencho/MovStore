package com.rubio.movstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rubio.movstore.databinding.ActivityMainBinding
import com.rubio.movstore.ui.home.HomeFragmentDirections
import com.rubio.movstore.ui.home.viewmodel.HomeViewModel
import com.rubio.movstore.ui.login.LoginViewModel
import com.rubio.movstore.ui.movcatalogue.viewModel.CatalogueViewModel
import com.rubio.movstore.utils.PreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var catalogueViewModel: CatalogueViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        catalogueViewModel = ViewModelProvider(this).get(CatalogueViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.catalogueViewModel = catalogueViewModel
        binding.fullToolbarMain.catalogueViewModel = catalogueViewModel
        binding.toolbarMain.catalogueViewModel = catalogueViewModel
        binding.loginViewModel = loginViewModel

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.home_nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigationBottom.setupWithNavController(navController)
        setupToolbar()
        setupNavigation()
        observerLiveData()
    }

    private fun setupToolbar() {
        binding.fullToolbarMain.btnCloseSession.setOnClickListener {
            val username = PreferencesHelper(this).prefUserName
            val password = PreferencesHelper(this).prefUserPassword
            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.closeSession(username, password)
            }
        }
    }

    private fun observerLiveData() {
        loginViewModel.closeSessionResponse.observe(this, Observer {
            if (it == false) {
                homeViewModel.isHome.value = false
                navController.navigate(R.id.loginFragment)
            }
        })
    }

    private fun setupNavigation() {
        binding.navigationBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.btnMovies -> {
                    navController.navigate(R.id.catalogueFragment)
                }
            }
            true
        }
    }
}