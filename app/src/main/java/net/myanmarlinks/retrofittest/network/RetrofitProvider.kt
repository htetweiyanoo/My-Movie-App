package net.myanmarlinks.retrofittest.network

import android.content.Context
import net.myanmarlinks.retrofittest.R
import net.myanmarlinks.retrofittest.network.interceptor.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vincent on 2019-10-17
 */
object RetrofitProvider {

    private var retrofit: Retrofit? = null

    fun retrofit(context: Context): Retrofit {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(context))
                .addInterceptor(logging)
//                .addInterceptor(ConnectivityInterceptor(context))
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