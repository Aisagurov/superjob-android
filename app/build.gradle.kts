plugins {
    alias(libs.plugins.superjob.android.application)
    alias(libs.plugins.superjob.android.dagger)
}

android {
    namespace = "com.superjob.android"

    defaultConfig {
        applicationId = "com.superjob.android"
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.ui)
    implementation(projects.feature.favorites)
    implementation(projects.feature.home)
    implementation(projects.feature.login)
    implementation(projects.feature.messages)
    implementation(projects.feature.profile)
    implementation(projects.feature.reactions)
    implementation(projects.feature.vacancy)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
}