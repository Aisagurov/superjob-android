plugins {
    alias(libs.plugins.superjob.android.library)
    alias(libs.plugins.superjob.android.dagger)
    alias(libs.plugins.superjob.android.room)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.superjob.android.core.database"
}

dependencies {
    implementation(projects.core.common)

    api(libs.kotlinx.serialization.json)
}