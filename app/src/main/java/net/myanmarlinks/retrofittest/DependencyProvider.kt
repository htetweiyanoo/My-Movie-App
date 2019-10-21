package net.myanmarlinks.retrofittest

import android.content.Context
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.network.interceptor.RetrofitProvider
import net.myanmarlinks.retrofittest.repository.MovieDetailRepository
import net.myanmarlinks.retrofittest.repository.MovieDetailRepositoryInterf

object DependencyProvider {
    private var api: ApiService? = null

    fun init(context: Context) {
        api = RetrofitProvider.retrofit(context).create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        return api!!
    }

    fun getMovieDetailRepository(): MovieDetailRepositoryInterf {
        return MovieDetailRepository(getApiService())
    }
}