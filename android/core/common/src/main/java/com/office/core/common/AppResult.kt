package com.office.core.common

/**
 * Lightweight functional result wrapper used across domain and data layers.
 * The app avoids throwing raw exceptions into the UI thread (reja.txt 31-bo'lim).
 */
sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Failure(val error: DocumentError) : AppResult<Nothing>

    val isSuccess: Boolean get() = this is Success

    fun <R> map(transform: (T) -> R): AppResult<R> = when (this) {
        is Success -> Success(transform(data))
        is Failure -> Failure(error)
    }

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Failure -> null
    }

    companion object {
        fun <T> success(data: T): AppResult<T> = Success(data)
        fun failure(error: DocumentError): AppResult<Nothing> = Failure(error)
    }
}
