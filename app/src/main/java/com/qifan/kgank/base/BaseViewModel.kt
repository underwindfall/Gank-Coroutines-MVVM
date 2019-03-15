package com.qifan.kgank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Qifan on 15/03/2019.
 */

open class BaseViewModel : ViewModel() {
    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean> = _spinner

    fun <T : Any> executeCall(callService: suspend () -> BaseResult<T>, handleResult: ((BaseResult<T>)) -> Unit) {
        viewModelScope.launch {
            _spinner.value = true
            val serviceResult = async(Dispatchers.IO) {
                callService.invoke()
            }
            handleResult(serviceResult.await())
            _spinner.value = false
        }


    }

}