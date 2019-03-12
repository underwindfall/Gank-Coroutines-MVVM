package com.qifan.kgank.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.qifan.kgank.KGankService
import com.qifan.kgank.repository.KGankRepo
import com.qifan.kgank.viewmodel.KGankViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Qifan on 06/03/2019.
 */

val viewModelModule = module {
    viewModel { KGankViewModel(get<KGankRepo>()) }
}

val repoModule = module {
    factory<KGankRepo> { KGankRepo(get()) }
}

val remoteModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single<KGankService> {
        get<Retrofit>().create(KGankService::class.java)
    }
}

val appModule = listOf(viewModelModule, repoModule, remoteModule)