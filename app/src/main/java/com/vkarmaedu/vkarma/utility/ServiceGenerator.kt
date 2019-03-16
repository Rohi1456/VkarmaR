package com.vkarmaedu.vkarma.utility

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = ""
private const val Application_ID = ""

class ServiceGenerator {
    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().create()

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Client-ID $Application_ID")
                .build()
            chain.proceed(request)
        }

    private val okHttpClient = okHttpClientBuilder.build()
    fun <T> createService(serviceClass: Class<T>): T {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!.create(serviceClass)
    }
}