package net.myanmarlinks.retrofittest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository (
    private val api: ApiService
) {
    fun getMovies(movies: MutableLiveData<List<Movie>>) {
        val language = "en-US"
        val sort_by = "popularity.desc"
        val include_adult = false
        val include_video = false
        val page = 1
        val movieCall = api.getPopularMovies(language, sort_by, include_adult, include_video, page)
        movieCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("MY_API", "Failed to API")
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                movies.value = response.body()!!.movies
            }

        })
    }
}