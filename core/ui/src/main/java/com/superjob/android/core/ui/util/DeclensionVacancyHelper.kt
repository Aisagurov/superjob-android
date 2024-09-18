package com.superjob.android.core.ui.util

import android.content.Context
import com.superjob.android.core.ui.R

fun getDeclensionVacancies(context: Context, number: Int): String {
    return when (number % 10) {
        1 -> context.getString(R.string.vacancy_word)
        2, 3, 4 -> context.getString(R.string.vacancies_word)
        else -> context.getString(R.string.many_vacancies_word)
    }
}