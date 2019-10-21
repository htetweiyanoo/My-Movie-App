package net.myanmarlinks.retrofittest.repository

import android.util.Log
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.viewmodel.DetailDataCallBack
import net.myanmarlinks.retrofittest.viewmodel.DetailDataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository(
    private val api: ApiService
) : MovieDetailRepositoryInterf {
    companion object {
        const val TAG = "RetrofitTest"
    }


    override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
        val language = "en-US"
        val movieDetailCall = api.getDetailMovies(movieId, language)

        movieDetailCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Movie_Retail_Repo_Error " + t.message)
                detailCallBack.onDetailCallBack(DetailDataState.Failure(t))
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        detailCallBack.onDetailCallBack(DetailDataState.Success(response.body()!!))
                        Log.d(TAG, "Movie_Retail_Repo " + response.body()!!.originalTitle)
                    }
                }
            }

        })
    }
}