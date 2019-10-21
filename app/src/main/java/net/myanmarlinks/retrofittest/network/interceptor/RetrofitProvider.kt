package net.myanmarlinks.retrofittest.network.interceptor

import android.content.Context
import net.myanmarlinks.retrofittest.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private var retrofit: Retrofit? = null

    fun retrofit(context: Context): Retrofit {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(context))
                .addInterceptor(logging)
                .addInterceptor(ConnectivityInterceptor(context))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }
}