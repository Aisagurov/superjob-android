package com.superjob.android.core.network.source

import com.superjob.android.core.common.Result
import com.superjob.android.core.network.model.EmploymentNetwork

interface NetworkDataSource {
    suspend fun getEmployment(): Result<EmploymentNetwork>
}