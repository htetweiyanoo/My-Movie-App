package net.myanmarlinks.retrofittest.repository

import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.viewmodel.DetailDataCallBack

interface MovieDetailRepositoryInterf {
    fun getMovies(movies: MutableLiveData<List<Movie>>)
    fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack)
    fun getTrending(mediaType: String, time)
}