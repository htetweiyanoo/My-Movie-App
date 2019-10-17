package net.myanmarlinks.retrofittest

import android.content.Context
import net.myanmarlinks.retrofittest.network.ApiService
import net.myanmarlinks.retrofittest.network.RetrofitProvider

/**
 * Created by Vincent on 2019-10-17
 */

object DependencyProvider {

    private var api: ApiService? = null

    fun init(context: Context) {
        api = RetrofitProvider.retrofit(context).create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        return api!!
    }

}