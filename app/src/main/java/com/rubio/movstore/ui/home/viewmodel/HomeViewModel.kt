package com.rubio.movstore.ui.home.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubio.movstore.domain.usecases.GetCatalogue
import com.rubio.movstore.ui.movcatalogue.model.SliderModel
import com.rubio.movstore.utils.MovStoreConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMovieImages: GetCatalogue) : ViewModel() {
    var showLoading = MutableLiveData<Boolean>(false)
    var isHome = MutableLiveData<Boolean>(false)
    var sliders = MutableLiveData<SliderModel>()

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
        getMovieImages.getListCatalogue(MovStoreConstants.VALUE_MOVIE_POPULAR, response = {
            sliders.value = SliderModel(
                it?.get(0)?.backdropPath,
                it?.get(1)?.backdropPath,
                it?.get(2)?.backdropPath
            )
        })
    }
}