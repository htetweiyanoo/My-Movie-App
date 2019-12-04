package net.myanmarlinks.retrofittest.feature.trailer

import net.myanmarlinks.retrofittest.feature.trailer.trailermodel.MovieTrailer

interface TrailerDataCallBack {

    fun onSuccess(movieTrailerList: List<MovieTrailer>)

    fun onFailure(exception: Exception)
}