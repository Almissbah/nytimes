package com.almissbbah.nytimes.data

class Resource<T, M>(val payload: T?, val action: M?, val message: String?) {
    enum class Status {
        NONE, LOADING, SUCCESS, FAIL, CONNECTION_ERROR, SERVER_ERROR, FORBIDDEN, NOT_FOUND
    }
}