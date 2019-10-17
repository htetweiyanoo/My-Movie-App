package net.myanmarlinks.retrofittest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_card.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.model.Movie

class MovieAdapter(
    val context: Context,
    private val movies: List<Movie>,
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

    interface OnItemClickedListener {
        fun onItemClicked(movie: Movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.movie_img
        val textView = itemView.movie_title

        fun bind(movie: Movie, listener: OnItemClickedListener) {
            itemView.setOnClickListener{
                listener.onItemClicked(movie)
            }

            textView.text = movie.title
            Picasso.with(context)
                .load(context.getString(R.string.img_url) + "/" + movie.poster_path)
                .into(imageView)
        }
    }
}