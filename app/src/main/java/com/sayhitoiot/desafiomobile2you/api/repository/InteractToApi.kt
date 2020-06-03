package com.sayhitoiot.desafiomobile2you.api.repository

import com.sayhitoiot.desafiomobile2you.api.OnGetMoviesDetailsCallback


interface InteractToApi {

    fun getMoviesDetails(callbackMoviesDetails: OnGetMoviesDetailsCallback)

}