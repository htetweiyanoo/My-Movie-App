package net.myanmarlinks.retrofittest.network.interceptor

import android.content.Context
import net.myanmarlinks.retrofittest.R
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", context.getString(R.string.api_key))
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)

    }
}