package com.superjob.android.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("lookingNumber") val lookingNumber: Int? = null,
    @ColumnInfo("title") val title: String? = null,
    @ColumnInfo("addressTown") val addressTown: String? = null,
    @ColumnInfo("addressStreet") val addressStreet: String? = null,
    @ColumnInfo("addressHouse") val addressHouse: String? = null,
    @ColumnInfo("company") val company: String? = null,
    @ColumnInfo("experiencePreviewText") val experiencePreviewText: String? = null,
    @ColumnInfo("experienceText") val experienceText: String? = null,
    @ColumnInfo("publishedDate") val publishedDate: String? = null,
    @ColumnInfo("salaryShort") val salaryShort: String? = null,
    @ColumnInfo("salaryFull") val salaryFull: String? = null,
    @ColumnInfo("schedules") val schedules: List<String>?,
    @ColumnInfo("appliedNumber") val appliedNumber: Int? = null,
    @ColumnInfo("description") val description: String? = null,
    @ColumnInfo("responsibilities") val responsibilities: String? = null,
    @ColumnInfo("questions") val questions: List<String>? = null,
    @ColumnInfo("isSelected") val isSelected: Boolean = false,
)