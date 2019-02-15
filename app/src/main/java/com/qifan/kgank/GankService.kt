package com.qifan.kgank

import com.qifan.kgank.entity.GankEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Qifan on 11/02/2019.
 */
interface GankService {
    @GET("data/{type}/{pageSize}/{page}")
    fun gank(
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("pageSize") pageSize: Int
    ): Deferred<GankEntity>
}