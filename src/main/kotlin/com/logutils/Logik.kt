package com.logutils

object Logik {
    fun d(tag: String, msg: String) {
        println("$tag $msg")
    }

    fun d(tag: String, msg: String, throwable: Throwable) {
        println("$tag $msg \n ${throwable.message}")
    }
}