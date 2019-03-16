package com.qifan.kgank.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
 * Created by Qifan on 14/03/2019.
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class BaseEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

//class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<BaseEvent<T>> {
//    override fun onChanged(event: BaseEvent<T>?) {
//        event?.getContentIfNotHandled()?.let { value ->
//            onEventUnhandledContent(value)
//        }
//    }
//}
inline fun <T> LiveData<BaseEvent<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) })
}