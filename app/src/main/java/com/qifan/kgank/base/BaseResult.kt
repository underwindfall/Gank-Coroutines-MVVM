package com.qifan.kgank.base

import okhttp3.Response
import retrofit2.HttpException


/**
 * Created by Qifan on 12/03/2019.
 */

sealed class BaseResult<out T : Any> {

    data class Success<out T : Any>(val value: T, override val response: Response) : BaseResult<T>(),
        ResponseResult {
        override fun toString(): String = "Result Success {value=$value }"
    }

    data class Error(override val exception: HttpException, override val response: Response) : BaseResult<Nothing>(),
        ErrorResult, ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    data class Exception(override val exception: Throwable) : BaseResult<Nothing>(),
        ErrorResult {
        override fun toString(): String = "Result.Exception{$exception}"
    }
}

/**
 * Interface for [Result] classes with [okhttp3.Response]: [Result.Ok] and [Result.Error]
 */
interface ResponseResult {
    val response: Response
}

/**
 * Interface for [Result] classes that contains [Throwable]: [Result.Error] and [Result.Exception]
 */
interface ErrorResult {
    val exception: Throwable
}

/**
 * Returns [Result.Ok.value] or `null`
 */
fun <T : Any> BaseResult<T>.getOrNull(): T? = (this as? BaseResult.Success)?.value

/**
 * Returns [Result.Ok.value] or [default]
 */
fun <T : Any> BaseResult<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Returns [Result.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
fun <T : Any> BaseResult<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is BaseResult.Success -> value
        is BaseResult.Error -> throw throwable ?: exception
        is BaseResult.Exception -> throw throwable ?: exception
    }
}