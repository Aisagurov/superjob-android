plugins {
    alias(libs.plugins.superjob.android.feature)
    alias(libs.plugins.superjob.android.dagger)
}

android {
    namespace = "com.superjob.android.feature.favorites"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)
}