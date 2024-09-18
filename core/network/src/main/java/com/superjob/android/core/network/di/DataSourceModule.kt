package com.superjob.android.core.network.di

import com.superjob.android.core.network.source.NetworkDataSource
import com.superjob.android.core.network.source.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    internal abstract fun bindNetworkDataSource(
        dataSource: NetworkDataSourceImpl
    ): NetworkDataSource
}