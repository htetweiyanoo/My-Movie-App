package net.myanmarlinks.retrofittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie
import net.myanmarlinks.retrofittest.movies.repository.MovieRepositoryInterf
import net.myanmarlinks.retrofittest.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.viewmodel.MainViewModel
import net.myanmarlinks.retrofittest.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.trendings.TrendingDataCallBack
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun testDataSuccess() {
        val expected = listOf<Movie>()
        val mainViewModel = MainViewModel(object : MovieRepositoryInterf {
            override fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback) {
                movieListDataCallback.onSuccess(expected)
            }

            override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getTrending(
                trending: List<TrendingMovie>,
                trendingCallBack: TrendingDataCallBack
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        mainViewModel.getMovieListing()
        val actual = mainViewModel.movies.value

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testDataSuccessTwo() {
        val expected = listOf<Movie>(
            Movie(
                id = 1,
                title = "Title",
                poster_path = "path",
                voteAvg = 20.0,
                overview = "Overview",
                releaseDate = "11/4/2019"
            )
        )
        val mainViewModel = MainViewModel(object : MovieRepositoryInterf {
            override fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback) {
                movieListDataCallback.onSuccess(expected)
            }

            override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getTrending(
                trending: List<TrendingMovie>,
                trendingCallBack: TrendingDataCallBack
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        mainViewModel.getMovieListing()
        val actual = mainViewModel.movies.value

        Assert.assertEquals(expected, actual)
    }

}