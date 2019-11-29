package net.myanmarlinks.retrofittest.feature.favourite.favouritedetail

import net.myanmarlinks.retrofittest.local.entity.FavouriteTodo

sealed class FavouriteDetailDataState {
    data class Success(val favouriteList: List<FavouriteTodo>) : FavouriteDetailDataState()

    data class Failure(val exception: Throwable) : FavouriteDetailDataState()
}

interface FavouriteDetailCallBack{
    fun onFavouriteDetailCallBack(favouriteDataState: FavouriteDetailDataState)
}