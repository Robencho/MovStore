package com.rubio.movstore.presentation.movcatalogue.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.domain.usecases.GetMoviesLocalDBUseCase
import com.rubio.movstore.domain.usecases.GetMoviesUseCase
import com.rubio.movstore.domain.usecases.SaveMovieLocalDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val getMovieUseCase: GetMoviesUseCase,
    private val saveMovieLocalDBUseCase:SaveMovieLocalDBUseCase,
    private val getMoviesLocalDBUseCase: GetMoviesLocalDBUseCase) : ViewModel()
{

    var listMovStoreResponse = MutableLiveData<List<Movie>>()
    var listMovStoreFromLocalDB = MutableLiveData<List<Movie>>()
    var statusLocalDB = MutableLiveData<Boolean>(false)
    var showSimpleToolbar = MutableLiveData<Boolean>(false)
    var showFullToolbar = MutableLiveData<Boolean>(false)
    var titleToolbar = MutableLiveData<String>()

    fun showFullToolbar() {
        showFullToolbar.value = true
        showSimpleToolbar.value = false
    }

    fun showSimpleToolbar() {
        showFullToolbar.value = false
        showSimpleToolbar.value = true
    }

    fun getMoviesFromLocalDB() {
        viewModelScope.launch {
            try {
                getMoviesLocalDBUseCase.invoke { listMoviesDB ->
                    if (listMoviesDB.isNotEmpty()) {
                        listMovStoreFromLocalDB.value = listMoviesDB
                        statusLocalDB.value = true
                    } else {
                        statusLocalDB.value = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Error: ", e.toString())
            }
        }
    }

    fun getMovieStoreFromApi() {
        viewModelScope.launch {
            getMovieUseCase.invoke("popular", response = { moviesResponse ->
                listMovStoreResponse.value = moviesResponse
                moviesResponse?.let {
                    setMoviesLocalDB(it)
                }
            })
        }
    }

    private fun setMoviesLocalDB(movies: List<Movie>) {
        viewModelScope.launch {
            try {
                saveMovieLocalDBUseCase.invoke(movies)
            } catch (e: Exception) {
                Log.d("Error: ", e.toString())
            }
        }
    }
}