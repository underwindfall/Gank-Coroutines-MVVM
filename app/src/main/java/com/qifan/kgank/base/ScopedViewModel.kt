package com.qifan.kgank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Qifan on 14/03/2019.
 */

open class ScopedViewModel : ViewModel(), CoroutineScope {
    private val job: Job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean> = _spinner

    fun <T : Any> executeCall(callService: suspend () -> BaseResult<T>, handleResult: ((BaseResult<T>)) -> Unit) {
//        TODO refactor by viewModelScope
//        viewModelScope.launch {  }
        launch {
            _spinner.value = true
            val serviceResult = async(Dispatchers.IO) {
                callService.invoke()
            }
            _spinner.value = false
            handleResult(serviceResult.await())
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}