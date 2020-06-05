package com.sayhitoiot.desafiomobile2you.storage

import android.content.Context

class LikesStorage(context: Context) : SessionStorage(context) {

    companion object {

        private const val KEY = "LIKES-VALUE-STORAGE"

    }

    var like: Boolean
        get() = this.getShared().getBoolean(KEY, false)
        set(value) = saveLike(value)

    private fun saveLike(like: Boolean) {
        val editor = getShared().edit()
        editor.putBoolean(KEY, like)
        editor.apply()
    }
}