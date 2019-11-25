package net.myanmarlinks.retrofittest.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_card.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.movies.moviemodel.Movie
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie

class MovieAdapter(
    val context: Context,
    private var movies: List<Movie>,
    private val listener: OnItemClickedListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_card, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, listener)
    }

    fun setMovie(movieList :
                 List<Movie>){
        this.movies = movieList
        notifyDataSetChanged()
    }

    interface OnItemClickedListener {
        fun onItemClicked(movie: Movie)
    }

    interface TrendingOnItemClickedListener {
        fun trendingOnItemClicked(trending: TrendingMovie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.movie_img
        val titleTextView = itemView.movie_title

        fun bind(movie: Movie, listener: OnItemClickedListener) {
            itemView.setOnClickListener {
                listener.onItemClicked(movie)
            }

            titleTextView.text = movie.title
            Picasso.with(context)
                .load(context.getString(R.string.img_url) + "/" + movie.poster_path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView)
        }
    }
}