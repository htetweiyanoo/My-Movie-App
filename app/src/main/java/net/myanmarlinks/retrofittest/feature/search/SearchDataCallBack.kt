package net.myanmarlinks.retrofittest.feature.search

import net.myanmarlinks.retrofittest.feature.search.searchmodel.SearchMovie

interface MovieSearchDataCallBack {

    fun onSuccess(movieTrailerList: List<SearchMovie>)

    fun onFailure(exception: Exception)
}