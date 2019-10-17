package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.DependencyProvider
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.repository.MainRepository

class MainViewModel() : ViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val apiService = DependencyProvider.getApiService()

    fun onNotifyMovie() {
        MainRepository(apiService).getMovies(movies)
    }

    val movieLD: MutableLiveData<List<Movie>>
        get() = movies


}