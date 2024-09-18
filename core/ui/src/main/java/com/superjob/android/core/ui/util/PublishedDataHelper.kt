package com.superjob.android.core.ui.util

import android.content.Context
import com.superjob.android.core.ui.R

fun createPublishedDate(context: Context, publishedDate: String): String {
    val day = if (publishedDate.subSequence(8, 9) != "0") {
        publishedDate.subSequence(8..9)
    } else {
        publishedDate.subSequence(9, 10)
    }
    val month = publishedDate.subSequence(5..6)
    val monthTitle = when (month) {
        "01" -> context.getString(R.string.january)
        "02" -> context.getString(R.string.february)
        "03" -> context.getString(R.string.march)
        "04" -> context.getString(R.string.april)
        "05" -> context.getString(R.string.may)
        "06" -> context.getString(R.string.june)
        "07" -> context.getString(R.string.july)
        "08" -> context.getString(R.string.august)
        "09" -> context.getString(R.string.september)
        "10" -> context.getString(R.string.october)
        "11" -> context.getString(R.string.november)
        "12" -> context.getString(R.string.december)
        else -> ""
    }
    return "${context.getString(R.string.published)} $day $monthTitle"
}