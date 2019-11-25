package net.myanmarlinks.retrofittest.detail

import net.myanmarlinks.retrofittest.detail.detailmodel.MovieResponse

sealed class DetailDataState {

    data class Success(val movieResponse: MovieResponse) : DetailDataState()

    data class Failure(val exception: Throwable) : DetailDataState()
}

interface DetailDataCallBack {
    fun onDetailCallBack(detailDataState: DetailDataState)
}