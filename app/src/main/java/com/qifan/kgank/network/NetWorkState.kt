package com.qifan.kgank.network

import androidx.annotation.StringDef

/**
 * Created by Qifan on 16/03/2019.
 * Status of a resource that is provided to the UI.
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
class NetWorkState(@Status status: String) {

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(NetWorkState.SUCCESS, NetWorkState.ERROR, NetWorkState.LOADING)
    annotation class Status

    companion object {
        const val SUCCESS = "SUCCESS"
        const val ERROR = "ERROR"
        const val LOADING = "LOADING"
    }
}