package net.myanmarlinks.retrofittest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository (
    private val api: ApiService
) {
    companion object{
        const val TAG = "RetrofitTest"
    }


    fun getDetail(movieId: Int, movieDetail : MutableLiveData<MovieResponse>){
        val language = "en-US"
        val moviedetailCall = api.getDetailMovies(movieId, language)
        moviedetailCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Movie_Retail_Repo_Error " + t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if(response.isSuccessful){
                    if(response.body() != null){
                        movieDetail.value = response.body()
                        Log.d(TAG, "Movie_Retail_Repo " +response.body()!!.originalTitle)
                    }
                }
            }

        })
    }
}