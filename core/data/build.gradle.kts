plugins {
    alias(libs.plugins.superjob.android.library)
    alias(libs.plugins.superjob.android.dagger)
}

android {
    namespace = "com.superjob.android.core.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)
}