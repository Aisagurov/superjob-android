plugins {
    alias(libs.plugins.superjob.android.feature)
    alias(libs.plugins.superjob.android.dagger)
    alias(libs.plugins.safeargs.kotlin)
}

android {
    namespace = "com.superjob.android.feature.home"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)
}