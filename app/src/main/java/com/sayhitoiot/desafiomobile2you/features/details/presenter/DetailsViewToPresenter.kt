package com.sayhitoiot.desafiomobile2you.features.details.presenter

import android.content.Context
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovie

interface DetailsViewToPresenter {
    val context: Context
    fun initializeViews()
    fun showDetailsOfMovie(path: String, likes: String, views: String)
    fun postSimilarMovies(similarMovies: MutableList<SimilarMovie>)
    fun renderImageLikeFill()
    fun renderImageLikeDefault()
    fun showDetailsOfMovieWithError()

    fun updateLikes(likes: String)
}