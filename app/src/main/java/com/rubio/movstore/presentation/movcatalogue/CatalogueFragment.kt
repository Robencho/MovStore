package com.rubio.movstore.presentation.movcatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentCatalogueBinding
import com.rubio.movstore.presentation.home.viewmodel.HomeViewModel
import com.rubio.movstore.presentation.movcatalogue.adapter.CatalogueAdapter
import com.rubio.movstore.presentation.movcatalogue.viewModel.CatalogueViewModel


class CatalogueFragment : Fragment() {

    private val catalogueViewModel: CatalogueViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentCatalogueBinding

    private val args: CatalogueFragmentArgs by navArgs()

    private val catalogueAdapter by lazy { CatalogueAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.showLoading.value = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalogue, container, false)
        binding.lifecycleOwner = this
        binding.catalogueViewModel = catalogueViewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeLiveData()
    }

    private fun setupListeners() {
        catalogueViewModel.titleToolbar.value = "Movies"
        catalogueViewModel.showFullToolbar()
        catalogueViewModel.getMoviesFromLocalDB()

        binding.rvListCatalogue.run {
            adapter = catalogueAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        catalogueAdapter.onItemClicked = {
            findNavController().navigate(
                CatalogueFragmentDirections.actionCatalogueFragmentToDetailCatalogueMovStoreFragment(
                    it.backdropPath,
                    it.originalTitle,
                    it.originalLanguage,
                    it.releaseDate,
                    it.voteAverage,
                    it.voteCount,
                    it.overview
                )
            )
        }
    }

    private fun observeLiveData() {
        catalogueViewModel.statusLocalDB.observe(viewLifecycleOwner, Observer {
            if (it) {
                catalogueViewModel.listMovStoreFromLocalDB.observe(
                    viewLifecycleOwner,
                    Observer { movies ->
                        catalogueAdapter.addMovies(movies)
                        homeViewModel.showLoading.value = false
                    })
            } else {
                args.category?.let { category -> catalogueViewModel.getMovieStoreFromApi(category) }
                catalogueViewModel.listMovStoreResponse.observe(
                    viewLifecycleOwner,
                    Observer { movies ->
                        catalogueAdapter.addMovies(movies)
                        homeViewModel.showLoading.value = false
                    })
            }
        })
    }
}