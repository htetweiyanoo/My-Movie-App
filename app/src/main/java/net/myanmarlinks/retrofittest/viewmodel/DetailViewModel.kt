package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.repository.MovieDetailRepositoryInterf

class DetailViewModel(
    private val movieDetailRepository: MovieDetailRepositoryInterf
) : ViewModel() {


    companion object {
        const val TAG = "DetailViewModel"
    }

    val movieDetailLiveData: MutableLiveData<MovieResponse> = MutableLiveData()

    fun getDetail(id: Int) {

        //Concrete implementation
        movieDetailRepository.getDetail(id, object : DetailDataCallBack {
            override fun onDetailCallBack(detailDataState: DetailDataState) {
                when (detailDataState) {
                    is DetailDataState.Success -> {
                        movieDetailLiveData.postValue(detailDataState.movieResponse)
                    }
                    is DetailDataState.Failure -> {
                        //Show Error
                    }
                }
            }

        })

    }
}