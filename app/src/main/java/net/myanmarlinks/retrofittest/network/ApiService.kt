package net.myanmarlinks.retrofittest.network

import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int
    ): Call<Movies>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String
    ): Call<MovieResponse>
}