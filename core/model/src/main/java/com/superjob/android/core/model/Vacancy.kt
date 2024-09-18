package com.superjob.android.core.model

data class Vacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val addressTown: String,
    val addressStreet: String,
    val addressHouse: String,
    val company: String,
    val experiencePreviewText: String,
    val experienceText: String,
    val publishedDate: String,
    val salaryShort: String,
    val salaryFull: String,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String>,
    val isSelected: Boolean,
)