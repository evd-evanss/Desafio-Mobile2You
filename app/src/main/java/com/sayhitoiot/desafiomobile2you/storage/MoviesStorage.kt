package com.sayhitoiot.desafiomobile2you.storage

import android.content.Context

class MoviesStorage(context: Context) : SessionStorage(context) {

    companion object {

        private const val KEY_LIKES = "COUNT-VALUE-STORAGE"
        private const val KEY_VIEWS = "VIEWS-VALUE-STORAGE"
        private const val KEY_POSTER= "POSTER-VALUE-STORAGE"
        const val EMPTY = "https://"

    }

    var likes: Int
        get() = this.getShared().getInt(KEY_LIKES, 0)
        set(value) = saveLike(value)

    var views: Float
        get() = this.getShared().getFloat(KEY_VIEWS, 0F)
        set(value) = saveViews(value)

    var poster: String?
        get() = this.getShared().getString(KEY_POSTER, EMPTY)
        set(value) = savePoster(value)

    private fun saveLike(like: Int) {
        val editor = getShared().edit()
        editor.putInt(KEY_LIKES, like)
        editor.apply()
    }

    private fun saveViews(views: Float) {
        val editor = getShared().edit()
        editor.putFloat(KEY_VIEWS, views)
        editor.apply()
    }

    private fun savePoster(poster: String?) {
        val editor = getShared().edit()
        editor.putString(KEY_POSTER, poster)
        editor.apply()
    }
}