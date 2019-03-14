package com.qifan.kgank

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.qifan.kgank.di.appModule
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

/**
 * Created by Qifan on 06/03/2019.
 */
class KGankApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule, logger = AndroidLogger(showDebug = BuildConfig.DEBUG))
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
