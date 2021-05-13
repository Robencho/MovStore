package com.rubio.movstore.ui.movcatalogue.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.domain.usecases.GetCatalogue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(val useCase1:GetCatalogue):ViewModel() {
    var listMovStoreResponse = MutableLiveData<List<Movie>>()

    fun getMovieStore(){
        useCase1.getListCatalogue("popular", response = {
            listMovStoreResponse.value = it
        })
    }
}