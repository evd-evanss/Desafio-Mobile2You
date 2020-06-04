package com.sayhitoiot.desafiomobile2you.api.repository

import android.util.Log
import com.sayhitoiot.desafiomobile2you.BuildConfig
import com.sayhitoiot.desafiomobile2you.api.ApiTMDB
import com.sayhitoiot.desafiomobile2you.api.OnGetMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.api.OnGetSimilarMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.api.model.movie.Movie
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDataManager : InteractToApi {

    private var service: ApiTMDB

    companion object {
        const val TAG = "data-manager"
        const val MOVIE_ID = 153518
        const val LANGUAGE = "pt_PT"
    }

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiTMDB::class.java)

    }

    override fun getMoviesDetails(callbackMoviesDetails: OnGetMoviesDetailsCallback) {
        service.getMovieDetails(movieId = MOVIE_ID, apiKey = BuildConfig.API_KEY, language = LANGUAGE)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie> ) {

                    response.body()?.let { callbackMoviesDetails.onSuccess(it) }
                    Log.d(TAG, "response: ${response.body()} ")

                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    callbackMoviesDetails.onError()
                    t.printStackTrace()
                    Log.d(TAG, t.toString())
                }
            })
    }

    override fun getSimilarMovies(callbackSimilarMovies: OnGetSimilarMoviesDetailsCallback) {
        service.getSimilarMovies(movieId = MOVIE_ID, apiKey = BuildConfig.API_KEY, language = LANGUAGE)
            .enqueue(object : Callback<SimilarMovies> {
                override fun onResponse(
                    call: Call<SimilarMovies>,
                    response: Response<SimilarMovies>
                ) {
                    response.body()?.let { callbackSimilarMovies.onSuccess(it) }
                }

                override fun onFailure(call: Call<SimilarMovies>, t: Throwable) {
                    callbackSimilarMovies.onError()
                    t.printStackTrace()
                    Log.d(TAG, t.toString())
                }
            })
    }
}