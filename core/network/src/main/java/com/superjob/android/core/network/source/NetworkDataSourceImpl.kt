package com.superjob.android.core.network.source

import com.superjob.android.core.common.Result
import com.superjob.android.core.network.model.EmploymentNetwork
import com.superjob.android.core.network.service.NetworkService
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val service: NetworkService,
): NetworkDataSource, BaseNetworkDataSource() {
     override suspend fun getEmployment(): Result<EmploymentNetwork> =
        getResult { service.getEmployment() }
}