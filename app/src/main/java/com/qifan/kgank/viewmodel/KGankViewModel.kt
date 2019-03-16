package com.qifan.kgank.viewmodel

import androidx.lifecycle.MutableLiveData
import com.qifan.kgank.base.BaseViewModel
import com.qifan.kgank.entity.KGankEntity
import com.qifan.kgank.repository.KGankRepository

/**
 * Created by Qifan on 06/03/2019.
 */
class KGankViewModel(private val repository: KGankRepository) : BaseViewModel() {
    val gankContentLiveData = MutableLiveData<KGankEntity>()

    fun fetchGankContent() {
        executeCall(callService = { repository.getArticleListAsync() }) { result ->
            gankContentLiveData.postValue(result)
        }
    }
}

