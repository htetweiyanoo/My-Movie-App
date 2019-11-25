package net.myanmarlinks.retrofittest.movies.repository

import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.GenericCallBack
import net.myanmarlinks.retrofittest.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.trendings.TrendingListDataCallback

interface MovieRepositoryInterf {

    fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback)
    fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack)
    fun getTrending(trendingListCallBack: TrendingListDataCallback)
    fun insertFavouriteID(id: Int, callback : GenericCallBack<Unit>)
//    fun getAllFavouriteMovie(): MutableLiveData<List<>>
}