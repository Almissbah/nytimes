package com.almissbbah.nytimes.data.repo

import com.almissbbah.nytimes.data.Resource


interface RepoCallback<T> {
    fun onResult(result: Resource<T, Resource.Status>)
}