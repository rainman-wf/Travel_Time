package ru.netology.common.utils

import android.util.Log

fun Any.log(message: Any?) {
    Log.d("travel_tag", "${this::class.simpleName} : ${message.toString()}")
}

fun <T : Any> T.log(): T {
    Log.d("travel_tag", "${this::class.simpleName} : $this")
    return this
}