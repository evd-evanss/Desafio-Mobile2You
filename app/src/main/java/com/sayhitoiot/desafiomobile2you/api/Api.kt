package com.sayhitoiot.desafiomobile2you.api

import com.sayhitoiot.desafiomobile2you.api.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiTMDB {

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>

}

interface OnGetMoviesDetailsCallback{
    fun onSuccess(result: Movie)
    fun onError()
}

