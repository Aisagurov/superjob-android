package com.superjob.android.core.common

fun <T> T?.emptyIfNull(default: T): T {
    return this ?: default
}