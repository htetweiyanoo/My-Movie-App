package net.myanmarlinks.retrofittest.feature.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_result_card.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.feature.search.searchmodel.SearchMovie

class SearchMovieAdapter(
    val context: Context,
    private var searchMovieNames: List<SearchMovie>,
    private val listener: OnItemClickedListener
) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.search_result_card, parent, false)

        return SearchMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchMovieNames.size
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val searchMovieName = searchMovieNames[position]
        holder.bind(searchMovieName, listener)
    }

    fun setSearchMovieName(
        searchMovieList:
        List<SearchMovie>
    ) {
        this.searchMovieNames = searchMovieList
        notifyDataSetChanged()
    }

    interface OnItemClickedListener {
        fun onItemClicked(searchMovie: SearchMovie)
    }

    inner class SearchMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchTextView = itemView.search_result_card_text

        fun bind(searchMovie: SearchMovie, listener: OnItemClickedListener) {
            itemView.setOnClickListener {
                listener.onItemClicked(searchMovie)
            }

            searchTextView.text = searchMovie.originalTitle
        }
    }


}