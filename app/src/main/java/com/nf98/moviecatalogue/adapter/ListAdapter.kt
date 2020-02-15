package com.nf98.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nf98.moviecatalogue.R
import com.nf98.moviecatalogue.model.Movie
import com.nf98.moviecatalogue.model.TVShow
import kotlinx.android.synthetic.main.item_movie.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter: RecyclerView.Adapter<ItemViewHolder<*>>() {

    companion object {
        const val TYPE_MOVIE = 0
        const val TYPE_TV = 1
    }

    private val list = ArrayList<Any>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(items: ArrayList<*>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return when(viewType) {
            TYPE_MOVIE -> MovieViewHolder(view)
            TYPE_TV -> TVShowViewHolder(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder<*>, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(list[position] as Movie)
            is TVShowViewHolder -> holder.bind(list[position] as TVShow)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Movie -> TYPE_MOVIE
            is TVShow -> TYPE_TV
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun getItemCount(): Int = list.size

    inner class MovieViewHolder(itemView: View): ItemViewHolder<Movie>(itemView) {
        override fun bind(item: Movie) {
            with(itemView) {
                    /*val options = RequestOptions()
                        .placeholder(R.drawable.your_placeholder_image)
                        .error(R.drawable.your_error_image)*/

                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w154${item.posterPath}")
                        .into(iv_poster)

                    tv_name.text = item.originalTitle
                    tv_desc.text = item.overview
                    item.releaseDate?.let { setDate(it) }
                    setDonut(item.score)

                    itemView.setOnClickListener { onItemClickCallback?.onItemClicked(item) }
            }
        }

        private fun setDonut(input: Float){
            val score = input.times(10.0).toInt()

            with(itemView) {
                donut_score.progress = score.toFloat()
                if (score > 0)
                    donut_score.text = score.toString()
                else
                    donut_score.text = "NR"
                when (score) {
                    in Int.MIN_VALUE..39 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutRed)
                    in 40..59 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutOrange)
                    in 60..69 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutYellow)
                    in 70..79 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutLime)
                    in 80..Int.MAX_VALUE -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutGreen)
                }
            }
        }

        private fun setDate(input: String) {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val format = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())

            with(itemView) {
                tv_date.text = format.format(parser.parse(input))
            }
        }

    }

    inner class TVShowViewHolder(itemView: View): ItemViewHolder<TVShow>(itemView) {
        override fun bind(item: TVShow) {
            with(itemView) {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w154${item.posterPath}")
                    .into(iv_poster)

                tv_name.text = item.name
                tv_desc.text = item.overview
                item.firstAirDate?.let { setDate(it) }
                setDonut(item.score)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(item) }
            }
        }

        private fun setDonut(input: Float){
            val score = input.times(10.0).toInt()

            with(itemView) {
                donut_score.progress = score.toFloat()
                if (score > 0)
                    donut_score.text = score.toString()
                else
                    donut_score.text = "NR"
                when (score) {
                    in Int.MIN_VALUE..39 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutRed)
                    in 40..59 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutOrange)
                    in 60..69 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutYellow)
                    in 70..79 -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutLime)
                    in 80..Int.MAX_VALUE -> donut_score.finishedStrokeColor =
                        ContextCompat.getColor(context, R.color.donutGreen)
                }
            }
        }

        private fun setDate(input: String) {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val format = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())

            with(itemView) {
                tv_date.text = format.format(parser.parse(input))
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Any)
    }
}