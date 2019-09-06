package net.myanmarlinks.retrofittest.network

import android.content.Context
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.network.interceptor.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET("members")
//    fun getMembers(): Call<Members>

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int
    ): Call<Movies>

    companion object {
        operator fun invoke(context: Context): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(context))
                .addInterceptor(logging)
//                .addInterceptor(ConnectivityInterceptor(context))
                .build()

            return Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}