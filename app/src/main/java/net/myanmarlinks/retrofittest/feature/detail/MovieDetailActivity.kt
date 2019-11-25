package net.myanmarlinks.retrofittest.detail

//import net.myanmarlinks.retrofittest.GlideApp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.activities.MainActivity
import net.myanmarlinks.retrofittest.detail.detailmodel.MovieResponse


class MovieDetailActivity : AppCompatActivity() {
    val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(
            this,
            DetailViewModelFactory()
        )
            .get(DetailViewModel::class.java)
    }
    lateinit var itemView: View

    val imageView: ImageView by lazy {
        findViewById<ImageView>(R.id.movie_detail_img)
    }

    val btnImage: ImageButton by lazy {
        findViewById<ImageButton>(R.id.btn_cancel)
    }

    val detailName: TextView by lazy {
        findViewById<TextView>(R.id.movie_name)
    }

    val voteAvg: TextView by lazy {
        findViewById<TextView>(R.id.vote_avg)
    }

    val overview: TextView by lazy {
        findViewById<TextView>(R.id.overview)
    }

    val releaseDate: TextView by lazy {
        findViewById<TextView>(R.id.release_date)
    }


    var detailID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        val trendingId = intent.getIntExtra("TRENDING_ID", 0)

        setContentView(R.layout.movie_detail)

        viewModel.movieDetailLiveData.observe(this,
            Observer<MovieResponse> { t -> showMovieDetail(t!!) })

        when {
            movieId != 0 -> {
                viewModel.getDetail(movieId)
                detailID = movieId
            }
            trendingId != 0 -> {
                viewModel.getDetail(trendingId)
                detailID = trendingId
            }
            else -> Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
        }
        btnImage.setOnClickListener {
            val intentToMain = Intent(this, MainActivity::class.java)
            startActivity(intentToMain)
        }

        val fab = findViewById<View>(R.id.fab)
        fab.setOnClickListener { view ->

            viewModel.insertFavouriteID(detailID)

            Log.d("Favourite", "Hello Favourite")
//            Snackbar.make(
//                view,
//                "Add movie to your favourite list sucessfully!!!",
//                Snackbar.LENGTH_LONG
//            )
//                .show()
        }

    }

    fun showMovieDetail(movieResponse: MovieResponse) {
        val movieTitle = findViewById<TextView>(R.id.movie_name)
        val voteAvg = findViewById<TextView>(R.id.vote_avg)
        val overview = findViewById<TextView>(R.id.overview)
        val releaseDate = findViewById<TextView>(R.id.release_date)
        movieTitle.text = movieResponse.originalTitle
        voteAvg.text = movieResponse.voteAverage.toString()
        overview.text = movieResponse.overview
        releaseDate.text = movieResponse.releaseDate
        Glide.with(this)
            .load(this.getString(R.string.img_url) + "/" + movieResponse.posterPath)
            .into(imageView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}