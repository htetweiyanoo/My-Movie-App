package net.myanmarlinks.retrofittest

import android.content.Context
import net.myanmarlinks.retrofittest.local.entity.MovieDatabase
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.network.interceptor.RetrofitProvider
import net.myanmarlinks.retrofittest.movies.repository.MovieRepository
import net.myanmarlinks.retrofittest.movies.repository.MovieRepositoryInterf

object DependencyProvider {
    private var api: ApiService? = null
    private var dataBase : MovieDatabase? = null

    fun init(context: Context) {
        api = RetrofitProvider.retrofit(context).create(ApiService::class.java)
        dataBase = MovieDatabase(context)
    }

    fun getApiService(): ApiService {
        return api!!
    }

    fun getMovieDetailRepository(): MovieRepositoryInterf {
        return MovieRepository()
    }

    fun getMovieDatabase() : MovieDatabase{
        return  dataBase!!
    }
}