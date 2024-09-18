plugins {
    alias(libs.plugins.superjob.android.library)
    alias(libs.plugins.superjob.android.dagger)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.superjob.android.core.network"

    defaultConfig {
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = "\"https://drive.usercontent.google.com/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)

    api(libs.kotlinx.serialization.json)
    api(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
}