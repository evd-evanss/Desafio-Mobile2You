package com.sayhitoiot.desafiomobile2you.service

import com.sayhitoiot.desafiomobile2you.api.OnGetMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.api.model.Movie
import com.sayhitoiot.desafiomobile2you.api.repository.ApiDataManager
import com.sayhitoiot.desafiomobile2you.api.repository.InteractToApi

class SyncApi {

    private val repository: InteractToApi = ApiDataManager()
    private val response: MutableList<Movie> = mutableListOf()

    init {
        fetchDetailsOfMovies()
    }

    private fun fetchDetailsOfMovies() {
        repository.getMoviesDetails(object : OnGetMoviesDetailsCallback {
            override fun onSuccess(result: Movie) {

            }

            override fun onError() {

            }

        })
    }

}