package com.wd.demo.mvp


interface ModelCallback<T> {
    fun success(t: T)
    fun failure(msg: String)
}