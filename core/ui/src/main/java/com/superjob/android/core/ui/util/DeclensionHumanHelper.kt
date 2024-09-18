package com.superjob.android.core.ui.util

import android.content.Context
import com.superjob.android.core.ui.R

fun getDeclensionHuman(context: Context, number: Int): String {

    if (number % 100 / 10 == 1) {
        return context.getString(R.string.person)
    }

    return when (number % 10) {
        0, 1, 5, 6, 7, 8, 9 -> context.getString(R.string.person)
        else -> context.getString(R.string.persons)
    }
}