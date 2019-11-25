package net.myanmarlinks.retrofittest.movies.repository

import android.util.Log
import net.myanmarlinks.retrofittest.DependencyProvider
import net.myanmarlinks.retrofittest.GenericCallBack
import net.myanmarlinks.retrofittest.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.detail.DetailDataState
import net.myanmarlinks.retrofittest.detail.detailmodel.MovieResponse
import net.myanmarlinks.retrofittest.local.entity.FavouriteTodo
import net.myanmarlinks.retrofittest.local.entity.Todo
import net.myanmarlinks.retrofittest.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.movies.moviemodel.Movies
import net.myanmarlinks.retrofittest.trendings.TrendingListDataCallback
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovies
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
//    private val api: ApiService
) : MovieRepositoryInterf {

    val api = DependencyProvider.getApiService()
    val database = DependencyProvider.getMovieDatabase()
    private val executor = DependencyProvider.getExecutor()

    override fun getTrending(trendingListCallBack: TrendingListDataCallback) {
        val mediaType = "all"
        val timeWindow = "day"
        val movieTrendingCall = api.getTrendingMovies(mediaType, timeWindow)
        movieTrendingCall.enqueue(object : Callback<TrendingMovies> {
            override fun onResponse(
                call: Call<TrendingMovies>,
                response: Response<TrendingMovies>
            ) {
                trendingListCallBack.onSuccess(response.body()!!.trendingMovies)
            }

            override fun onFailure(call: Call<TrendingMovies>, t: Throwable) {
                Log.d("My test", "Movie_Trending_Error" + t.message)
            }
        })
    }

    companion object {
        const val TAG = "RetrofitTest"
    }


    override fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack) {
        val language = "en-US"
        val movieDetailCall = api.getDetailMovies(movieId, language)
        movieDetailCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                doAsync {
                    val detailList: List<Todo> = database.toDoDao().getDetail(movieId)

                    val _detailData = ArrayList<MovieResponse>()


                    for (d in detailList) {
                        _detailData.add(
                            MovieResponse(
                                id = d.id,
                                originalTitle = d.title,
                                posterPath = d.posterPath,
                                voteAverage = d.voteAverage,
                                overview = d.overview,
                                releaseDate = d.releaseDate
                            )
                        )
                    }

                    detailCallBack.onDetailCallBack(DetailDataState.Success(_detailData.get(0)))

                    Log.d(TAG, "Movie_Retail_Repo_Error " + t.message)
                    detailCallBack.onDetailCallBack(DetailDataState.Failure(t))
                }
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        detailCallBack.onDetailCallBack(DetailDataState.Success(response.body()!!))
                        Log.d(TAG, "Movie_Retail_Repo " + response.body()!!.originalTitle)
                    }
                }
            }
        })

    }

    override fun insertFavouriteID(id: Int, callback : GenericCallBack<Unit>) {

        executor.execute {
            val favId = database.toDoDao().insertToFavourite(FavouriteTodo(id))
//            Log.d("Favourite Sucessfully", "$favId")
            callback.onSuccess(Unit)
        }

    }

    override fun getMovieWithoutLiveData(movieListDataCallback: MovieListDataCallback) {
        val language = "en-US"
        val sort_by = "popularity.desc"
        val include_adult = false
        val include_video = false
        val page = 1
        val movieCall =
            api.getPopularMovies(language, sort_by, include_adult, include_video, page)
        movieCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {

                Log.d("MY_API", "Failed to API")

                doAsync {
                    val movieList: List<Todo> = database.toDoDao().getAll()
                    val _movieList = ArrayList<Movie>()

                    for (m in movieList) {
                        _movieList.add(
                            Movie(
                                id = m.id,
                                title = m.title,
                                poster_path = m.posterPath,
                                voteAvg = m.voteAverage,
                                overview = m.overview,
                                releaseDate = m.releaseDate
                            )
                        )
                        Log.d("MY DATA", m.title)
                    }
                    movieListDataCallback.onSuccess(_movieList)
                }

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//                movies.value = response.body()!!.movies


                val todos: MutableList<Todo> = ArrayList()
                for (movie in response.body()!!.movies) {
                    todos.add(
                        Todo(
                            id = movie.id,
                            title = movie.title,
                            posterPath = movie.poster_path,
                            voteAverage = movie.voteAvg,
                            overview = movie.overview,
                            releaseDate = movie.releaseDate
                        )
                    )
                }

                doAsync {
                    database.toDoDao().insertAll(todos)
                    Log.d("MY DATA", database.toDoDao().getAll().toString())
                }
                ///
                movieListDataCallback.onSuccess(response.body()!!.movies)
            }

        })
    }
}