package com.sayhitoiot.desafiomobile2you.api.model.similar

data class SimilarMovies(
    val page: Int,
    val results: List<SimilarMovie>,
    val total_pages: Int,
    val total_results: Int
)