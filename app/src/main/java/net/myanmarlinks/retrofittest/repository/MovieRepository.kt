package net.myanmarlinks.retrofittest.feature.movies.repository

import android.util.Log
import net.myanmarlinks.retrofittest.DependencyProvider
import net.myanmarlinks.retrofittest.GenericCallBack
import net.myanmarlinks.retrofittest.feature.detail.DetailDataCallBack
import net.myanmarlinks.retrofittest.feature.detail.DetailDataState
import net.myanmarlinks.retrofittest.feature.detail.detailmodel.MovieResponse
import net.myanmarlinks.retrofittest.feature.favourite.*
import net.myanmarlinks.retrofittest.feature.movies.MovieListDataCallback
import net.myanmarlinks.retrofittest.feature.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.feature.movies.moviemodel.Movies
import net.myanmarlinks.retrofittest.feature.trailer.MovieTrailerDataCallBack
import net.myanmarlinks.retrofittest.feature.trailer.trailermodel.MovieTrailerList
import net.myanmarlinks.retrofittest.feature.trendings.TrendingListDataCallback
import net.myanmarlinks.retrofittest.feature.trendings.trending.TrendingMovies
import net.myanmarlinks.retrofittest.local.entity.FavouriteTodo
import net.myanmarlinks.retrofittest.local.entity.Todo
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

    override fun insertFavouriteID(id: Int, callback: GenericCallBack<Unit>) {

        executor.execute {
            val favId = database.toDoDao().insertToFavourite(FavouriteTodo(id))
//            Log.d("Favourite Sucessfully", "$favId")
            callback.onSuccess(Unit)
        }

    }

    override fun deleteFavouriteMovie(
        id: Int,
        deleteFavouriteMovieDataCallBack: DeleteFavouriteMovieDataCallBack
    ) {
        executor.execute {
            val deleteFavouriteMovie = database.toDoDao().deleteFavouriteMovie(id)
        }
    }

    override fun getAllFavouriteMovie(favouriteCallBack: FavouriteDataCallBack) {

        executor.execute {
            val favouriteList: List<Todo> = database.toDoDao().getAllFavouriteMovie()
            Log.d("Favourite_List", favouriteList.toString())
            val _favouriteData = ArrayList<Todo>()

            for (d in favouriteList) {
                _favouriteData.add(
//                    MovieResponse(
//                        id = d.id,
//                        originalTitle = d.title,
//                        posterPath = d.posterPath,
//                        voteAverage = d.voteAverage,
//                        overview = d.overview,
//                        releaseDate = d.releaseDate
//                    )
                    Todo(
                        id = d.id,
                        title = d.title,
                        posterPath = d.posterPath,
                        voteAverage = d.voteAverage,
                        overview = d.overview,
                        releaseDate = d.releaseDate
                    )

                )
            }
            favouriteCallBack.onFavouriteCallBack(FavouriteDataState.Success(_favouriteData))

            Log.d("FAVOURITE_ERROR", "Movie_Favourite_Error")
            favouriteCallBack.onFavouriteCallBack(FavouriteDataState.Failure(Throwable()))
        }


    }

    override fun searchFavouriteIDResult(
        id: Int,
        favouriteIDResultDataCallBack: FavouriteIDResultDataCallBack
    ) {
        executor.execute {
            val favdetailId = database.toDoDao().searchFavouriteMovie(id)
            when (favdetailId) {
                0 -> favouriteIDResultDataCallBack.onFavouriteIDResultDataCallBack(
                    FavouriteIDResultDataState.Success(false)
                )

                1 -> favouriteIDResultDataCallBack.onFavouriteIDResultDataCallBack(
                    FavouriteIDResultDataState.Success(true)
                )
            }

        }
    }

    override fun getMovieTrailer(movieId: Int, trailerDataCallBack: MovieTrailerDataCallBack) {
        val language = "en-US"
        val movieTrailerCall =
            api.getTrailer(movieId, language)
        movieTrailerCall.enqueue(object : Callback<MovieTrailerList> {
            override fun onFailure(call: Call<MovieTrailerList>, t: Throwable) {
                Log.d("MY_API", "Failed to Trailer API")
            }

            override fun onResponse(
                call: Call<MovieTrailerList>,
                response: Response<MovieTrailerList>
            ) {
                trailerDataCallBack.onSuccess(response.body()!!.movieTrailerList)
            }
        })
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

