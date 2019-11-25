package net.myanmarlinks.retrofittest.trendings

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
import kotlinx.android.synthetic.main.movie_trendings_content.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.detail.MovieDetailActivity
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie
import net.myanmarlinks.retrofittest.viewmodel.MainViewModel
import net.myanmarlinks.retrofittest.viewmodel.MainViewModelFactory

class TrendingFragment : Fragment(), TrendingAdapter.OnItemClickedListener {


    private lateinit var trendingViewModel: MainViewModel
    private val trendingList = ArrayList<TrendingMovie>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.movie_trendings_content, container, false)
        trendingViewModel =
            ViewModelProviders.of(this, MainViewModelFactory())[MainViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.trending_movie_recycler)

        val adapter = TrendingAdapter(
            requireContext(),
            trendingList,
            this
        )

        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(context, 2)

        view.trending_swipe_view.isRefreshing = true
        trendingViewModel.getTrendingListing()
        trendingViewModel.trendings.observe(this, Observer {
            view.trending_swipe_view.isRefreshing = false
            adapter.setTrending(it)
        })

        view.trending_swipe_view.setOnRefreshListener {
            trendingViewModel.getTrendingListing()
        }

        return view
    }

    override fun onItemClicked(trending: TrendingMovie) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        val trendingId = trending.id
        intent.putExtra("TRENDING_ID", trendingId)
        startActivity(intent)
    }

}