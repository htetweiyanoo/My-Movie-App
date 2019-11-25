package net.myanmarlinks.retrofittest.trendings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_card.view.*
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.trendings.trending.TrendingMovie

class TrendingAdapter(
    val context: Context,
    private var trending: List<TrendingMovie>,
    private val listener: OnItemClickedListener
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return TrendingViewHolder(view)
    }


    override fun getItemCount(): Int {
        return trending.size
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trending = trending[position]
        holder.bind(trending, listener)
    }

    fun setTrending(trendingList: List<TrendingMovie>) {
        this.trending = trendingList
        notifyDataSetChanged()
    }

    interface OnItemClickedListener {
        fun onItemClicked(trending: TrendingMovie)
    }

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.movie_img
        val textView = itemView.movie_title

        fun bind(trending: TrendingMovie, listener: OnItemClickedListener) {
            itemView.setOnClickListener {
                listener.onItemClicked(trending)
            }
            textView.text = trending.name
            Glide.with(context)
                .load(context.getString(R.string.img_url) + "/" + trending.posterPath)
                .into(imageView)
        }
    }
}