package com.superjob.android.core.network.service

import com.superjob.android.core.common.EMPLOYMENT_EXPORT
import com.superjob.android.core.common.EMPLOYMENT_ID
import com.superjob.android.core.network.model.EmploymentNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    /**
     * Api details
     * https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download
     */
    @GET("u/0/uc")
    suspend fun getEmployment(
        @Query("id") id: String = EMPLOYMENT_ID,
        @Query("export") export: String = EMPLOYMENT_EXPORT,
    ): Response<EmploymentNetwork>
}