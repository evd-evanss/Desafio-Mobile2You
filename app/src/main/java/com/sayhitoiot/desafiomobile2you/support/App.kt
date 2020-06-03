package com.sayhitoiot.desafiomobile2you.support

import android.app.Application
import com.sayhitoiot.desafiomobile2you.service.SyncApi


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        SyncApi()
    }

}