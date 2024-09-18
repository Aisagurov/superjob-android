plugins {
    alias(libs.plugins.superjob.android.library)
    alias(libs.plugins.superjob.android.dagger)
}

android {
    namespace = "com.superjob.android.core.datastore"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.datastore.preferences)
}