package com.qifan.kgank

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Qifan on 11/02/2019.
 */
object KGankApi {
    private fun retrofit(): Retrofit {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BASIC
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://gank.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val instance: GankService = retrofit().create(GankService::class.java)

}
