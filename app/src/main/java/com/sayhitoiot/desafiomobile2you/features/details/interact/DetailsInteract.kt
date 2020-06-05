package com.sayhitoiot.desafiomobile2you.features.details.interact

import com.sayhitoiot.desafiomobile2you.api.OnGetMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.api.OnGetSimilarMoviesDetailsCallback
import com.sayhitoiot.desafiomobile2you.storage.LikesStorage
import com.sayhitoiot.desafiomobile2you.storage.MoviesStorage
import com.sayhitoiot.desafiomobile2you.storage.MoviesStorage.Companion.EMPTY
import com.sayhitoiot.desafiomobile2you.storage.entity.MovieEntity
import com.sayhitoiot.desafiomobile2you.api.model.movie.Movie
import com.sayhitoiot.desafiomobile2you.api.repository.ApiDataManager
import com.sayhitoiot.desafiomobile2you.api.repository.InteractToApi
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovie
import com.sayhitoiot.desafiomobile2you.api.model.similar.SimilarMovies
import com.sayhitoiot.desafiomobile2you.features.details.interact.contract.DetailsInteractToPresenter
import com.sayhitoiot.desafiomobile2you.features.details.interact.contract.DetailsPresenterToInteract
import com.sayhitoiot.desafiomobile2you.utils.createImagePath

class DetailsInteract(private val presenter: DetailsPresenterToInteract) :
    DetailsInteractToPresenter {

    private val repository: InteractToApi = ApiDataManager()
    private val likeStorage = LikesStorage(presenter.context)
    private val moviesStorage = MoviesStorage(presenter.context)

    override fun fetchData() {

        if (moviesStorage.poster != EMPTY) {
            fetchSimilarMovies()
        } else {
            fetchDetailsOfMoviesOnAPI()
        }

    }

    private fun fetchDetailsOfMoviesOnAPI() {

        repository.getMoviesDetails(object : OnGetMoviesDetailsCallback {
            override fun onSuccess(movie: Movie) {

                moviesStorage.likes = movie.vote_count
                moviesStorage.views = movie.popularity.toFloat()
                moviesStorage.poster = movie.poster_path.createImagePath()

                MovieEntity(likes = moviesStorage.likes, views = moviesStorage.views, poster = movie.poster_path.createImagePath())
                    .also { presenter.didFinishFetchMovieOnAPI(it) }

                fetchSimilarMovies()
            }

            override fun onError() {
                presenter.didFinishFetchMovieOnAPIWithError()
            }

        })

    }

    private fun fetchSimilarMovies() {
        repository.getSimilarMovies(object : OnGetSimilarMoviesDetailsCallback {
            override fun onSuccess(similarMovies: SimilarMovies) {
                val similarMoviesList = mutableListOf<SimilarMovie>()

                similarMovies.results.forEach {
                    similarMoviesList.add(it)
                }

                val movie = getMovie()

                if(movie != null) {
                    presenter.didFinishFetchSimilarMoviesOnAPI(movie, similarMoviesList)
                } else {
                    presenter.didFinishFetchMovieOnAPIWithError()
                }
            }

            override fun onError() {
                presenter.didFinishFetchMovieOnAPIWithError()
            }

        })
    }

    private fun getMovie(): MovieEntity? {
        return MovieEntity(
            likes = moviesStorage.likes,
            views = moviesStorage.views,
            poster = "${moviesStorage.poster}"
        )
    }

    override fun fetchLikeOnStorage() {
        presenter.didFetchLikeOnStorage(likeStorage.like)
    }

    override fun handleLike() {
        likeStorage.like =  !likeStorage.like
        presenter.didFinishHandleLike(likeStorage.like)
        updateLikes(likeStorage.like)
    }

    private fun updateLikes(status: Boolean) {
        when(status) {
            true -> {
                moviesStorage.likes ++
            }
            false -> {
                moviesStorage.likes --
            }
        }
        presenter.requestUpdateLikesOnView(moviesStorage.likes)
    }

}