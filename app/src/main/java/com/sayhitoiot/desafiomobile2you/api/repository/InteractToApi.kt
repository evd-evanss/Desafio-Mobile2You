package com.sayhitoiot.desafiomobile2you.api.repository

import com.sayhitoiot.desafiomobile2you.api.OnGetMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.api.OnGetSimilarMoviesDetailsCallback


interface InteractToApi {

    fun getMoviesDetails(callbackMoviesDetails: OnGetMoviesDetailsCallback)
    fun getSimilarMovies(callbackSimilarMovies: OnGetSimilarMoviesDetailsCallback)

}