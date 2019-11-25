package net.myanmarlinks.retrofittest.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.GenericCallBack
import net.myanmarlinks.retrofittest.detail.detailmodel.MovieResponse
import net.myanmarlinks.retrofittest.feature.movies.repository.MovieRepositoryInterf
import java.lang.Exception

class DetailViewModel(private val movieDetailRepository: MovieRepositoryInterf) :
    ViewModel() {

    companion object {
        const val TAG = "DetailViewModel"
    }

    val movieDetailLiveData: MutableLiveData<MovieResponse> = MutableLiveData()

    fun getDetail(id: Int) {
        movieDetailRepository.getDetail(id, object :
            DetailDataCallBack {
            override fun onDetailCallBack(detailDataState: DetailDataState) {
                when (detailDataState) {
                    is DetailDataState.Success -> {
                        movieDetailLiveData.postValue(detailDataState.movieResponse)
                    }
                    is DetailDataState.Failure -> {

                        Log.d("DETAIL_DATA_STATE_ERR", "Error")
                    }
                }
            }

        })
    }

    fun insertFavouriteID(id: Int) {
        movieDetailRepository.insertFavouriteID(id, object : GenericCallBack<Unit> {
            override fun onSuccess(item: Unit) {
                Log.i("DetailViewModel", "Insert success")
            }

            override fun onError(exception: Exception) {
                exception.printStackTrace()
            }

        })
    }
}