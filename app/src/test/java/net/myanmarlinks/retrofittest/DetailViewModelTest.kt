package net.myanmarlinks.retrofittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import net.myanmarlinks.retrofittest.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.detail.DetailDataState
import net.myanmarlinks.retrofittest.detail.DetailViewModel
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.detail.detailmodel.MovieResponse
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie
import net.myanmarlinks.retrofittest.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.movies.repository.MovieRepositoryInterf
import net.myanmarlinks.retrofittest.trendings.TrendingDataCallBack
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun testDetailSuccess() {
        // Given
        val fakeMovieResponse = MovieResponse(
            1,
            "title",
            "posterPath",
            20.0,
            "Overview",
            "11/4/2019"
        )

        val fakeMovieRepository = object : MovieRepositoryInterf {
            override fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getMovies(movies: MutableLiveData<List<Movie>>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getTrending(
                trending: List<TrendingMovie>,
                trendingCallBack: TrendingDataCallBack
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
                detailCallBack.onDetailCallBack(DetailDataState.Success(fakeMovieResponse))
            }
        }

        val detailViewModel =
            DetailViewModel(
                fakeMovieRepository
            )

        // When
        detailViewModel.getDetail(1)

        val actual = detailViewModel.movieDetailLiveData.value

        //Then
        Assert.assertEquals(fakeMovieResponse, actual)

    }
}