package com.sayhitoiot.desafiomobile2you.features.details.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton
import com.sayhitoiot.desafiomobile2you.R
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovie
import com.sayhitoiot.desafiomobile2you.features.details.presenter.DetailsPresenter
import com.sayhitoiot.desafiomobile2you.features.details.presenter.contract.DetailsPresenterToView
import com.sayhitoiot.desafiomobile2you.features.details.presenter.contract.DetailsViewToPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_activity.*

class DetailsActivity : AppCompatActivity(),
    DetailsViewToPresenter {

    private val presenter: DetailsPresenterToView by lazy {
        DetailsPresenter(this)
    }
    private val adapter: SimilarMoviesAdapter by lazy {
        SimilarMoviesAdapter(mutableListOf())
    }

    override val context: Context get() = this
    private var recyclerView: RecyclerView? = null
    private var imageMovie: ImageView? = null
    private var imageLike: ImageView? = null
    private var textLikes: TextView? = null
    private var textViews: TextView? = null
    private var textError: TextView? = null
    private var imageError: ImageView? = null
    private var imageBack: ImageView? = null
    private var buttonUpdate: MaterialButton? = null
    private var appBar: AppBarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun initializeViews() {
        appBar = appBarLayout
        recyclerView = detailsActivity_recyclerView_similarMovies
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
        imageMovie = detailsActivity_imageView_background
        imageLike = detailsActivity_imageView_likes
        imageLike?.setOnClickListener { presenter.imageLikeTapped() }
        imageBack = detailsActivity_imageView_back
        imageBack?.setOnClickListener { onBackPressed() }
        textLikes = detailsActivity_textView_likes
        textViews = detailsActivity_textView_popularity
        textError = detailsActivity_textView_error
        imageError = detailsActivity_imageView_error
        buttonUpdate = detailsActivity_materialButton_update
        buttonUpdate?.setOnClickListener { presenter.onResume() }
    }

    override fun showDetailsOfMovie(path: String, likes: String, views: String) {
        imageMovie?.let {
            Picasso
                .get()
                .load(path)
                .fit()
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
        textLikes?.text = likes
        textViews?.text = views
        Log.d(TAG, path)
    }

    override fun showDetailsOfMovieWithError() {
        textError?.visibility = VISIBLE
        imageError?.visibility = VISIBLE
        buttonUpdate?.visibility = VISIBLE
        appBar?.visibility = GONE
        recyclerView?.visibility = GONE
    }

    override fun postSimilarMovies(similarMovies: MutableList<SimilarMovie>) {
        appBar?.visibility = VISIBLE
        recyclerView?.visibility = VISIBLE
        imageError?.visibility = GONE
        textError?.visibility = GONE
        buttonUpdate?.visibility = GONE
        recyclerView?.setItemViewCacheSize(similarMovies.size)
        recyclerView?.visibility = VISIBLE
        adapter.updateList(similarMovies)
    }

    override fun renderImageLikeFill() {
        imageLike?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp))
    }

    override fun renderImageLikeDefault() {
        imageLike?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp))
    }

    override fun updateLikes(likes: String) {
        textLikes?.text = likes
    }

    companion object {
        const val TAG = "details-activity"
    }

}
