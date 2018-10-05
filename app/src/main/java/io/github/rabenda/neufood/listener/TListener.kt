package io.github.rabenda.neufood.listener

interface TListener<T> {
    fun onResponse(t: T)
    fun onFail(msg: String)
}