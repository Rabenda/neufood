package io.github.rabenda.neufood.listener

interface ListListener<T> {
    fun onResponse(list: List<T>)
    fun onFail(msg: String)
}