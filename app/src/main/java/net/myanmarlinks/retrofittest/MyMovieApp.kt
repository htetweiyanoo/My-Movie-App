package net.myanmarlinks.retrofittest

import android.app.Application

class MyMovieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyProvider.init(this)
    }
}