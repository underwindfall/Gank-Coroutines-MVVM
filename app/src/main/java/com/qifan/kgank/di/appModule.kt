package com.qifan.kgank.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.qifan.kgank.GankService
import com.qifan.kgank.repository.GankRepo
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Qifan on 06/03/2019.
 */

val repoModule = module {
    factory<GankRepo> { GankRepo(get()) }
}
val remoteModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single<GankService> {
        get<Retrofit>().create(GankService::class.java)
    }
}

val appModule = listOf(repoModule, remoteModule)