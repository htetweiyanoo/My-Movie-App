package net.myanmarlinks.retrofittest.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.model.detail.MovieResponse
import net.myanmarlinks.retrofittest.viewmodel.DetailViewModel
import net.myanmarlinks.retrofittest.viewmodel.DetailViewModelFactory


class MovieDetailActivity : AppCompatActivity() {

    val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this, DetailViewModelFactory())
            .get(DetailViewModel::class.java)
    }
    lateinit var itemView: View
    val imageView: ImageView by lazy {
        findViewById<ImageView>(R.id.movie_detail_img)
    }

    val btnImage: ImageButton by lazy {
        findViewById<ImageButton>(R.id.btn_cancel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        setContentView(R.layout.movie_detail)

        viewModel.movieDetailLiveData.observe(this,
            Observer<MovieResponse> { t -> showMovieDetail(t!!) })

        viewModel.getDetail(movieId)
        btnImage.setOnClickListener {
            val intentToMain = Intent(this, MainActivity::class.java)
            startActivity(intentToMain)
        }


    }

    fun showMovieDetail(movieResponse: MovieResponse) {
        val movieTitle = findViewById<TextView>(R.id.movie_name)
        movieTitle.text = movieResponse.originalTitle
        Glide.with(this)
            .load(this.getString(R.string.img_url) + "/" + movieResponse.posterPath)
            .into(imageView)
    }

}