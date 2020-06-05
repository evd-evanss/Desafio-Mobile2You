package com.sayhitoiot.desafiomobile2you.features.details.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.sayhitoiot.desafiomobile2you.R
import com.sayhitoiot.desafiomobile2you.utils.createImagePath
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovie
import com.sayhitoiot.desafiomobile2you.utils.ToGenres
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class SimilarMoviesAdapter(var context: Context, var similarMoviesList: MutableList<SimilarMovie>):
    RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>(){

    companion object{
        const val TAG = "similar-adapter"
        const val H = 200f
        const val ALPHA = 0.3f
        const val DURATION = 1000L
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_movies))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(similarMoviesList, position)
    }

    override fun getItemCount() = similarMoviesList.size

    fun updateList(feeds: MutableList<SimilarMovie>) {
        this.similarMoviesList.clear()
        this.similarMoviesList.addAll(feeds)
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var textTitle: TextView = itemView.itemMovies_textView_title
        private var textSubTitle: TextView = itemView.itemMovies_textView_subtitle
        private var imagePoster: ImageView = itemView.itemMovies_imageView_poster
        private val enterInterpolator = AnticipateOvershootInterpolator(5f)

        fun bind(similarMovie: MutableList<SimilarMovie>, position: Int){
            setAnimation(itemView)
            Log.d("adapter", "title: ${similarMovie[position].title} url: ${similarMovie[position].id}")
            textTitle.text = similarMovie[position].title
            textSubTitle.text = getGenre(similarMovie[position])
            val path = similarMovie[position].poster_path.createImagePath()
            Picasso
                .get()
                .load(path)
                .centerCrop()
                .fit()
                .error(R.drawable.ic_launcher_background)
                .into(imagePoster)

        }

        private fun getGenre(similarMovie: SimilarMovie) : String {
            val genreIds = similarMovie.genre_ids
            val year = similarMovie.release_date.subSequence(0, 4)
            var genres = ""
            genreIds.forEach {
                genres += "${ToGenres.invoke(it)}, "
            }
            return "$year ${genres.subSequence(0, genres.count() - 2)}"
        }

        private fun setAnimation(child: View) {
            child.translationY = if(adapterPosition == 0) -H else H
            child.alpha = ALPHA
            child.animate().translationY(0f).alpha(1f)
                .setInterpolator(enterInterpolator).duration = DURATION
        }

    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}