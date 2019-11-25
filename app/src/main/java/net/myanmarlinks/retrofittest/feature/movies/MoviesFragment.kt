package net.myanmarlinks.retrofittest.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.detail.MovieDetailActivity
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.viewmodel.MainViewModel
import net.myanmarlinks.retrofittest.viewmodel.MainViewModelFactory

class MoviesFragment : Fragment(), MovieAdapter.OnItemClickedListener {

    private val movieViewModel : MainViewModel by lazy{
        ViewModelProviders.of(this, MainViewModelFactory())[MainViewModel::class.java]
    }
    private val adapter: MovieAdapter by lazy{
        MovieAdapter(
            requireContext(),
            movieList,
            this
        )
    }
    private var movieList = ArrayList<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_main, container, false)

        //movieViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //view acess
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_recycler)

        //adapter
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        //data
        view.movie_swipe_view.isRefreshing = true




        view.movie_swipe_view.setOnRefreshListener {
            movieViewModel.getMovieListing()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.getMovieListing()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel.movies.observe(this, Observer {
            movie_swipe_view.isRefreshing = false
            adapter.setMovie(it)
        })
    }

    override fun onItemClicked(movie: Movie) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        val movieId = movie.id
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

}