plugins {
    alias(libs.plugins.superjob.android.library)
}

android {
    namespace = "com.superjob.android.core.ui"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(libs.androidx.splashscreen)
    implementation(libs.material)
}