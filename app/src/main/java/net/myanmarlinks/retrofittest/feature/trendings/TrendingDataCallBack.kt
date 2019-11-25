package net.myanmarlinks.retrofittest.trendings

import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie

sealed class TrendingDataState {

    data class Success(val trending: TrendingMovie): TrendingDataState()

    data class Failure(val exception: Throwable): TrendingDataState()
}

interface TrendingDataCallBack {
    fun onTrendingDataCallBack(trendingDataState: TrendingDataState)
}