package com.sayhitoiot.desafiomobile2you.api

fun String.ToPath() : String {

    return "https://image.tmdb.org/t/p/w500/${this}"

}