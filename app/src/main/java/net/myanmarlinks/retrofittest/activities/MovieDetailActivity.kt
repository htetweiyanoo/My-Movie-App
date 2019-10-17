package net.myanmarlinks.retrofittest.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.network.interceptor.ApiDetailService
import net.myanmarlinks.retrofittest.viewmodel.DetailViewModel
import net.myanmarlinks.retrofittest.viewmodel.DetailViewModelFactory


class MovieDetailActivity : AppCompatActivity() {

    val api by lazy {
        ApiDetailService(this)
    }

    val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this, DetailViewModelFactory(api = api))
            .get(DetailViewModel::class.java)
    }
    lateinit var itemView: View
    val imageView: ImageView by lazy {
        findViewById<ImageView>(R.id.movie_detail_img)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        setContentView(net.myanmarlinks.retrofittest.R.layout.movie_detail)





        viewModel.movieDetail.observe(this, object : Observer<MovieResponse> {
            override fun onChanged(t: MovieResponse?) {
                showMovieDetail(t!!)
            }
        })

        viewModel.getDetail(movieId)

    }

    fun showMovieDetail(movieResponse: MovieResponse) {
        Log.d("RetrofitTest", movieResponse.originalTitle)
        Glide.with(this)
            .load(this.getString(R.string.img_url) + "/" + movieResponse.posterPath)
            .into(imageView)
    }
}