package com.sayhitoiot.desafiomobile2you.api

import com.sayhitoiot.desafiomobile2you.api.model.movie.Movie
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovies
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

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<SimilarMovies>

}

interface OnGetMoviesDetailsCallback{
    fun onSuccess(movie: Movie)
    fun onError()
}

interface OnGetSimilarMoviesDetailsCallback{
    fun onSuccess(similarMovies: SimilarMovies)
    fun onError()
}
