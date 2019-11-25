package net.myanmarlinks.retrofittest.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.movies.MoviesFragment
import net.myanmarlinks.retrofittest.trendings.TrendingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MoviesFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.movies_content, fragment)
                .commit()
        }

        val selectNavigationListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_movies -> replaceFragment(MoviesFragment())
                    R.id.action_trending -> replaceFragment(TrendingFragment())
                    R.id.favourite -> replaceFragment(TrendingFragment())
                }
                true
            }
        bottom_nav.setOnNavigationItemSelectedListener(selectNavigationListener)

    }

    //getTrendingList is stateRefreshId 1
//    fun getMovieList() {
//        viewModel.getMovieListing()
//        viewModel.movieLD.observe(this, Observer { movies ->
//            clearData()
//            stateRefreshId = 1
//            swipeRefreshLayout.isRefreshing = false
//            listMovies.addAll(movies)
//
//            movieAdapter = MovieAdapter(this, listMovies, this)
//            movie_recycler.adapter = movieAdapter
//            movieAdapter.notifyDataSetChanged()
//        })
//    }
//
//    //getTrendingList is stateRefreshId 2
//    fun getTrendingList() {
//        viewModel.getTrendingListing()
//        viewModel.trendingLD.observe(this, Observer { trending ->
//            clearData()
//            stateRefreshId = 2
//            swipeRefreshLayout.isRefreshing = false
//            listTrending.addAll(trending)
//            trendingAdapter = TrendingAdapter(this, listTrending, this)
//            movie_recycler.adapter = trendingAdapter
//            trendingAdapter.notifyDataSetChanged()
//        })
//    }
//
//    fun clearData() {
//        listMovies.clear()
//        listTrending.clear()
//    }


    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.movies_content, fragment)
            .commit()
    }
}
