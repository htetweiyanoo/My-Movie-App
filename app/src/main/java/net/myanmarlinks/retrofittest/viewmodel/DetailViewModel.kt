package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.network.interceptor.ApiDetailService
import net.myanmarlinks.retrofittest.repository.MainRepository
import net.myanmarlinks.retrofittest.repository.MovieDetailRepository

class DetailViewModel(private val api: ApiDetailService):ViewModel() {
    val movieDetail: MutableLiveData<MovieResponse> = MutableLiveData()

    fun getDetail(id : Int){
        MovieDetailRepository(api).getDetail(id, movieDetail)
    }
}