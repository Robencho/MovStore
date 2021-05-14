package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.models.MovieResponse
import com.rubio.movstore.domain.repositories.CatalogueRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetCatalogue @Inject constructor(private val catalogueRepository: CatalogueRepository) {

    fun getListCatalogue(category: String?, response: ((data: List<Movie>?) -> Unit)) {
        catalogueRepository.getMovStoreApi(category, response = {
            it?.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) = Unit

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response(response.body()?.results)
                }
            })
        })
    }

    suspend fun insertInDB(movs: List<Movie>?) {
        catalogueRepository.setMoviesToLocalDB(movs)
    }

    suspend fun getAllMoviesLocalDB(response: (data: (List<Movie>)) -> Unit) {
        catalogueRepository.getMoviesFromLocalDB {
            response(it)
        }
    }
}