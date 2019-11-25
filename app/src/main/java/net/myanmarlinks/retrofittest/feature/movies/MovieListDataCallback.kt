package net.myanmarlinks.retrofittest.movies

import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import java.lang.Exception

interface MovieListDataCallback  {

    fun onSuccess(movieList: List<Movie>)

    fun onFailure(exception: Exception)
}