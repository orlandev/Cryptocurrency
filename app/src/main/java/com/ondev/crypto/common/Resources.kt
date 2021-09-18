package com.ondev.crypto.common

sealed class Resources<T>(val data: T? = null, val error: ResourcesError? = null) {
    class Success<T>(data: T) : Resources<T>(data)
    class Error<T>(error: ResourcesError? = null, data: T? = null):Resources<T>(data = data, error = error)
    class Loading<T>(data: T? = null) : Resources<T>(data)
}

enum class ResourcesError {
    IO_ERROR,
    HTTP_ERROR,
    UNKNOW
}
