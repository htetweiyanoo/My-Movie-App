package net.myanmarlinks.retrofittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.repository.MovieDetailRepositoryInterf
import net.myanmarlinks.retrofittest.viewmodel.DetailDataCallBack
import net.myanmarlinks.retrofittest.viewmodel.DetailDataState
import net.myanmarlinks.retrofittest.viewmodel.DetailViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by Vincent on 2019-10-21
 */
class DetailViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun testDetailSuccess() {
        //Given
        val fakeMovieResponse = MovieResponse(
            1,
            "title",
            "posterPath"
        )

        val fakeMovieRepository = object : MovieDetailRepositoryInterf {
            override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
                detailCallBack.onDetailCallBack(DetailDataState.Success(fakeMovieResponse))
            }
        }


        val detailViewModel = DetailViewModel(fakeMovieRepository)

        //When

        detailViewModel.getDetail(1)

        val actual = detailViewModel.movieDetailLiveData.value
        //Then

        Assert.assertEquals(fakeMovieResponse, actual)
    }


}