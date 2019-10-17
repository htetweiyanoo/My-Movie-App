package net.myanmarlinks.retrofittest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: ApiService) : ViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun onNotifyMovie(){
        MainRepository(api).getMovies(movies)
    }

    val movieLD:MutableLiveData<List<Movie>>
    get() = movies


}