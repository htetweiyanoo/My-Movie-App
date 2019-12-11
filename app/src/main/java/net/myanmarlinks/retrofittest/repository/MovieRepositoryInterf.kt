package net.myanmarlinks.retrofittest.feature.movies.repository

import net.myanmarlinks.retrofittest.GenericCallBack
import net.myanmarlinks.retrofittest.feature.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.feature.favourite.DeleteFavouriteMovieDataCallBack
import net.myanmarlinks.retrofittest.feature.favourite.FavouriteDataCallBack
import net.myanmarlinks.retrofittest.feature.favourite.FavouriteIDResultDataCallBack
import net.myanmarlinks.retrofittest.feature.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.feature.trailer.MovieTrailerDataCallBack
import net.myanmarlinks.retrofittest.feature.trendings.TrendingListDataCallback

interface MovieRepositoryInterf {

    fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback)
    fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack)
    fun getTrending(trendingListCallBack: TrendingListDataCallback)
    fun insertFavouriteID(id: Int, callback: GenericCallBack<Unit>)
    fun getAllFavouriteMovie(favouriteCallBack: FavouriteDataCallBack)
    fun searchFavouriteIDResult(id: Int, favouriteIDResultDataCallBack: FavouriteIDResultDataCallBack)
    fun deleteFavouriteMovie(id: Int, deleteFavouriteMovieDataCallBack: DeleteFavouriteMovieDataCallBack)
    fun getMovieTrailer(movieId: Int, trailerDataCallBack: MovieTrailerDataCallBack)
}