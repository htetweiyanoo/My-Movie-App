package net.myanmarlinks.retrofittest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.viewmodel.DetailDataCallBack
import net.myanmarlinks.retrofittest.viewmodel.DetailDataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository(
    private val api: ApiService
) : MovieDetailRepositoryInterf {

    companion object {
        const val TAG = "RetrofitTest"
    }


    override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
        val language = "en-US"
        val movieDetailCall = api.getDetailMovies(movieId, language)
        movieDetailCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Movie_Retail_Repo_Error " + t.message)
                detailCallBack.onDetailCallBack(DetailDataState.Failure(t))
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        detailCallBack.onDetailCallBack(DetailDataState.Success(response.body()!!))
                        Log.d(TAG, "Movie_Retail_Repo " + response.body()!!.originalTitle)
                    }
                }
            }

        })
    }

    override fun getMovies(movies: MutableLiveData<List<Movie>>) {
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