package net.myanmarlinks.retrofittest.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.adapter.MovieAdapter
import net.myanmarlinks.retrofittest.model.Movie
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.viewmodel.MainViewModel
import net.myanmarlinks.retrofittest.viewmodel.MainViewModelFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickedListener {
    override fun onItemClicked(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        val movieId = movie.id
        intent.putExtra("MOVIE_ID",movieId)
        startActivity(intent)
    }


    lateinit var viewModel: MainViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var listMovies: MutableList<Movie>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val api = ApiService(this)
        listMovies = ArrayList()
        movieAdapter = MovieAdapter(this, listMovies, this)
        movie_recycler.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(api))
            .get(MainViewModel::class.java)

        viewModel.onNotifyMovie()
        viewModel.movieLD.observe(this, Observer { movies ->
            listMovies.addAll(movies)
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
