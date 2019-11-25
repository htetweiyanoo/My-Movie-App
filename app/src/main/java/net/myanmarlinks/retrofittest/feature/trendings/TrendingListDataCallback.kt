package net.myanmarlinks.retrofittest.trendings

import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie

interface TrendingListDataCallback {

    fun onSuccess(trendingList: List<TrendingMovie>)

    fun onFailure(exception: Exception)
}