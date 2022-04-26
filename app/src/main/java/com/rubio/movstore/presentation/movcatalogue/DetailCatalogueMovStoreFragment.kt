package com.rubio.movstore.presentation.movcatalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentDetailCatalogueMovStoreBinding
import com.rubio.movstore.presentation.movcatalogue.viewModel.CatalogueViewModel

class DetailCatalogueMovStoreFragment : Fragment() {
    private lateinit var binding: FragmentDetailCatalogueMovStoreBinding
    private val catalogueViewModel: CatalogueViewModel by activityViewModels()
    private val args: DetailCatalogueMovStoreFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_catalogue_mov_store,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.detailViewModel = catalogueViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieModel = MovieModel(
            args.imageDetailMovie,
            args.titleMovieDetail,
            args.originalLanguage,
            args.releaseDate,
            args.voteAverage,
            args.voteCount,
            args.overview
        )
    }
}