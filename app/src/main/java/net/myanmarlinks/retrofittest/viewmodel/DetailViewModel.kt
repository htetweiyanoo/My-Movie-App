package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.DependencyProvider
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.repository.MovieDetailRepository

class DetailViewModel : ViewModel() {
    val movieDetail: MutableLiveData<MovieResponse> = MutableLiveData()

    private val apiService = DependencyProvider.getApiService()

    fun getDetail(id: Int) {
        MovieDetailRepository(apiService).getDetail(id, movieDetail)
    }
}