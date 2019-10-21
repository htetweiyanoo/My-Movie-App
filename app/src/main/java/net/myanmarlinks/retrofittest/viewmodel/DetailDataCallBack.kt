package net.myanmarlinks.retrofittest.viewmodel

import net.myanmarlinks.retrofittest.model.detail.MovieResponse

/**
 * Created by Vincent on 2019-10-21
 */
sealed class DetailDataState {

    data class Success(val movieResponse: MovieResponse) : DetailDataState()

    data class Failure(val exception: Throwable) : DetailDataState()

}


interface DetailDataCallBack {
    

    fun onDetailCallBack(detailDataState: DetailDataState)

}