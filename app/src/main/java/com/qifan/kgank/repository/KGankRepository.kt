package com.qifan.kgank.repository

import com.qifan.kgank.base.BaseRepository
import com.qifan.kgank.base.BaseResult
import com.qifan.kgank.entity.KGankEntity
import com.qifan.kgank.network.KGankService
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Qifan on 15/02/2019.
 */

class KGankRepository constructor(private val remoteService: KGankService) : BaseRepository() {

    suspend fun getArticleListAsync(
        scope: CoroutineScope
    ): BaseResult<KGankEntity> {
        return remoteService.gankAsync("Android", 1, 10).awaitResult(scope)
    }
}