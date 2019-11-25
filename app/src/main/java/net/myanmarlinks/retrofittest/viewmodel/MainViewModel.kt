package net.myanmarlinks.retrofittest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie
import net.myanmarlinks.retrofittest.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.movies.repository.MovieRepositoryInterf
import net.myanmarlinks.retrofittest.trendings.TrendingListDataCallback

class MainViewModel(
    private val movieRepositoryInterf: MovieRepositoryInterf
) : ViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

//    val apiService = DependencyProvider.getApiService()


    fun getMovieListing() {
        movieRepositoryInterf.getMovieWithoutLiveData(object :
            MovieListDataCallback {
            override fun onSuccess(movieList: List<Movie>) {
                movies.postValue(movieList)
            }

            override fun onFailure(exception: Exception) {
                Log.d("fail","error")
            }

        })
    }

    val movieLD: MutableLiveData<List<Movie>>
        get() = movies

    val trendings: MutableLiveData<List<TrendingMovie>> = MutableLiveData()

    fun getTrendingListing() {
        movieRepositoryInterf.getTrending(object :
            TrendingListDataCallback {
            override fun onSuccess(trendingList: List<TrendingMovie>) {
                trendings.postValue(trendingList)
            }

            override fun onFailure(exception: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    val trendingLD: MutableLiveData<List<TrendingMovie>>
        get() = trendings
}