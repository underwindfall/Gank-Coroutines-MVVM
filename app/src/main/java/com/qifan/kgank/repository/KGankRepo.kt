package com.qifan.kgank.repository

import com.qifan.kgank.KGankService

/**
 * Created by Qifan on 15/02/2019.
 */

class KGankRepo constructor(private val remote: KGankService) {
    fun getArticleList() = remote.gank("Android", 1, 10)
}