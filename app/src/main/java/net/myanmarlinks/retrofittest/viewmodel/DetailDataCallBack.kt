package net.myanmarlinks.retrofittest.repository

import net.myanmarlinks.retrofittest.model.detail.MovieResponse

sealed class DetailDataState {
    data class Success(val movieResponse: MovieResponse) : DetailDataState()

    data class Failure(val exception: Throwable) : DetailDataState()
}

interface DetailDataCallBack {
    fun onDetailCallBack(detailDataState: DetailDataState)
}