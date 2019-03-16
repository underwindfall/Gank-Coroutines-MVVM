package com.qifan.kgank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by Qifan on 15/03/2019.
 */

open class BaseViewModel : ViewModel() {
    private val _snackBar = MutableLiveData<BaseEvent<String>>()
    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean> = _spinner
    val snackBar: LiveData<BaseEvent<String>> = _snackBar

    fun <T : Any> executeCall(
        callService: suspend () -> BaseResult<T>,
        handleResult: ((T)) -> Unit
    ) {
        viewModelScope.launch {
            _spinner.value = true
            val deferred = async(Dispatchers.IO) {
                callService.invoke()
            }
            try {
                val result = deferred.await().getOrThrow()
                handleResult(result)
            } catch (exception: Exception) {
                when (exception) {
                    is NullPointerException -> _snackBar.value = BaseEvent("Response body is null")
                    is HttpException -> _snackBar.value = BaseEvent("Http Exception error")
                    else -> _snackBar.value = BaseEvent("Other Exception")
                }
            } finally {
                _spinner.value = false
            }


        }


    }

}