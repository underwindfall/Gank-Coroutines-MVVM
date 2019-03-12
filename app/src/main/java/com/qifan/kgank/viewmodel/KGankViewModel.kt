package com.qifan.kgank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qifan.kgank.entity.KGankEntity
import com.qifan.kgank.repository.KGankRepo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Qifan on 06/03/2019.
 */
class KGankViewModel(private val repo: KGankRepo) : ViewModel(), CoroutineScope {
    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext = viewModelJob + Dispatchers.IO
    val gankContentLiveData = MutableLiveData<KGankEntity>()
    //    fun fetchGankContent() {
//        launch {
//            // This launch uses the coroutineContext defined
//            // by the coroutine presenter.
//            val result = repo.getArticleList().await()
//            withContext(Dispatchers.Main) {
//
//            }
//        }
//
//    }
    fun fetchGankContent() {
        launch {
            // This launch uses the coroutineContext defined
            // by the coroutine presenter.
            val result = repo.getArticleList().await()
            withContext(Dispatchers.Main) {
                gankContentLiveData.postValue(result)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        // By default, every coroutine initiated in this context
        // will use the job and dispatcher specified by the
        // coroutineContext.
        // The coroutines are scoped to their execution environment.
        viewModelJob.cancel()
    }


}