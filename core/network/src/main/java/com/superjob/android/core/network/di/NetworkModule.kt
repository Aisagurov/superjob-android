package com.superjob.android.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.superjob.android.core.network.BuildConfig
import com.superjob.android.core.network.service.NetworkService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    internal fun provideNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }

    @Singleton
    @Provides
    internal fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
            )
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideGeckoNetworkService(
        json: Json,
        okHttpClient: OkHttpClient,
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }
}