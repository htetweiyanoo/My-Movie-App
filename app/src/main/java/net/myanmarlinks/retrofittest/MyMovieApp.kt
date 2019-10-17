package net.myanmarlinks.retrofittest

import android.app.Application

/**
 * Created by Vincent on 2019-10-17
 */
class MyMovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyProvider.init(this)
    }
}
