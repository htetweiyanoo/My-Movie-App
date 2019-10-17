package net.myanmarlinks.retrofittest.network.interceptor

import android.content.Context
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDetailService {

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String
    ): Call<MovieResponse>

    companion object {
        operator fun invoke(context: Context): ApiDetailService {
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
                .create(ApiDetailService::class.java)
        }
    }
}