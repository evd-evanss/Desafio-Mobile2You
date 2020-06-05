package com.sayhitoiot.desafiomobile2you.api

import com.sayhitoiot.desafiomobile2you.api.model.movie.Movie
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovies
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.QUERY_API_KEY
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.END_POINT_MOVIES
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.END_POINT_SIMILAR_MOVIES
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.PATH_ID
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.QUERY_LANGUAGE
import com.sayhitoiot.desafiomobile2you.utils.Constants.Companion.PATH_MOVIE_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTMDB {

    @GET(END_POINT_MOVIES)
    fun getMovieDetails(
        @Path(PATH_ID) id: Int,
        @Query(QUERY_API_KEY) apiKey: String,
        @Query(QUERY_LANGUAGE) language: String
    ): Call<Movie>

    @GET(END_POINT_SIMILAR_MOVIES)
    fun getSimilarMovies(
        @Path(PATH_MOVIE_ID) movieId: Int,
        @Query(QUERY_API_KEY) apiKey: String,
        @Query(QUERY_LANGUAGE) language: String
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
