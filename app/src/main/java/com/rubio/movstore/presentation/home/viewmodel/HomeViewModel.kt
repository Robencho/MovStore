package com.rubio.movstore.presentation.home.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubio.movstore.domain.models.Slider
import com.rubio.movstore.domain.usecases.GetMoviesUseCase
import com.rubio.movstore.utils.MovStoreConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMovieImages: GetMoviesUseCase) : ViewModel() {
    var showLoading = MutableLiveData<Boolean>(false)
    var isHome = MutableLiveData<Boolean>(false)
    var sliders = MutableLiveData<List<Slider>>()

    fun timerLoading() {
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                showLoading.value = false
            }
        }
        timer.start()
    }

    fun showSliders() {
        viewModelScope.launch {
            getMovieImages.invoke(MovStoreConstants.VALUE_MOVIE_POPULAR, response = {
                it?.let {
                    val slidFilter = it.filterIndexed { index, _ -> (index in 0..3) }
                    val filterLIst = ArrayList<Slider>()
                    for (i in slidFilter) {
                        filterLIst.add(
                            Slider(
                                i.posterPath,
                                i.title
                            )
                        )
                    }
                    sliders.value = filterLIst
                    Log.d("Respnse: ", slidFilter.toString())
                }
            })
        }
    }
}